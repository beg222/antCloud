package com.antcloud.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.antcloud.project.domain.vo.project.ProjectVO;
import com.antcloud.project.domain.ProjectCollection;

/**
 * @author beg
 * @date 2026/11/17
 */
public interface ProjectCollectionService extends IService<ProjectCollection> {

    /**
     * 收藏项目
     * @param projectVO
     * @return
     */
    int collectProject(ProjectVO projectVO);
    /**
     * 取消收藏项目
     * @param projectVO
     * @return
     */
    int cancelCollectProject(ProjectVO projectVO);

}
