package com.antcloud.project.service.project;

import com.alibaba.fastjson2.JSON;
import com.antcloud.base.security.utils.SecurityUtils;
import com.antcloud.project.domain.vo.project.ProjectReqVO;
import com.antcloud.project.domain.vo.project.ProjectResVO;
import com.antcloud.project.mapper.ProjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author beg
 * @date 2026/11/17
 */
@Slf4j
@Service("queryMyCollectProjectExecutor")
public class QueryMyCollectProjectExecutor extends QueryAbstractExecutor {
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public List<ProjectResVO> query(ProjectReqVO projectReqVO) {
        log.info("查询我收藏的项目入参:{}", JSON.toJSONString(projectReqVO));
        return projectMapper.selectMyCollectProjectList(projectReqVO, SecurityUtils.getUserId());
    }
}
