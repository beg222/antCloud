package com.antcloud.project.service.task;

import com.antcloud.project.domain.vo.project.log.ProjectLogVO;

import java.util.List;

/**
 * @author beg
 * @date 2026/11/17
 */
public abstract class QueryLogAbstractExecutor {

    public abstract List<ProjectLogVO> query(String taskId);
}
