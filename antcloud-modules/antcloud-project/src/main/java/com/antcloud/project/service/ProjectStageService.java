package com.antcloud.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.antcloud.project.domain.vo.project.ProjectVO;
import com.antcloud.project.domain.ProjectStage;
import com.antcloud.project.domain.vo.project.stage.ProjectStageVO;

import java.util.List;

/**
 * @author beg
 * @date 2026/11/17
 */
public interface ProjectStageService extends IService<ProjectStage> {
    List<ProjectStageVO> list(ProjectVO projectVO);
    void delete(ProjectStageVO projectStageVO);
    void add(ProjectStageVO projectStageVO);
    void edit(ProjectStageVO projectStageVO);
    boolean selectTaskByStageId(String stageId);
}
