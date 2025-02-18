package org.jeecg.modules.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.project.entity.ProjApprovalRecord;
import org.jeecg.modules.project.mapper.ProjApprovalRecordMapper;
import org.jeecg.modules.project.service.IProjApprovalRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Description: 审批环节记录
 * @Author: jeecg-boot
 * @Date: 2024-02-18
 * @Version: V1.0
 */
@Service
public class ProjApprovalRecordServiceImpl extends ServiceImpl<ProjApprovalRecordMapper, ProjApprovalRecord> implements IProjApprovalRecordService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean recordStatusChange(String projectId, String stageId, String oldStatus, String newStatus, String remark, String operator) {
        ProjApprovalRecord record = new ProjApprovalRecord();
        record.setProjectId(projectId);
        record.setStageId(stageId);
        record.setOldStatus(oldStatus);
        record.setNewStatus(newStatus);
        record.setRemark(remark);
        record.setOperator(operator);
        record.setOperateTime(new Date());
        
        return this.save(record);
    }
}
