package com.antcloud.auth.service;

import com.antcloud.api.system.LogFeignService;
import com.antcloud.base.core.constant.Constants;
import com.antcloud.base.core.constant.SecurityConstants;
import com.antcloud.base.core.core.domain.entity.SysLogininfor;
import com.antcloud.base.core.utils.StringUtils;
import com.antcloud.base.core.utils.ip.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 记录日志方法
 *
 * @author beg
 */
@Component
public class SysRecordLogService {
    @Autowired
    private LogFeignService remoteLogService;

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息内容
     * @return
     */
    public void recordLogininfor(String username, String status, String message) {
        SysLogininfor logininfor = new SysLogininfor();
        logininfor.setUserName(username);
        logininfor.setIpaddr(IpUtils.getIpAddr());
        logininfor.setMsg(message);
        // 日志状态
        if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER)) {
            logininfor.setStatus(Constants.LOGIN_SUCCESS_STATUS);
        } else if (Constants.LOGIN_FAIL.equals(status)) {
            logininfor.setStatus(Constants.LOGIN_FAIL_STATUS);
        }
        remoteLogService.saveLogininfor(logininfor, SecurityConstants.INNER);
    }
}
