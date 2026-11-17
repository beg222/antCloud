package com.antcloud.project.domain.vo.project.file;

/**
 * @author beg
 * @date 2026/11/17
 */
public class FileVO {
    /**
     * 文件id
     */
    private String projectFileId;
    /**
     * 文件地址
     */
    private String fileUrl;
    /**
     * 文件名
     */
    private String fileName;

    public String getProjectFileId() {
        return projectFileId;
    }

    public void setProjectFileId(String projectFileId) {
        this.projectFileId = projectFileId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
