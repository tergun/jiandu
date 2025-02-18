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
}
