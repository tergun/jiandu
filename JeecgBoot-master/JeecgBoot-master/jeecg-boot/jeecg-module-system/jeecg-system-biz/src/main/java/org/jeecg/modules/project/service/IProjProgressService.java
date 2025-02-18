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
}
