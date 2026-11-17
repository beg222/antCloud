package com.antcloud.workflow.domain.vo;

import lombok.Data;

/**
 * @author beg
 * @date 2026/11/17
 */
@Data
public class MaterialsApprovalSetVO {

    private String type;
    private String approved;
    private String definitionId;
    private String deploymentId;
}
