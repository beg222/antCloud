package com.antcloud.project.domain.vo.project.task;


/**
 * @author beg
 * @date 2026/11/17
 */

public class TaskStatisticsByDateVO {
    /**
     * 时间
     */
    private String date;
    /**
     * 总数
     */
    private Integer total;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
