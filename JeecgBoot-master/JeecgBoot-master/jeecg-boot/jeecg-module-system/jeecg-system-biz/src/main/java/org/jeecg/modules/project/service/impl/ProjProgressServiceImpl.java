package org.jeecg.modules.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.project.entity.ProjBasicInfo;
import org.jeecg.modules.project.entity.ProjProgress;
import org.jeecg.modules.project.mapper.ProjProgressMapper;
import org.jeecg.modules.project.service.IProjBasicInfoService;
import org.jeecg.modules.project.service.IProjProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Description: 项目进度
 * @Author: jeecg-boot
 * @Date: 2024-02-18
 * @Version: V1.0
 */
@Service
public class ProjProgressServiceImpl extends ServiceImpl<ProjProgressMapper, ProjProgress> implements IProjProgressService {
    
    @Autowired
    private IProjBasicInfoService projBasicInfoService;

    @Autowired
    private IProjActivityMonitorService activityMonitorService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateActiveTime(String projectId) {
        // 更新项目进度表的活跃时间
        ProjProgress progress = new ProjProgress();
        progress.setProjectId(projectId);
        progress.setLastActiveTime(new Date());
        this.save(progress);

        // 更新项目基本信息表的活跃时间
        ProjBasicInfo basicInfo = new ProjBasicInfo();
        basicInfo.setId(projectId);
        basicInfo.setLastActiveTime(new Date());
        return projBasicInfoService.updateById(basicInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateProgressDescription(String projectId, String description, String operator) {
        ProjProgress progress = this.getById(projectId);
        if (progress == null) {
            progress = new ProjProgress();
            progress.setProjectId(projectId);
        }
        
        progress.setProgressDescription(description);
        progress.setLastActiveTime(new Date());
        progress.setActivityStatus("1"); // 设置为活跃状态
        
        boolean success = this.saveOrUpdate(progress);
        
        if (success) {
            // 记录活跃度
            activityMonitorService.recordProjectUpdate(projectId, "2", "更新项目进展描述", operator);
        }
        
        return success;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateVisualProgress(String projectId, Integer percentage, String operator) {
        ProjProgress progress = this.getById(projectId);
        if (progress == null) {
            progress = new ProjProgress();
            progress.setProjectId(projectId);
        }
        
        progress.setVisualProgress(percentage);
        progress.setLastActiveTime(new Date());
        progress.setActivityStatus("1"); // 设置为活跃状态
        
        boolean success = this.saveOrUpdate(progress);
        
        if (success) {
            // 记录活跃度
            activityMonitorService.recordProjectUpdate(projectId, "3", "更新形象进度：" + percentage + "%", operator);
        }
        
        return success;
    }
}
