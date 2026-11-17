package com.antcloud.gateway.service;


import com.antcloud.base.core.core.domain.AjaxResult;
import com.antcloud.base.core.exception.user.CaptchaException;

import java.io.IOException;

/**
 * 验证码处理
 *
 * @author beg
 */
public interface ValidateCodeService
{
    /**
     * 生成验证码
     */
    public AjaxResult createCaptcha() throws IOException, CaptchaException;

    /**
     * 校验验证码
     */
    public void checkCaptcha(String key, String value) throws CaptchaException;
}
