package com.antcloud.base.notice.service;

import cn.hutool.json.JSONUtil;
import com.antcloud.base.core.config.redis.RedisService;
import com.antcloud.base.notice.domain.dto.MessageDataDTO;
import com.antcloud.base.notice.domain.dto.ProcessWxMessageStateUpdateDTO;
import com.antcloud.base.notice.domain.entity.WxResult;
import com.antcloud.base.notice.enums.ButtonStateEnum;
import com.antcloud.base.notice.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 企微消息卡片维护
 * <p>
 * 审批结束后，把卡片上的按钮置成「已完成」，并清掉 Redis 里的记录。
 *
 * @author beg
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WxMessageService {

    private final RedisService redisService;

    /**
     * 清理（收尾）一条已发出的提醒
     *
     * @param instId 发送时记录的 key
     */
    public void cleanMessage(String instId) {
        log.info("清理消息：{}", instId);
        try {
            MessageDataDTO data = redisService.getCacheObject(instId);
            if (data != null) {
                ProcessWxMessageStateUpdateDTO update = new ProcessWxMessageStateUpdateDTO();
                update.setAtall(1);
                update.setResponse_code(data.getMsgCode());
                update.getButton().setReplace_name(ButtonStateEnum.FINISH);
                WxResult result = MessageUtils.updateMessage(update);
                log.info("企微卡片更新结果：{}", JSONUtil.toJsonStr(result));
                redisService.deleteObject(instId);
            }
        } catch (Exception e) {
            log.info("消息不存在或已清理：{}", instId);
        }
    }
}
