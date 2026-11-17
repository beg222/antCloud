package com.antcloud.project.service.project;

/**
 * @author beg
 * @date 2026/11/17
 */
public enum QueryProjectEnum {

    MY("my", "queryMyProjectExecutor"),
    COLLECT("collect", "queryMyCollectProjectExecutor"),
    RECYCLE("recycle", "queryRecycleProjectExecutor");
    private final String type;
    private final String beanName;

    QueryProjectEnum(String type, String beanName) {
        this.type = type;
        this.beanName = beanName;
    }

    public String getType() {
        return type;
    }

    public String getBeanName() {
        return beanName;
    }
}
