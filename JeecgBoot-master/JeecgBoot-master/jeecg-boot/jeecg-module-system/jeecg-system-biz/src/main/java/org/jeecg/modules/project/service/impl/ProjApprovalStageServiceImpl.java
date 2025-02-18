package org.jeecg.modules.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.project.entity.ProjApprovalStage;
import org.jeecg.modules.project.mapper.ProjApprovalStageMapper;
import org.jeecg.modules.project.service.IProjApprovalRecordService;
import org.jeecg.modules.project.service.IProjApprovalStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Description: 项目审批环节
 * @Author: jeecg-boot
 * @Date: 2024-02-18
 * @Version: V1.0
 */
@Service
public class ProjApprovalStageServiceImpl extends ServiceImpl<ProjApprovalStageMapper, ProjApprovalStage> implements IProjApprovalStageService {

    @Autowired
    private IProjApprovalRecordService approvalRecordService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateStageStatus(String id, String status, String remark, String operator) {
        ProjApprovalStage stage = this.getById(id);
        if (stage == null) {
            return false;
        }

        String oldStatus = stage.getStatus();
        stage.setStatus(status);
        boolean success = this.updateById(stage);

        if (success) {
            // 记录状态变更
            approvalRecordService.recordStatusChange(stage.getProjectId(), id, oldStatus, status, remark, operator);
        }

        return success;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateStageProcessInfo(String id, Date deadline, String approvalLevel, String fileInfo, String approvalInfo) {
        ProjApprovalStage stage = new ProjApprovalStage();
        stage.setId(id);
        stage.setDeadline(deadline);
        stage.setApprovalLevel(approvalLevel);
        stage.setFileInfo(fileInfo);
        stage.setApprovalInfo(approvalInfo);
        stage.setStatus("0"); // 设置为"否"状态
        
        return this.updateById(stage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean uploadApprovalFile(String id, String fileId) {
        ProjApprovalStage stage = new ProjApprovalStage();
        stage.setId(id);
        stage.setStatus("1"); // 设置为"是"状态
        stage.setFileInfo(fileId); // 保存文件ID
        
        return this.updateById(stage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean uploadExplanationMaterial(String id, String fileId, String explanation) {
        ProjApprovalStage stage = new ProjApprovalStage();
        stage.setId(id);
        stage.setStatus("2"); // 设置为"无需办理"状态
        stage.setFileInfo(fileId); // 保存文件ID
        stage.setApprovalInfo(explanation); // 保存说明内容
        
        return this.updateById(stage);
    }
}
