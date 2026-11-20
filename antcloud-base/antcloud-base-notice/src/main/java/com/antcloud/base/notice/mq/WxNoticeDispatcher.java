package com.antcloud.base.notice.mq;

import cn.hutool.json.JSONUtil;
import com.antcloud.base.core.config.redis.RedisService;
import com.antcloud.base.notice.domain.dto.MessageDataDTO;
import com.antcloud.base.notice.domain.dto.ProcessRemindDTO;
import com.antcloud.base.notice.domain.dto.ProcessReturnDTO;
import com.antcloud.base.notice.domain.dto.TaskAssignRemindDTO;
import com.antcloud.base.notice.domain.dto.TaskOverdueRemindDTO;
import com.antcloud.base.notice.domain.dto.TaskOvertimeRemindDTO;
import com.antcloud.base.notice.domain.dto.TodoRemindDTO;
import com.antcloud.base.notice.domain.entity.WxMessage;
import com.antcloud.base.notice.domain.entity.WxResult;
import com.antcloud.base.notice.utils.MessageUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 提醒消息分派
 * <p>
 * 消息体是 JSON，靠里面的 type 字段决定走哪个分支。
 * 消费成功即返回，抛异常则交给 RocketMQ 重试（默认 16 次后进死信队列）。
 *
 * @author beg
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WxNoticeDispatcher {

    /**
     * 去重键保留时长（小时）
     * */
    private static final int DEDUP_HOURS = 2;

    private final RedisService redisService;

    /**
     * 企微推送开关：没有企微账号时只记日志，不调接口
     * */
    @Value("${antcloud.workwx.enabled:false}")
    private boolean workWxEnabled;

    /**
     * 分派一条提醒消息
     *
     * @param body 消息体（JSON）
     */
    public void dispatch(String body) {
        String type = readType(body);
        if (!workWxEnabled) {
            log.info("企微推送未开启（antcloud.workwx.enabled=false），消息已确认但不发送：type={}", type);
            return;
        }
        switch (type) {
            case "任务审批提醒":
                handleProcessRemind(body);
                break;
            case "审批流结束回执":
                send(JSONUtil.toBean(body, ProcessReturnDTO.class).toWxMessage(), type);
                break;
            case "待办提醒":
                send(JSONUtil.toBean(body, TodoRemindDTO.class).toWxMessage(), type);
                break;
            case "任务逾期提醒":
                send(JSONUtil.toBean(body, TaskOverdueRemindDTO.class).toWxMessage(), type);
                break;
            case "任务已逾期提醒":
                send(JSONUtil.toBean(body, TaskOvertimeRemindDTO.class).toWxMessage(), type);
                break;
            case "任务指派提醒":
                send(JSONUtil.toBean(body, TaskAssignRemindDTO.class).toWxMessage(), type);
                break;
            default:
                log.warn("未知的提醒类型，已忽略：type={}，body={}", type, body);
                break;
        }
    }

    /**
     * 任务审批提醒：带幂等校验，发送前先确认同一个 taskId + assignee 没处理过
     */
    private void handleProcessRemind(String body) {
        ProcessRemindDTO dto = JSONUtil.toBean(body, ProcessRemindDTO.class);
        String dedupKey = dto.getTaskId() + "_" + dto.getAssignee();
        if (Boolean.TRUE.equals(redisService.hasKey(dedupKey))) {
            log.info("重复消息，直接确认：{}", dedupKey);
            return;
        }
        WxResult result = send(dto.toWxMessage(), "任务审批提醒");
        MessageDataDTO data = new MessageDataDTO();
        data.setMsgCode(result.getResponse_code());
        data.setMsgTime(System.currentTimeMillis());
        data.setWxUserName(dto.getWxUserName());
        redisService.setCacheObject(dedupKey, data, DEDUP_HOURS, TimeUnit.HOURS);
        log.info("提醒已记录，key={}", dedupKey);
    }

    /**
     * 发送企微消息
     *
     * @return 企微返回值
     */
    private WxResult send(WxMessage message, String type) {
        WxResult result = MessageUtils.sendMessage(message);
        log.info("企微消息发送结果：type={}，result={}", type, JSONUtil.toJsonStr(result));
        return result;
    }

    /**
     * 读取消息体里的 type，读不到就返回空串（走默认分支，不让整条链路失败）
     */
    private String readType(String body) {
        try {
            JsonNode node = new ObjectMapper().readTree(body);
            return node == null ? "" : node.path("type").asText("");
        } catch (Exception e) {
            log.warn("消息体不是合法 JSON：{}", body);
            return "";
        }
    }
}
