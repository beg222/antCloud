package com.antcloud.project.service.file;

import com.antcloud.base.core.config.AntCloudConfig;
import com.antcloud.base.core.core.domain.model.LoginUser;
import com.antcloud.base.core.exception.ServiceException;
import com.antcloud.base.core.utils.file.MimeTypeUtils;
import com.antcloud.project.domain.Project;
import com.antcloud.project.domain.vo.project.file.FileVO;
import com.antcloud.project.mapper.ProjectMapper;
import com.antcloud.project.utils.ProjectFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author beg
 * @date 2026/11/17
 */
@Service("uploadCoverFileExecutor")
@Slf4j
public class UploadCoverFileExecutor extends UploadAbstractExecutor {
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FileVO upload(LoginUser user, MultipartFile file, String id) throws Exception {
        log.info("封面上传的项目id:{}", id);
        String coverPath = ProjectFileUtil.uploadProjectFile(AntCloudConfig.getProjectCoverPath(), file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        if (StringUtils.isBlank(coverPath)) {
            throw new ServiceException("上传文件异常，请联系管理员");
        }
        Project project = projectMapper.selectById(id);
        project.setCover(coverPath);
        projectMapper.updateById(project);
        FileVO fileVO = new FileVO();
        fileVO.setFileUrl(coverPath);
        fileVO.setFileName(file.getOriginalFilename());
        return fileVO;
    }
}
