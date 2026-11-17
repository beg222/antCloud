package com.laigeoffer.antcloud.system.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.laigeoffer.antcloud.common.core.domain.entity.SysUser;
import com.laigeoffer.antcloud.common.core.redis.RedisCache;
import com.laigeoffer.antcloud.system.domain.AntCloudOAuth2User;
import com.laigeoffer.antcloud.system.mapper.AntCloudOAuth2AgreeMapper;
import com.laigeoffer.antcloud.system.mapper.AntCloudOAuth2ClientMapper;
import com.laigeoffer.antcloud.system.domain.AntCloudOAuth2Agree;
import com.laigeoffer.antcloud.system.domain.AntCloudOAuth2Client;
import com.laigeoffer.antcloud.system.service.IOAuth2Service;
import com.laigeoffer.antcloud.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class OAuth2ServiceImpl implements IOAuth2Service {

    @Autowired
    private AntCloudOAuth2AgreeMapper antcloudOAuth2AgreeMapper;

    @Autowired
    private AntCloudOAuth2ClientMapper antcloudOAuth2ClientMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysUserService iSysUserService;

    /**
     * redis中code作为key的前缀
     */
    private static final String CODE_TITLE = "auth2_code:";

    /**
     * redis中token作为key的前缀
     */
    private static final String TOKEN_TITLE = "auth2_token:";


    /**
     * 是否已被用户授权登录
     *
     * @param userId   用户id
     * @param clientId 客户端id
     * @return {@link Boolean}
     */
    @Override
    public Boolean isAgree(Long userId, String clientId) {

        QueryWrapper<AntCloudOAuth2Agree> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("client_id", clientId);

        // 数据库中如果有这个授权就返回true
        long count = antcloudOAuth2AgreeMapper.selectCount(queryWrapper);
        return count > 0;
    }

    /**
     * 允许授权登录
     *
     * @param userId   用户id
     * @param clientId 客户端id
     */
    @Override
    public void agree(Long userId, String clientId) {
        if (ObjectUtil.isNotEmpty(clientId)){
            AntCloudOAuth2Agree antcloudOAuth2Agree = new AntCloudOAuth2Agree();
            antcloudOAuth2Agree.setId(IdUtil.fastUUID());

            antcloudOAuth2Agree.setClientId(clientId);
            antcloudOAuth2Agree.setUserId(userId);

            antcloudOAuth2AgreeMapper.insert(antcloudOAuth2Agree);
        }else {
            throw new RuntimeException("ClientID can not null!");
        }
    }

    /**
     * 创建用于授权的用户code
     *
     * @param userId 用户id
     * @return {@link String}
     */
    @Override
    public String createCode(Long userId) {
        String code = IdUtil.simpleUUID();
        // 授权码1分钟过期
        redisCache.setCacheObject(CODE_TITLE+code,userId,1, TimeUnit.MINUTES);
        return code;
    }

    /**
     * 创建用于授权的用户token
     *
     * @param code 用户code
     * @return {@link String}
     */
    @Override
    public String createToken(String code) {
        Long userId = redisCache.getCacheObject(CODE_TITLE+code);
        // 清除code
        redisCache.deleteObject(CODE_TITLE+code);
        if (ObjectUtil.isNotEmpty(userId)){
            String token = IdUtil.randomUUID();
            // token 15分钟过期
            redisCache.setCacheObject(TOKEN_TITLE+token,userId,15, TimeUnit.MINUTES);
            return token;
        }else {
            return null;
        }
    }

    /**
     * 更加token获取用户信息
     * @param token token
     * @return {@link AntCloudOAuth2User}
     */
    @Override
    public AntCloudOAuth2User getUser(String token){
        Long userId = redisCache.getCacheObject(TOKEN_TITLE+token);
        if (ObjectUtil.isNotEmpty(userId)){
            SysUser sysUser = iSysUserService.selectUserById(userId);
            AntCloudOAuth2User antcloudOAuth2User = new AntCloudOAuth2User();
            antcloudOAuth2User.setSub(sysUser.getUserName());
            antcloudOAuth2User.setName(sysUser.getNickName());
            antcloudOAuth2User.setPreferred_username(sysUser.getUserName());
            antcloudOAuth2User.setEmail(sysUser.getEmail());
            antcloudOAuth2User.setUpdated_at(sysUser.getCreateTime().toString());
            return antcloudOAuth2User;
        }else {
            return null;
        }
    }

    /**
     * 获取客户端logo
     *
     * @param clientId clientId
     * @return {@link AntCloudOAuth2User}
     */
    @Override
    public AntCloudOAuth2Client getClientInfo(String clientId) {

        QueryWrapper<AntCloudOAuth2Client> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("client_id", clientId);
        AntCloudOAuth2Client antcloudOAuth2Client =  antcloudOAuth2ClientMapper.selectOne(queryWrapper);
        if (ObjectUtil.isNotEmpty(antcloudOAuth2Client)){
            antcloudOAuth2Client.setClientSecret(null);
        }
        return antcloudOAuth2Client;
    }

    /**
     * 验证客户端的Secret
     *
     * @param clientId
     * @param clientSecret
     * @return {@link Boolean}
     */
    @Override
    public Boolean checkClientSecret(String clientId, String clientSecret) {

        QueryWrapper<AntCloudOAuth2Client> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("client_id", clientId);
        return antcloudOAuth2ClientMapper.selectOne(queryWrapper).getClientSecret().equals(clientSecret);

    }
}
