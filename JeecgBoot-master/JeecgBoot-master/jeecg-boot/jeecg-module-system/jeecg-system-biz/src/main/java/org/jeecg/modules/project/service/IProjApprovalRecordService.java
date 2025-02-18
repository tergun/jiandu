package org.jeecg.modules.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.project.entity.ProjApprovalRecord;

/**
 * @Description: 审批环节记录
 * @Author: jeecg-boot
 * @Date: 2024-02-18
 * @Version: V1.0
 */
public interface IProjApprovalRecordService extends IService<ProjApprovalRecord> {
    /**
     * 记录状态变更
     * @param projectId 项目ID
     * @param stageId 环节ID
     * @param oldStatus 原状态
     * @param newStatus 新状态
     * @param remark 备注说明
     * @param operator 操作人
     * @return boolean
     */
    boolean recordStatusChange(String projectId, String stageId, String oldStatus, String newStatus, String remark, String operator);
}
