package org.jeecg.modules.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.project.entity.ProjProgress;

/**
 * @Description: 项目进度
 * @Author: jeecg-boot
 * @Date: 2024-02-18
 * @Version: V1.0
 */
public interface IProjProgressService extends IService<ProjProgress> {
    /**
     * 更新项目活跃时间
     * @param projectId 项目ID
     * @return boolean
     */
    boolean updateActiveTime(String projectId);

    /**
     * 更新项目进展描述
     * @param projectId 项目ID
     * @param description 进展描述
     * @param operator 操作人
     * @return boolean
     */
    boolean updateProgressDescription(String projectId, String description, String operator);

    /**
     * 更新形象进度
     * @param projectId 项目ID
     * @param percentage 进度百分比
     * @param operator 操作人
     * @return boolean
     */
    boolean updateVisualProgress(String projectId, Integer percentage, String operator);
}
