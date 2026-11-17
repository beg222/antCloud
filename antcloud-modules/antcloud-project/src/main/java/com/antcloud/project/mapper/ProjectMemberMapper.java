package com.antcloud.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.antcloud.project.domain.ProjectMember;
import com.antcloud.project.domain.vo.project.member.ProjectMemberReqVO;
import com.antcloud.project.domain.vo.project.member.ProjectMemberResVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author beg
 * @date 2026/11/17
 */
public interface ProjectMemberMapper extends BaseMapper<ProjectMember> {

    List<ProjectMemberResVO> searchMember(@Param("data") ProjectMemberReqVO projectMemberReqVO);

    List<ProjectMemberResVO> queryExecutorList(@Param("projectId") String projectId);
    List<ProjectMemberResVO> queryTaskUserList(@Param("taskId") String taskId);
}
