package com.antcloud.project.service.file;

import com.antcloud.base.core.core.domain.model.LoginUser;
import com.antcloud.project.domain.vo.project.file.FileVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author beg
 * @date 2026/11/17
 */
public abstract class UploadAbstractExecutor {
    public abstract FileVO upload(LoginUser user, MultipartFile file, String id) throws Exception;
}
