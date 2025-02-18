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
import org.jeecg.modules.project.entity.ProjActivityMonitor;
import org.jeecg.modules.project.service.IProjActivityMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 项目活跃度监测
 * @Author: jeecg-boot
 * @Date: 2024-02-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="项目活跃度监测")
@RestController
@RequestMapping("/project/activity")
public class ProjActivityMonitorController extends JeecgController<ProjActivityMonitor, IProjActivityMonitorService> {
    @Autowired
    private IProjActivityMonitorService projActivityMonitorService;

    /**
     * 分页列表查询
     */
    @AutoLog(value = "项目活跃度监测-分页列表查询")
    @ApiOperation(value="项目活跃度监测-分页列表查询", notes="项目活跃度监测-分页列表查询")
    @GetMapping(value = "/list")
    @RequiresPermissions("project:activity:list")
    public Result<?> queryPageList(ProjActivityMonitor projActivityMonitor,
                                 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                 HttpServletRequest req) {
        QueryWrapper<ProjActivityMonitor> queryWrapper = QueryGenerator.initQueryWrapper(projActivityMonitor, req.getParameterMap());
        Page<ProjActivityMonitor> page = new Page<>(pageNo, pageSize);
        IPage<ProjActivityMonitor> pageList = projActivityMonitorService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 记录项目更新
     */
    @AutoLog(value = "项目活跃度监测-记录更新")
    @ApiOperation(value="项目活跃度监测-记录更新", notes="项目活跃度监测-记录更新")
    @PostMapping(value = "/recordUpdate")
    @RequiresPermissions("project:activity:edit")
    public Result<?> recordUpdate(@RequestParam(name="projectId",required=true) String projectId,
                                @RequestParam(name="updateType",required=true) String updateType,
                                @RequestParam(name="updateContent",required=true) String updateContent,
                                HttpServletRequest req) {
        String username = req.getRemoteUser();
        boolean success = projActivityMonitorService.recordProjectUpdate(projectId, updateType, updateContent, username);
        return success ? Result.OK("记录成功！") : Result.error("记录失败！");
    }

    /**
     * 更新活跃状态
     */
    @AutoLog(value = "项目活跃度监测-更新状态")
    @ApiOperation(value="项目活跃度监测-更新状态", notes="项目活跃度监测-更新状态")
    @PostMapping(value = "/updateStatus")
    @RequiresPermissions("project:activity:edit")
    public Result<?> updateStatus(@RequestParam(name="projectId",required=true) String projectId) {
        boolean success = projActivityMonitorService.updateActivityStatus(projectId);
        return success ? Result.OK("更新成功！") : Result.error("更新失败！");
    }

    /**
     * 批量更新所有项目活跃状态
     */
    @AutoLog(value = "项目活跃度监测-批量更新状态")
    @ApiOperation(value="项目活跃度监测-批量更新状态", notes="项目活跃度监测-批量更新状态")
    @PostMapping(value = "/updateAllStatus")
    @RequiresPermissions("project:activity:edit")
    public Result<?> updateAllStatus() {
        boolean success = projActivityMonitorService.updateAllProjectsActivityStatus();
        return success ? Result.OK("更新成功！") : Result.error("更新失败！");
    }

    /**
     * 导出excel
     */
    @RequestMapping(value = "/exportXls")
    @RequiresPermissions("project:activity:exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ProjActivityMonitor projActivityMonitor) {
        return super.exportXls(request, projActivityMonitor, ProjActivityMonitor.class, "项目活跃度监测");
    }

    /**
     * 通过excel导入数据
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    @RequiresPermissions("project:activity:importExcel")
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ProjActivityMonitor.class);
    }
}
