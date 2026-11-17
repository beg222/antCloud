package com.antcloud.system.domain;

import lombok.Data;

/**
 * OAuth2 用于客户端请求接口的token
 * @author beg
 * @date 2026/11/17
 */
@Data
public class AntCloudOAuth2Token {

    public AntCloudOAuth2Token(){
        this.token_type = "bearer";
        this.scope = "profile";
    }

    /**
     * token
     */
    String access_token;

    /**
     * token 类型
     */
    String token_type;

    /**
     * token 可用的范围
     */
    String scope;


}
