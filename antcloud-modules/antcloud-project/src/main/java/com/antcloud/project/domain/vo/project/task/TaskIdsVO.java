package com.antcloud.project.domain.vo.project.task;


import java.util.List;

/**
 * @author beg
 * @date 2026/11/17
 */
public class TaskIdsVO {
    /**
     * 任务id
     */
    private List<String> taskIdList;

    public List<String> getTaskIdList() {
        return taskIdList;
    }

    public void setTaskIdList(List<String> taskIdList) {
        this.taskIdList = taskIdList;
    }
}
