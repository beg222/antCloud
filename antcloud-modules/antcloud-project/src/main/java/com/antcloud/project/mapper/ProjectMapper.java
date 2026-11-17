package com.antcloud.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.antcloud.project.domain.vo.project.ProjectReqVO;
import com.antcloud.project.domain.vo.project.ProjectResVO;
import com.antcloud.project.domain.vo.project.ProjectVO;
import com.antcloud.project.domain.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author beg
 * @date 2026/11/17
 */
public interface ProjectMapper extends BaseMapper<Project> {

    List<ProjectVO> queryMyProjectList(@Param("userId") Long userId);

    List<ProjectResVO> recycleProjectList();
    ProjectResVO detail(@Param("projectId") String projectId);

    List<ProjectResVO> selectMyProjectList(@Param("data") ProjectReqVO projectReqVO, @Param("userId") Long userId);
    List<ProjectResVO> selectMyCollectProjectList(@Param("data") ProjectReqVO projectReqVO, @Param("userId") Long userId);
}
