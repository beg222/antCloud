package com.antcloud.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.antcloud.project.domain.ProjectFile;
import com.antcloud.project.domain.vo.project.file.ProjectFileReqVO;
import com.antcloud.project.domain.vo.project.file.ProjectFileResVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author beg
 * @date 2026/11/17
 */
public interface ProjectFileMapper extends BaseMapper<ProjectFile> {

    List<ProjectFileResVO> queryFileList(@Param("data") ProjectFileReqVO projectFileReqVO);
    List<ProjectFileResVO> queryProjectFileList(@Param("data") ProjectFileReqVO projectFileReqVO);
    List<ProjectFileResVO> queryTaskFileList(@Param("data") ProjectFileReqVO projectFileReqVO);
    List<ProjectFileResVO> queryTemplateFileList(@Param("data") ProjectFileReqVO projectFileReqVO);
}
