package com.antcloud.base.notice.mq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * 提醒消息消费者
 * <p>
 * 订阅关系全部写在注解里，不再手工创建 PushConsumer。
 * 只有把 {@code antcloud.notice.consumer-enabled} 打开的服务才会启动消费者。
 *
 * @author beg
 */
@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "antcloud.notice", name = "consumer-enabled", havingValue = "true")
@RocketMQMessageListener(
        topic = "${antcloud.notice.topic}",
        consumerGroup = "${antcloud.notice.consumer-group}",
        selectorExpression = "${antcloud.notice.tag}")
public class WxNoticeConsumer implements RocketMQListener<String> {

    private final WxNoticeDispatcher dispatcher;

    @Override
    public void onMessage(String body) {
        log.info("收到提醒消息，长度={}", body == null ? 0 : body.length());
        dispatcher.dispatch(body);
    }
}
