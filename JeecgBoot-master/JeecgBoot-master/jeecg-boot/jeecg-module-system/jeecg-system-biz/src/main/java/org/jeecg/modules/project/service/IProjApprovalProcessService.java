package org.jeecg.modules.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.project.entity.ProjApprovalProcess;

/**
 * @Description: 审批流程
 * @Author: jeecg-boot
 * @Date: 2024-02-18
 * @Version: V1.0
 */
public interface IProjApprovalProcessService extends IService<ProjApprovalProcess> {
    /**
     * 提交审批
     * @param projectId 项目ID
     * @param stageId 环节ID
     * @param currentHandler 当前处理人
     * @return boolean
     */
    boolean submitApproval(String projectId, String stageId, String currentHandler);

    /**
     * 处理审批
     * @param id 流程ID
     * @param handleResult 处理结果
     * @param handleOpinion 处理意见
     * @param handler 处理人
     * @return boolean
     */
    boolean handleApproval(String id, String handleResult, String handleOpinion, String handler);

    /**
     * 撤回审批
     * @param id 流程ID
     * @param operator 操作人
     * @return boolean
     */
    boolean withdrawApproval(String id, String operator);
}
