package com.antcloud.base.notice.mq;

import com.antcloud.base.notice.domain.entity.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

/**
 * 提醒消息发送入口
 * <p>
 * RocketMQTemplate 由 starter 自动装配，单例复用连接；
 * 不再像以前那样每条消息都手工 build 一个 Producer 再 close。
 * 消息体直接传 DTO 即可，DTO 上的 {@code @JsonTypeInfo} 会由 Jackson 写入 type 字段。
 *
 * @author beg
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class NoticePublisher {

    private final ObjectProvider<RocketMQTemplate> templateProvider;

    private final NoticeProperties properties;

    /**
     * 发送一条提醒消息
     *
     * @param payload 提醒 DTO（都继承 Message）
     */
    public void publish(Message payload) {
        RocketMQTemplate template = templateProvider.getIfAvailable();
        if (template == null) {
            log.warn("未配置 rocketmq.name-server，跳过发送：{}", payload.getClass().getSimpleName());
            return;
        }
        String destination = properties.getTopic() + ":" + properties.getTag();
        template.syncSend(destination, payload);
        log.info("提醒消息已发送，destination={}，type={}", destination, payload.getClass().getSimpleName());
    }
}
