package org.jeecg.modules.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.project.entity.ProjDeptPermission;

/**
 * @Description: 部门权限
 * @Author: jeecg-boot
 * @Date: 2024-02-18
 * @Version: V1.0
 */
public interface IProjDeptPermissionService extends IService<ProjDeptPermission> {
    /**
     * 授权部门权限
     * @param deptId 部门ID
     * @param deptName 部门名称
     * @param validStartTime 有效期开始时间
     * @param validEndTime 有效期结束时间
     * @return boolean
     */
    boolean grantPermission(String deptId, String deptName, String validStartTime, String validEndTime);

    /**
     * 撤销部门权限
     * @param deptId 部门ID
     * @return boolean
     */
    boolean revokePermission(String deptId);

    /**
     * 检查部门权限是否有效
     * @param deptId 部门ID
     * @return boolean
     */
    boolean checkPermissionValid(String deptId);
}
