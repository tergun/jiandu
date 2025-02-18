package org.jeecg.modules.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.CommonUtils;
import org.jeecg.modules.project.entity.ProjApprovalStage;
import org.jeecg.modules.project.service.IProjApprovalStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Description: 项目审批环节
 * @Author: jeecg-boot
 * @Date: 2024-02-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="项目审批环节")
@RestController
@RequestMapping("/project/approval")
public class ProjApprovalStageController extends JeecgController<ProjApprovalStage, IProjApprovalStageService> {
    @Autowired
    private IProjApprovalStageService projApprovalStageService;

    /**
     * 分页列表查询
     */
    @AutoLog(value = "项目审批环节-分页列表查询")
    @ApiOperation(value="项目审批环节-分页列表查询", notes="项目审批环节-分页列表查询")
    @GetMapping(value = "/list")
    @RequiresPermissions("project:approval:list")
    public Result<?> queryPageList(ProjApprovalStage projApprovalStage,
                                 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                 HttpServletRequest req) {
        QueryWrapper<ProjApprovalStage> queryWrapper = QueryGenerator.initQueryWrapper(projApprovalStage, req.getParameterMap());
        Page<ProjApprovalStage> page = new Page<>(pageNo, pageSize);
        IPage<ProjApprovalStage> pageList = projApprovalStageService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 更新环节状态
     */
    @AutoLog(value = "项目审批环节-更新状态")
    @ApiOperation(value="项目审批环节-更新状态", notes="项目审批环节-更新状态")
    @PostMapping(value = "/updateStatus")
    @RequiresPermissions("project:approval:edit")
    public Result<?> updateStatus(@RequestParam(name="id",required=true) String id,
                                @RequestParam(name="status",required=true) String status,
                                @RequestParam(name="remark",required=false) String remark,
                                HttpServletRequest req) {
        String username = req.getRemoteUser();
        boolean success = projApprovalStageService.updateStageStatus(id, status, remark, username);
        return success ? Result.OK("状态更新成功！") : Result.error("状态更新失败！");
    }

    /**
     * 更新环节办理信息（否状态）
     */
    @AutoLog(value = "项目审批环节-更新办理信息")
    @ApiOperation(value="项目审批环节-更新办理信息", notes="项目审批环节-更新办理信息")
    @PostMapping(value = "/updateProcessInfo")
    @RequiresPermissions("project:approval:edit")
    public Result<?> updateProcessInfo(@RequestParam(name="id",required=true) String id,
                                     @RequestParam(name="deadline",required=true) String deadline,
                                     @RequestParam(name="approvalLevel",required=true) String approvalLevel,
                                     @RequestParam(name="fileInfo",required=false) String fileInfo,
                                     @RequestParam(name="approvalInfo",required=false) String approvalInfo) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date deadlineDate = sdf.parse(deadline);
            boolean success = projApprovalStageService.updateStageProcessInfo(id, deadlineDate, approvalLevel, fileInfo, approvalInfo);
            return success ? Result.OK("办理信息更新成功！") : Result.error("办理信息更新失败！");
        } catch (Exception e) {
            return Result.error("日期格式错误！");
        }
    }

    /**
     * 上传审批文件（是状态）
     */
    @AutoLog(value = "项目审批环节-上传审批文件")
    @ApiOperation(value="项目审批环节-上传审批文件", notes="项目审批环节-上传审批文件")
    @PostMapping(value = "/uploadApprovalFile")
    @RequiresPermissions("project:approval:edit")
    public Result<?> uploadApprovalFile(@RequestParam("file") MultipartFile file,
                                      @RequestParam(name="id",required=true) String id) {
        try {
            String bizPath = "approval/" + id;
            String fileId = CommonUtils.upload(file, bizPath, "");
            boolean success = projApprovalStageService.uploadApprovalFile(id, fileId);
            return success ? Result.OK(fileId) : Result.error("文件上传失败！");
        } catch (Exception e) {
            return Result.error("文件上传失败：" + e.getMessage());
        }
    }

    /**
     * 上传说明材料（无需办理状态）
     */
    @AutoLog(value = "项目审批环节-上传说明材料")
    @ApiOperation(value="项目审批环节-上传说明材料", notes="项目审批环节-上传说明材料")
    @PostMapping(value = "/uploadExplanation")
    @RequiresPermissions("project:approval:edit")
    public Result<?> uploadExplanation(@RequestParam("file") MultipartFile file,
                                     @RequestParam(name="id",required=true) String id,
                                     @RequestParam(name="explanation",required=true) String explanation) {
        try {
            String bizPath = "explanation/" + id;
            String fileId = CommonUtils.upload(file, bizPath, "");
            boolean success = projApprovalStageService.uploadExplanationMaterial(id, fileId, explanation);
            return success ? Result.OK(fileId) : Result.error("材料上传失败！");
        } catch (Exception e) {
            return Result.error("材料上传失败：" + e.getMessage());
        }
    }

    /**
     * 导出excel
     */
    @RequestMapping(value = "/exportXls")
    @RequiresPermissions("project:approval:exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ProjApprovalStage projApprovalStage) {
        return super.exportXls(request, projApprovalStage, ProjApprovalStage.class, "项目审批环节");
    }

    /**
     * 通过excel导入数据
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    @RequiresPermissions("project:approval:importExcel")
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ProjApprovalStage.class);
    }
}
