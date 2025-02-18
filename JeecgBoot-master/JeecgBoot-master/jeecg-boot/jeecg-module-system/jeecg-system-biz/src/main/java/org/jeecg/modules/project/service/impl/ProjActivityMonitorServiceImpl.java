package org.jeecg.modules.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.project.entity.ProjActivityMonitor;
import org.jeecg.modules.project.mapper.ProjActivityMonitorMapper;
import org.jeecg.modules.project.service.IProjActivityMonitorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Description: 项目活跃度监测
 * @Author: jeecg-boot
 * @Date: 2024-02-18
 * @Version: V1.0
 */
@Service
public class ProjActivityMonitorServiceImpl extends ServiceImpl<ProjActivityMonitorMapper, ProjActivityMonitor> implements IProjActivityMonitorService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean recordProjectUpdate(String projectId, String updateType, String updateContent, String operator) {
        // 查找项目现有监测记录
        QueryWrapper<ProjActivityMonitor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        ProjActivityMonitor monitor = this.getOne(queryWrapper);
        
        if (monitor == null) {
            // 创建新记录
            monitor = new ProjActivityMonitor();
            monitor.setProjectId(projectId);
        }
        
        // 更新记录
        monitor.setLastUpdateTime(new Date());
        monitor.setLastUpdateType(updateType);
        monitor.setLastUpdateContent(updateContent);
        monitor.setIsActive(true);
        monitor.setInactiveDays(0);
        
        return monitor.getId() == null ? this.save(monitor) : this.updateById(monitor);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateActivityStatus(String projectId) {
        QueryWrapper<ProjActivityMonitor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", projectId);
        ProjActivityMonitor monitor = this.getOne(queryWrapper);
        
        if (monitor == null) {
            return false;
        }
        
        // 计算不活跃天数
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -7); // 7天前
        Date sevenDaysAgo = calendar.getTime();
        
        boolean isActive = monitor.getLastUpdateTime().after(sevenDaysAgo);
        int inactiveDays = isActive ? 0 : (int)((new Date().getTime() - monitor.getLastUpdateTime().getTime()) / (1000 * 60 * 60 * 24));
        
        // 更新状态
        monitor.setIsActive(isActive);
        monitor.setInactiveDays(inactiveDays);
        
        return this.updateById(monitor);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAllProjectsActivityStatus() {
        List<ProjActivityMonitor> monitors = this.list();
        boolean success = true;
        
        for (ProjActivityMonitor monitor : monitors) {
            success &= updateActivityStatus(monitor.getProjectId());
        }
        
        return success;
    }
}
