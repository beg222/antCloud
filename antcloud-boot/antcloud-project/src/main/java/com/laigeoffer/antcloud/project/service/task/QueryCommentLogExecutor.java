package com.laigeoffer.antcloud.project.service.task;

import com.laigeoffer.antcloud.common.enums.LogTypeEnum;
import com.laigeoffer.antcloud.project.domain.vo.project.log.ProjectLogVO;
import com.laigeoffer.antcloud.project.mapper.ProjectLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author canghe
 * @date 2023-01-09 16:33
 */
@Service("queryCommentLogExecutor")
public class QueryCommentLogExecutor extends QueryLogAbstractExecutor {
    @Autowired
    private ProjectLogMapper projectLogMapper;
    @Override
    public List<ProjectLogVO> query(String taskId) {
        List<ProjectLogVO> list = projectLogMapper.queryCommentLog(taskId);
        list.forEach(projectLogVO -> projectLogVO.setLogTypeName(LogTypeEnum.getStatusNameByStatus(projectLogVO.getLogType())));
        return list;
    }
}
