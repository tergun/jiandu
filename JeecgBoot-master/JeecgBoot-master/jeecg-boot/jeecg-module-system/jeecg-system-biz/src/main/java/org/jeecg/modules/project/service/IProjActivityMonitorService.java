package org.jeecg.modules.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.project.entity.ProjActivityMonitor;

/**
 * @Description: 项目活跃度监测
 * @Author: jeecg-boot
 * @Date: 2024-02-18
 * @Version: V1.0
 */
public interface IProjActivityMonitorService extends IService<ProjActivityMonitor> {
    /**
     * 记录项目更新
     * @param projectId 项目ID
     * @param updateType 更新类型
     * @param updateContent 更新内容
     * @param operator 操作人
     * @return boolean
     */
    boolean recordProjectUpdate(String projectId, String updateType, String updateContent, String operator);

    /**
     * 更新项目活跃状态
     * @param projectId 项目ID
     * @return boolean
     */
    boolean updateActivityStatus(String projectId);

    /**
     * 批量更新所有项目活跃状态
     * @return boolean
     */
    boolean updateAllProjectsActivityStatus();
}
