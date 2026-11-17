package com.antcloud.project.service.project;

import com.antcloud.project.domain.vo.project.ProjectReqVO;
import com.antcloud.project.domain.vo.project.ProjectResVO;

import java.util.List;

/**
 * @author beg
 * @date 2026/11/17
 */
public abstract class QueryAbstractExecutor {
    public abstract List<ProjectResVO> query(ProjectReqVO projectReqVO);
}
