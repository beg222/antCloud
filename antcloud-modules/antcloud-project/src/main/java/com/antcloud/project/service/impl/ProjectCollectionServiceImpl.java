package com.antcloud.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.antcloud.base.security.utils.SecurityUtils;
import com.antcloud.project.domain.ProjectCollection;
import com.antcloud.project.domain.vo.project.ProjectVO;
import com.antcloud.project.mapper.ProjectCollectionMapper;
import com.antcloud.project.service.ProjectCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author beg
 * @date 2026/11/17
 */
@Service
public class ProjectCollectionServiceImpl extends ServiceImpl<ProjectCollectionMapper, ProjectCollection> implements ProjectCollectionService {

    @Autowired
    private ProjectCollectionMapper projectCollectionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int collectProject(ProjectVO projectVO) {
        ProjectCollection projectCollection = new ProjectCollection();
        projectCollection.setProjectId(projectVO.getProjectId());
        projectCollection.setUserId(SecurityUtils.getUserId());
        projectCollection.setCreatedBy(SecurityUtils.getUsername());
        projectCollection.setCreatedTime(new Date());
        projectCollection.setUpdatedBy(SecurityUtils.getUsername());
        projectCollection.setUpdatedTime(new Date());
        return projectCollectionMapper.insert(projectCollection);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int cancelCollectProject(ProjectVO projectVO) {
        LambdaQueryWrapper<ProjectCollection> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProjectCollection::getProjectId, projectVO.getProjectId()).eq(ProjectCollection::getUserId, SecurityUtils.getUserId());
        return projectCollectionMapper.delete(queryWrapper);
    }
}
