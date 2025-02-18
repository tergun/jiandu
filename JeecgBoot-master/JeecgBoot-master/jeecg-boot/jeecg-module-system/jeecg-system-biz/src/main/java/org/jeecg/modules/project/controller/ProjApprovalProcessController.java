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
import org.jeecg.modules.project.entity.ProjApprovalProcess;
import org.jeecg.modules.project.service.IProjApprovalProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 审批流程
 * @Author: jeecg-boot
 * @Date: 2024-02-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="审批流程")
@RestController
@RequestMapping("/project/process")
public class ProjApprovalProcessController extends JeecgController<ProjApprovalProcess, IProjApprovalProcessService> {
    @Autowired
    private IProjApprovalProcessService projApprovalProcessService;

    /**
     * 分页列表查询
     */
    @AutoLog(value = "审批流程-分页列表查询")
    @ApiOperation(value="审批流程-分页列表查询", notes="审批流程-分页列表查询")
    @GetMapping(value = "/list")
    @RequiresPermissions("project:process:list")
    public Result<?> queryPageList(ProjApprovalProcess projApprovalProcess,
                                 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                 HttpServletRequest req) {
        QueryWrapper<ProjApprovalProcess> queryWrapper = QueryGenerator.initQueryWrapper(projApprovalProcess, req.getParameterMap());
        Page<ProjApprovalProcess> page = new Page<>(pageNo, pageSize);
        IPage<ProjApprovalProcess> pageList = projApprovalProcessService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 提交审批
     */
    @AutoLog(value = "审批流程-提交审批")
    @ApiOperation(value="审批流程-提交审批", notes="审批流程-提交审批")
    @PostMapping(value = "/submit")
    @RequiresPermissions("project:process:submit")
    public Result<?> submit(@RequestParam(name="projectId",required=true) String projectId,
                          @RequestParam(name="stageId",required=true) String stageId,
                          HttpServletRequest req) {
        String username = req.getRemoteUser();
        boolean success = projApprovalProcessService.submitApproval(projectId, stageId, username);
        return success ? Result.OK("提交成功！") : Result.error("提交失败！");
    }

    /**
     * 处理审批
     */
    @AutoLog(value = "审批流程-处理审批")
    @ApiOperation(value="审批流程-处理审批", notes="审批流程-处理审批")
    @PostMapping(value = "/handle")
    @RequiresPermissions("project:process:handle")
    public Result<?> handle(@RequestParam(name="id",required=true) String id,
                          @RequestParam(name="handleResult",required=true) String handleResult,
                          @RequestParam(name="handleOpinion",required=false) String handleOpinion,
                          HttpServletRequest req) {
        String username = req.getRemoteUser();
        boolean success = projApprovalProcessService.handleApproval(id, handleResult, handleOpinion, username);
        return success ? Result.OK("处理成功！") : Result.error("处理失败！");
    }

    /**
     * 撤回审批
     */
    @AutoLog(value = "审批流程-撤回审批")
    @ApiOperation(value="审批流程-撤回审批", notes="审批流程-撤回审批")
    @PostMapping(value = "/withdraw")
    @RequiresPermissions("project:process:withdraw")
    public Result<?> withdraw(@RequestParam(name="id",required=true) String id,
                            HttpServletRequest req) {
        String username = req.getRemoteUser();
        boolean success = projApprovalProcessService.withdrawApproval(id, username);
        return success ? Result.OK("撤回成功！") : Result.error("撤回失败！");
    }

    /**
     * 导出excel
     */
    @RequestMapping(value = "/exportXls")
    @RequiresPermissions("project:process:exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ProjApprovalProcess projApprovalProcess) {
        return super.exportXls(request, projApprovalProcess, ProjApprovalProcess.class, "审批流程");
    }

    /**
     * 通过excel导入数据
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    @RequiresPermissions("project:process:importExcel")
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ProjApprovalProcess.class);
    }
}
