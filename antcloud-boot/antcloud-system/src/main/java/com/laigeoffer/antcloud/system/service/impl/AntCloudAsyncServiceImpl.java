package com.laigeoffer.antcloud.system.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.log.LogFactory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.laigeoffer.antcloud.common.utils.file.FileUtils;
import com.laigeoffer.antcloud.system.domain.AntCloudAsync;
import com.laigeoffer.antcloud.system.mapper.AntCloudAsyncMapper;
import com.laigeoffer.antcloud.system.service.IAntCloudAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@Service
public class AntCloudAsyncServiceImpl implements IAntCloudAsyncService {

    @Autowired
    AntCloudAsyncMapper antcloudAsyncMapper;

    /**
     * 创建异步任务记录
     *
     * @param asyncName 异步任务名
     * @param asyncType 异步任务类型
     * @param createBy  创建者
     */
    @Override
    public AntCloudAsync addAsyncJob(String asyncName, String asyncType, String createBy) {
        AntCloudAsync antcloudAsync = new AntCloudAsync(asyncName,asyncType,createBy);
        antcloudAsyncMapper.insert(antcloudAsync);
        return antcloudAsync;
    }

    /**
     * 更新任务状态
     *
     * @param antcloudAsync 任务信息
     */
    @Override
    public void updateAsyncJob(AntCloudAsync antcloudAsync) {
        antcloudAsync.setUpdateTime(new Date());
        antcloudAsyncMapper.updateById(antcloudAsync);
    }

    /**
     * 查询异步任务信息
     *
     * @param antcloudAsync
     * @return {@link List}<{@link AntCloudAsync}>
     */
    @Override
    public List<AntCloudAsync> list(AntCloudAsync antcloudAsync) {
        antcloudAsync.setAsyncLog(null);
        QueryWrapper<AntCloudAsync> queryWrapper = new QueryWrapper<>();
        // 如果 asyncName 字段不为空，则添加模糊查询条件
        if (antcloudAsync.getAsyncName() != null && !antcloudAsync.getAsyncName().isEmpty()) {
            // 添加模糊查询条件
            queryWrapper.like("async_name", "%"+ antcloudAsync.getAsyncName()+"%");
        }
        if (antcloudAsync.getAsyncType() != null && !antcloudAsync.getAsyncType().isEmpty()) {
            // 添加精确查询条件
            queryWrapper.eq("async_type", antcloudAsync.getAsyncType());
        }
        if (antcloudAsync.getAsyncStatus() != null) {
            // 添加精确查询条件
            queryWrapper.eq("async_status", antcloudAsync.getAsyncStatus());
        }
        if (antcloudAsync.getCreateBy() != null && !antcloudAsync.getCreateBy().isEmpty()) {
            // 添加精确查询条件
            queryWrapper.eq("create_by", antcloudAsync.getCreateBy());
        }
        if (antcloudAsync.getId() != null && !antcloudAsync.getId().isEmpty()) {
            // 添加精确查询条件
            queryWrapper.eq("id", antcloudAsync.getId());
        }
        queryWrapper.orderByDesc("update_time"); // 添加根据 updateTime 字段的倒序排序
        // 查询所有记录
        return antcloudAsyncMapper.selectList(queryWrapper);
    }

    /**
     * 查询单条异步任务信息
     *
     * @param id id
     * @return {@link List}<{@link AntCloudAsync}>
     */
    @Override
    public AntCloudAsync load(String id) {
        AntCloudAsync antcloudAsync = new AntCloudAsync();
        antcloudAsync.setId(id);
        List<AntCloudAsync> antcloudAsyncs = list(antcloudAsync);
        if (!antcloudAsyncs.isEmpty()){
            return antcloudAsyncs.get(0);
        }else {
            return null;
        }
    }


    /**
     * 删除
     * @param ids ids
     */
    @Transactional
    @Override
    public void delete(String[] ids) {
        for (String id:ids){
            AntCloudAsync antcloudAsync = new AntCloudAsync();
            antcloudAsync.setId(id);
            delete(antcloudAsync);
        }
    }

    /**
     * 下载附件
     *
     * @param id       任务id
     * @param response Http
     */
    @Override
    public void downloadFile(String id, String user, HttpServletResponse response) {
        AntCloudAsync antcloudAsync = load(id);
        if (antcloudAsync ==null){
            throw new RuntimeException("不存在的任务");
        }else if (antcloudAsync.getFile()==null|| antcloudAsync.getFile().isEmpty()){
            throw new RuntimeException("不存在的附件");
        }else if (!antcloudAsync.getCreateBy().equals(user)){
            throw new RuntimeException("非法操作");
        }else {
            // 单文件下载
            try {
                FileUtils.setAttachmentResponseHeader(response, antcloudAsync.getFile());
                FileUtils.writeBytes(antcloudAsync.getFile(), response);
            } catch (UnsupportedEncodingException e) {
                LogFactory.get().error(e);
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void delete(AntCloudAsync antcloudAsync) {
        antcloudAsync = antcloudAsyncMapper.selectById(antcloudAsync.getId());

        if (antcloudAsync ==null){
            return;
        }

        if (antcloudAsync.getAsyncStatus()==0){
            throw new RuntimeException("任务进行中禁止删除");
        }

        // 文件路径
        String filePath = antcloudAsync.getFile();
        if (ObjectUtil.isNotEmpty(filePath)){
            // 删除文件
            boolean result = new File(filePath).delete();

            if (result) {
                LogFactory.get().info("任务["+ antcloudAsync.getId()+"]文件已被成功删除");
            } else {
                LogFactory.get().warn("任务["+ antcloudAsync.getId()+"]文件删除失败，可能文件不存在或无法删除");
            }
        }
        antcloudAsyncMapper.deleteById(antcloudAsync);
    }

}
