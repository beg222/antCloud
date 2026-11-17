package com.laigeoffer.antcloud.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.laigeoffer.antcloud.project.domain.vo.project.ProjectVO;
import com.laigeoffer.antcloud.project.domain.vo.project.log.LogVO;
import com.laigeoffer.antcloud.project.domain.vo.project.log.ProjectLogVO;
import com.laigeoffer.antcloud.project.domain.ProjectLog;

/**
 * @author canghe
 * @date 2022-12-21 11:40
 */
public interface ProjectLogService extends IService<ProjectLog> {
    void run(LogVO logVO);

    PageInfo<ProjectLogVO> list(ProjectVO projectVO);
}
