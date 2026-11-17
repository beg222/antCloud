package com.antcloud.base.notice.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author beg
 * @date 2026/11/17
 */
@Data
public class MessageDataDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String msgCode;
    private Long msgTime;
    private String wxUserName;
}
