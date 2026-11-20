package com.antcloud.base.notice.mq;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 消息提醒配置
 *
 * @author beg
 */
@Data
@Component
@ConfigurationProperties(prefix = "antcloud.notice")
public class NoticeProperties {

    /**
     * 是否启用消费者（只在需要收消息的服务里打开）
     * */
    private boolean consumerEnabled = false;

    /**
     * 提醒消息的 topic
     * */
    private String topic = "antcloud-notice";

    /**
     * 消费组
     * */
    private String consumerGroup = "antcloud-notice-consumer";

    /**
     * 消息 tag，消费端按它过滤
     * */
    private String tag = "WX_MESSAGE";
}
