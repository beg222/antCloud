package com.laigeoffer.antcloud.web.controller.tool;

import com.laigeoffer.antcloud.common.core.controller.BaseController;
import com.laigeoffer.antcloud.common.core.domain.AjaxResult;
import com.laigeoffer.antcloud.common.core.page.TableDataInfo;
import com.laigeoffer.antcloud.system.domain.AntCloudAsync;
import com.laigeoffer.antcloud.system.service.IAntCloudAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * swagger 接口
 *
 * @author canghe
 */
@RestController
@RequestMapping("/tool/async")
public class AsyncController extends BaseController {

    @Autowired
    IAntCloudAsyncService iAntCloudAsyncService;


    /**
     * 查询异步任务
     * @param antcloudAsync
     * @return {@link TableDataInfo}
     */
    @GetMapping("/list")
    public TableDataInfo list(AntCloudAsync antcloudAsync) {
        startPage();
        antcloudAsync.setCreateBy(getUsername());
        return getDataTable(iAntCloudAsyncService.list(antcloudAsync));
    }

    /**
     * 删除异步任务
     * @param asyncIds
     * @return {@link TableDataInfo}
     */
    @DeleteMapping("/delete/{asyncIds}")
    public AjaxResult delete(@PathVariable String[] asyncIds) {
        iAntCloudAsyncService.delete(asyncIds);
        return AjaxResult.success("success");
    }

    /**
     * 下载附件
     *
     * @param id       包含任务id
     * @param response HttpServletResponse
     */
    @PostMapping("/download")
    public void downloadPost(String id, HttpServletResponse response) {
        iAntCloudAsyncService.downloadFile(id,getUsername(),response);
    }

}
