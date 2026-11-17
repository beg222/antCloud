package com.antcloud.workflow.mapper;

import com.antcloud.base.core.core.domain.entity.SysDept;
import com.antcloud.base.core.core.domain.entity.SysRole;
import com.antcloud.base.core.core.domain.entity.SysUser;
import com.antcloud.base.core.mapper.BaseMapperPlus;
import com.antcloud.workflow.domain.WfCopy;
import com.antcloud.workflow.domain.vo.WfCopyVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流程抄送Mapper接口
 *
 * @author beg
 * @date 2026/11/17
 */
@Mapper
public interface WfCopyMapper extends BaseMapperPlus<WfCopyMapper, WfCopy, WfCopyVo> {

    SysUser selectUserById(@Param("userId") Long userId);
    SysRole selectRoleById(@Param("roleId") Long roleId);
    SysDept selectDeptById(@Param("deptId") Long deptId);
    List<Long> selectUserIdsByRoleId(@Param("roleId") Long roleId);
    List<Long> selectUserIds(@Param("deptIds") List<String> deptIds);

}
