package com.antcloud.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.antcloud.project.domain.vo.project.log.ProjectLogVO;
import com.antcloud.project.domain.ProjectLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author beg
 * @date 2026/11/17
 */
public interface ProjectLogMapper extends BaseMapper<ProjectLog> {

    List<ProjectLogVO> queryLogList(@Param("projectId") String projectId);
    List<ProjectLogVO> queryAllLog(@Param("taskId") String taskId);
    List<ProjectLogVO> queryCommentLog(@Param("taskId") String taskId);
    List<ProjectLogVO> queryDeliverableLog(@Param("taskId") String taskId);
    List<ProjectLogVO> queryTrendsLog(@Param("taskId") String taskId);
}
