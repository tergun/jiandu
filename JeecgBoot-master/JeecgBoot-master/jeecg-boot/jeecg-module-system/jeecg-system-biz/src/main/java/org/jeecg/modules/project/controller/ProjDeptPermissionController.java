package org.jeecg.modules.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.project.entity.ProjDeptPermission;
import org.jeecg.modules.project.service.IProjDeptPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @Description: 部门权限
 * @Author: jeecg-boot
 * @Date: 2024-02-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="部门权限")
@RestController
@RequestMapping("/project/dept")
public class ProjDeptPermissionController extends JeecgController<ProjDeptPermission, IProjDeptPermissionService> {
    @Autowired
    private IProjDeptPermissionService projDeptPermissionService;

    /**
     * 分页列表查询
     */
    @AutoLog(value = "部门权限-分页列表查询")
    @ApiOperation(value="部门权限-分页列表查询", notes="部门权限-分页列表查询")
    @GetMapping(value = "/list")
    @RequiresPermissions("project:dept:list")
    @RequiresRoles("project_supervision_admin")
    public Result<?> queryPageList(ProjDeptPermission projDeptPermission,
                                 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                 HttpServletRequest req) {
        QueryWrapper<ProjDeptPermission> queryWrapper = QueryGenerator.initQueryWrapper(projDeptPermission, req.getParameterMap());
        Page<ProjDeptPermission> page = new Page<>(pageNo, pageSize);
        IPage<ProjDeptPermission> pageList = projDeptPermissionService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 授权
     */
    @AutoLog(value = "部门权限-授权")
    @ApiOperation(value="部门权限-授权", notes="部门权限-授权")
    @PostMapping(value = "/grant")
    @RequiresPermissions("project:dept:auth")
    @RequiresRoles("project_supervision_admin")
    public Result<?> grant(@RequestParam(name="deptId",required=true) String deptId,
                          @RequestParam(name="deptName",required=true) String deptName,
                          @RequestParam(name="validStartTime",required=true) String validStartTime,
                          @RequestParam(name="validEndTime",required=true) String validEndTime) {
        boolean success = projDeptPermissionService.grantPermission(deptId, deptName, validStartTime, validEndTime);
        return success ? Result.OK("授权成功！") : Result.error("授权失败！");
    }

    /**
     * 撤销
     */
    @AutoLog(value = "部门权限-撤销")
    @ApiOperation(value="部门权限-撤销", notes="部门权限-撤销")
    @PostMapping(value = "/revoke")
    @RequiresPermissions("project:dept:revoke")
    @RequiresRoles("project_supervision_admin")
    public Result<?> revoke(@RequestParam(name="deptId",required=true) String deptId) {
        boolean success = projDeptPermissionService.revokePermission(deptId);
        return success ? Result.OK("撤销成功！") : Result.error("撤销失败！");
    }

    /**
     * 检查权限
     */
    @AutoLog(value = "部门权限-检查")
    @ApiOperation(value="部门权限-检查", notes="部门权限-检查")
    @GetMapping(value = "/check")
    public Result<?> check(@RequestParam(name="deptId",required=true) String deptId) {
        boolean valid = projDeptPermissionService.checkPermissionValid(deptId);
        return Result.OK(valid);
    }

    /**
     * 导出excel
     */
    @RequestMapping(value = "/exportXls")
    @RequiresPermissions("project:dept:exportXls")
    @RequiresRoles("project_supervision_admin")
    public ModelAndView exportXls(HttpServletRequest request, ProjDeptPermission projDeptPermission) {
        return super.exportXls(request, projDeptPermission, ProjDeptPermission.class, "部门权限");
    }

    /**
     * 通过excel导入数据
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    @RequiresPermissions("project:dept:importExcel")
    @RequiresRoles("project_supervision_admin")
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ProjDeptPermission.class);
    }
}
