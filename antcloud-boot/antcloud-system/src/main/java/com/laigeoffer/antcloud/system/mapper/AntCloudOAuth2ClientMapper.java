package com.laigeoffer.antcloud.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laigeoffer.antcloud.system.domain.AntCloudOAuth2Client;
import org.apache.ibatis.annotations.Mapper;

/**
 * OAuth2客户端 数据层
 *
 * @author canghe
 */
@Mapper
public interface AntCloudOAuth2ClientMapper extends BaseMapper<AntCloudOAuth2Client> {

}
