package com.antcloud.workflow.domain.bo;

import com.antcloud.base.core.core.domain.BaseEntity;
import com.antcloud.base.core.core.validate.AddGroup;
import com.antcloud.base.core.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 流程分类业务对象
 *
 * @author beg
 * @date 2026/11/17
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class WfCategoryBo extends BaseEntity {

    /**
     * 分类ID
     */
    @NotNull(message = "分类ID不能为空", groups = { EditGroup.class })
    private Long categoryId;

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String categoryName;

    /**
     * 分类编码
     */
    @NotBlank(message = "分类编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String code;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
