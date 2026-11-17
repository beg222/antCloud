package com.antcloud.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.antcloud.project.domain.vo.project.ProjectVO;
import com.antcloud.project.domain.vo.project.log.LogVO;
import com.antcloud.project.domain.vo.project.log.ProjectLogVO;
import com.antcloud.project.domain.ProjectLog;

/**
 * @author beg
 * @date 2026/11/17
 */
public interface ProjectLogService extends IService<ProjectLog> {
    void run(LogVO logVO);

    PageInfo<ProjectLogVO> list(ProjectVO projectVO);
}
