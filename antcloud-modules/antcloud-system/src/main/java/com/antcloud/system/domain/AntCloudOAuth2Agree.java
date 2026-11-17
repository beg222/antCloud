package com.antcloud.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * OAuth2 用户已统一登陆客户端
 * @author beg
 * @date 2026/11/17
 */
@Data
@TableName("antcloud_oauth2_agree")
public class AntCloudOAuth2Agree {

    String id;

    /**
     * 客户端名称
     */
    Long userId;

    /**
     * 客户端id
     */
    String clientId;

}
