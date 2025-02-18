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
import org.jeecg.modules.project.entity.ProjApprovalStage;
import org.jeecg.modules.project.service.IProjApprovalStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

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
     * 添加
     */
    @AutoLog(value = "项目审批环节-添加")
    @ApiOperation(value="项目审批环节-添加", notes="项目审批环节-添加")
    @PostMapping(value = "/add")
    @RequiresPermissions("project:approval:add")
    public Result<?> add(@RequestBody ProjApprovalStage projApprovalStage) {
        projApprovalStageService.save(projApprovalStage);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     */
    @AutoLog(value = "项目审批环节-编辑")
    @ApiOperation(value="项目审批环节-编辑", notes="项目审批环节-编辑")
    @PutMapping(value = "/edit")
    @RequiresPermissions("project:approval:edit")
    public Result<?> edit(@RequestBody ProjApprovalStage projApprovalStage) {
        projApprovalStageService.updateById(projApprovalStage);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     */
    @AutoLog(value = "项目审批环节-通过id删除")
    @ApiOperation(value="项目审批环节-通过id删除", notes="项目审批环节-通过id删除")
    @DeleteMapping(value = "/delete")
    @RequiresPermissions("project:approval:delete")
    public Result<?> delete(@RequestParam(name="id",required=true) String id) {
        projApprovalStageService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     */
    @AutoLog(value = "项目审批环节-批量删除")
    @ApiOperation(value="项目审批环节-批量删除", notes="项目审批环节-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    @RequiresPermissions("project:approval:deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        this.projApprovalStageService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功！");
    }

    /**
     * 通过id查询
     */
    @AutoLog(value = "项目审批环节-通过id查询")
    @ApiOperation(value="项目审批环节-通过id查询", notes="项目审批环节-通过id查询")
    @GetMapping(value = "/queryById")
    @RequiresPermissions("project:approval:queryById")
    public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
        ProjApprovalStage projApprovalStage = projApprovalStageService.getById(id);
        return Result.OK(projApprovalStage);
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
