package com.laigeoffer.antcloud.project.service.task;

import com.laigeoffer.antcloud.project.domain.vo.project.log.ProjectLogVO;

import java.util.List;

/**
 * @author canghe
 * @date 2023-01-09 16:21
 */
public abstract class QueryLogAbstractExecutor {

    public abstract List<ProjectLogVO> query(String taskId);
}
