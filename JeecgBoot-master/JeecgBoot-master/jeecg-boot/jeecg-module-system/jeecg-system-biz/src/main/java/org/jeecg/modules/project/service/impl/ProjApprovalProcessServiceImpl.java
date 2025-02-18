package org.jeecg.modules.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.project.entity.ProjApprovalProcess;
import org.jeecg.modules.project.entity.ProjApprovalStage;
import org.jeecg.modules.project.mapper.ProjApprovalProcessMapper;
import org.jeecg.modules.project.service.IProjApprovalProcessService;
import org.jeecg.modules.project.service.IProjApprovalStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Description: 审批流程
 * @Author: jeecg-boot
 * @Date: 2024-02-18
 * @Version: V1.0
 */
@Service
public class ProjApprovalProcessServiceImpl extends ServiceImpl<ProjApprovalProcessMapper, ProjApprovalProcess> implements IProjApprovalProcessService {

    @Autowired
    private IProjApprovalStageService approvalStageService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean submitApproval(String projectId, String stageId, String currentHandler) {
        // 创建新的审批流程
        ProjApprovalProcess process = new ProjApprovalProcess();
        process.setProjectId(projectId);
        process.setStageId(stageId);
        process.setProcessStatus("0"); // 待提交状态
        process.setCurrentHandler(currentHandler);
        process.setSubmitTime(new Date());
        
        boolean success = this.save(process);
        
        if (success) {
            // 更新环节状态
            ProjApprovalStage stage = new ProjApprovalStage();
            stage.setId(stageId);
            stage.setProcessId(process.getId());
            stage.setHandleUser(currentHandler);
            approvalStageService.updateById(stage);
        }
        
        return success;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean handleApproval(String id, String handleResult, String handleOpinion, String handler) {
        ProjApprovalProcess process = this.getById(id);
        if (process == null) {
            return false;
        }

        // 更新处理结果
        process.setHandleResult(handleResult);
        process.setHandleOpinion(handleOpinion);
        process.setHandleTime(new Date());
        process.setProcessStatus(handleResult); // 使用处理结果作为流程状态
        
        boolean success = this.updateById(process);
        
        if (success) {
            // 更新环节状态
            ProjApprovalStage stage = new ProjApprovalStage();
            stage.setId(process.getStageId());
            stage.setStatus(handleResult.equals("2") ? "1" : "0"); // 如果审批通过，设置为"是"状态，否则为"否"状态
            approvalStageService.updateById(stage);
        }
        
        return success;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean withdrawApproval(String id, String operator) {
        ProjApprovalProcess process = this.getById(id);
        if (process == null) {
            return false;
        }

        // 更新流程状态为已撤回
        process.setProcessStatus("4");
        process.setHandleTime(new Date());
        process.setHandleOpinion("已撤回");
        
        boolean success = this.updateById(process);
        
        if (success) {
            // 更新环节状态
            ProjApprovalStage stage = new ProjApprovalStage();
            stage.setId(process.getStageId());
            stage.setStatus("0"); // 设置为"否"状态
            approvalStageService.updateById(stage);
        }
        
        return success;
    }
}
