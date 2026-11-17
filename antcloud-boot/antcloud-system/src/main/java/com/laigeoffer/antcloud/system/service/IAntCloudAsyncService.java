package com.laigeoffer.antcloud.system.service;

import com.laigeoffer.antcloud.system.domain.AntCloudAsync;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 异步任务管理 服务层
 *
 * @author canghe
 */
public interface IAntCloudAsyncService {

    /**
     * 创建异步任务记录
     *
     * @param asyncName 异步任务名
     * @param asyncType 异步任务类型
     * @param createBy  创建者
     * @return id
     */
    AntCloudAsync addAsyncJob(String asyncName, String asyncType, String createBy);

    /**
     * 更新任务状态
     * @param antcloudAsync 任务信息
     */
    void updateAsyncJob(AntCloudAsync antcloudAsync);


    /**
     * 查询异步任务信息
     * @param antcloudAsync
     * @return {@link List}<{@link AntCloudAsync}>
     */
    List<AntCloudAsync> list(AntCloudAsync antcloudAsync);

    /**
     * 查询单条异步任务信息
     * @param id id
     * @return {@link List}<{@link AntCloudAsync}>
     */
    AntCloudAsync load(String id);

    /**
     * 删除
     * @param ids ids
     */
    void delete(String[] ids);



    void downloadFile(String id, String user, HttpServletResponse response);
}
