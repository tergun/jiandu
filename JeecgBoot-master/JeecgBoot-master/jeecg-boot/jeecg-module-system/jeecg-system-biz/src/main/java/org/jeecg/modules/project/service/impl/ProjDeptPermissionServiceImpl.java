package org.jeecg.modules.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.project.entity.ProjDeptPermission;
import org.jeecg.modules.project.mapper.ProjDeptPermissionMapper;
import org.jeecg.modules.project.service.IProjDeptPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 部门权限
 * @Author: jeecg-boot
 * @Date: 2024-02-18
 * @Version: V1.0
 */
@Service
public class ProjDeptPermissionServiceImpl extends ServiceImpl<ProjDeptPermissionMapper, ProjDeptPermission> implements IProjDeptPermissionService {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean grantPermission(String deptId, String deptName, String validStartTime, String validEndTime) {
        try {
            // 先撤销现有权限
            revokePermission(deptId);

            // 创建新权限
            ProjDeptPermission permission = new ProjDeptPermission();
            permission.setDeptId(deptId);
            permission.setDeptName(deptName);
            permission.setValidStartTime(DATE_FORMAT.parse(validStartTime));
            permission.setValidEndTime(DATE_FORMAT.parse(validEndTime));
            permission.setStatus("1"); // 有效状态

            return this.save(permission);
        } catch (ParseException e) {
            log.error("日期格式错误", e);
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean revokePermission(String deptId) {
        QueryWrapper<ProjDeptPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_id", deptId)
                   .eq("status", "1");

        ProjDeptPermission permission = new ProjDeptPermission();
        permission.setStatus("0"); // 已撤销状态

        return this.update(permission, queryWrapper);
    }

    @Override
    public boolean checkPermissionValid(String deptId) {
        QueryWrapper<ProjDeptPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_id", deptId)
                   .eq("status", "1")
                   .le("valid_start_time", new Date())
                   .ge("valid_end_time", new Date());

        return this.count(queryWrapper) > 0;
    }
}
