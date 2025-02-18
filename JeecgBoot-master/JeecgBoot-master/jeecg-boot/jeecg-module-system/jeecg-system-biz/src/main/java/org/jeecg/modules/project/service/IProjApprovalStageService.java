package org.jeecg.modules.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.project.entity.ProjApprovalStage;

/**
 * @Description: 项目审批环节
 * @Author: jeecg-boot
 * @Date: 2024-02-18
 * @Version: V1.0
 */
public interface IProjApprovalStageService extends IService<ProjApprovalStage> {
    /**
     * 更新环节状态
     * @param id 环节ID
     * @param status 新状态
     * @param remark 备注说明
     * @param operator 操作人
     * @return boolean
     */
    boolean updateStageStatus(String id, String status, String remark, String operator);

    /**
     * 更新环节办理信息（否状态）
     * @param id 环节ID
     * @param deadline 办理期限
     * @param approvalLevel 办理层级
     * @param fileInfo 组卷情况
     * @param approvalInfo 审批情况
     * @return boolean
     */
    boolean updateStageProcessInfo(String id, Date deadline, String approvalLevel, String fileInfo, String approvalInfo);

    /**
     * 上传审批文件（是状态）
     * @param id 环节ID
     * @param fileId 文件ID
     * @return boolean
     */
    boolean uploadApprovalFile(String id, String fileId);

    /**
     * 上传说明材料（无需办理状态）
     * @param id 环节ID
     * @param fileId 文件ID
     * @param explanation 说明内容
     * @return boolean
     */
    boolean uploadExplanationMaterial(String id, String fileId, String explanation);
}
