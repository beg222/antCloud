package com.antcloud.project.controller;

import com.antcloud.base.core.core.domain.AjaxResult;
import com.antcloud.base.security.annotation.RequiresPermissions;
import com.antcloud.project.domain.vo.project.ProjectVO;
import com.antcloud.project.service.ProjectLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author beg
 * @date 2026/11/17
 */

@RestController
@RequestMapping("/project/log")
public class ProjectLogController {

    @Autowired
    private ProjectLogService projectLogService;

    /**
     * 项目动态
     * @param projectVO
     * @return
     */
    @PostMapping("/list")
    @RequiresPermissions("project:log:list")
    public AjaxResult list(@RequestBody ProjectVO projectVO) {
        return AjaxResult.success(projectLogService.list(projectVO));
    }

}
