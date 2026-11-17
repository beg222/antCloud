package com.antcloud.project.domain.vo.project.file;

import java.util.List;

/**
 * @author beg
 * @date 2026/11/17
 */
public class ProjectFileIdsVO {
    /**
     * 项目id
     */
    private String projectId;
    /**
     * 下载内容
     */
    private List<FileVO> fileVOList;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List<FileVO> getFileVOList() {
        return fileVOList;
    }

    public void setFileVOList(List<FileVO> fileVOList) {
        this.fileVOList = fileVOList;
    }
}
