package com.antcloud.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.antcloud.project.domain.ProjectFile;
import com.antcloud.project.domain.vo.project.file.ProjectFileIdsVO;
import com.antcloud.project.domain.vo.project.file.ProjectFileReqVO;
import com.antcloud.project.domain.vo.project.file.ProjectFileResVO;

import java.io.IOException;
import java.util.List;

/**
 * @author beg
 * @date 2026/11/17
 */
public interface ProjectFileService extends IService<ProjectFile> {

    PageInfo<ProjectFileResVO> queryFileList(ProjectFileReqVO projectFileReqVO);

    void deleteFileList(ProjectFileIdsVO projectFileIdsVO);

    void rename(ProjectFileReqVO projectFileReqVO);

    void batchDownload(String totalZip, List<String> paths) throws IOException;
}
