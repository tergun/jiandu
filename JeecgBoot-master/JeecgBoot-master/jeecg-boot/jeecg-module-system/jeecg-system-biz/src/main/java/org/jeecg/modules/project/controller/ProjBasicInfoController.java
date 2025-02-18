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
import org.jeecg.modules.project.entity.ProjBasicInfo;
import org.jeecg.modules.project.service.IProjBasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @Description: 项目基本信息
 * @Author: jeecg-boot
 * @Date: 2024-02-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="项目基本信息")
@RestController
@RequestMapping("/project/basic")
public class ProjBasicInfoController extends JeecgController<ProjBasicInfo, IProjBasicInfoService> {
    @Autowired
    private IProjBasicInfoService projBasicInfoService;

    /**
     * 分页列表查询
     */
    @AutoLog(value = "项目基本信息-分页列表查询")
    @ApiOperation(value="项目基本信息-分页列表查询", notes="项目基本信息-分页列表查询")
    @GetMapping(value = "/list")
    @RequiresPermissions("project:basic:list")
    public Result<?> queryPageList(ProjBasicInfo projBasicInfo,
                                 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                 HttpServletRequest req) {
        QueryWrapper<ProjBasicInfo> queryWrapper = QueryGenerator.initQueryWrapper(projBasicInfo, req.getParameterMap());
        Page<ProjBasicInfo> page = new Page<>(pageNo, pageSize);
        IPage<ProjBasicInfo> pageList = projBasicInfoService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     */
    @AutoLog(value = "项目基本信息-添加")
    @ApiOperation(value="项目基本信息-添加", notes="项目基本信息-添加")
    @PostMapping(value = "/add")
    @RequiresPermissions("project:basic:add")
    public Result<?> add(@RequestBody ProjBasicInfo projBasicInfo) {
        projBasicInfoService.save(projBasicInfo);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     */
    @AutoLog(value = "项目基本信息-编辑")
    @ApiOperation(value="项目基本信息-编辑", notes="项目基本信息-编辑")
    @PutMapping(value = "/edit")
    @RequiresPermissions("project:basic:edit")
    public Result<?> edit(@RequestBody ProjBasicInfo projBasicInfo) {
        projBasicInfoService.updateById(projBasicInfo);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     */
    @AutoLog(value = "项目基本信息-通过id删除")
    @ApiOperation(value="项目基本信息-通过id删除", notes="项目基本信息-通过id删除")
    @DeleteMapping(value = "/delete")
    @RequiresPermissions("project:basic:delete")
    public Result<?> delete(@RequestParam(name="id",required=true) String id) {
        projBasicInfoService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     */
    @AutoLog(value = "项目基本信息-批量删除")
    @ApiOperation(value="项目基本信息-批量删除", notes="项目基本信息-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    @RequiresPermissions("project:basic:deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        this.projBasicInfoService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功！");
    }

    /**
     * 通过id查询
     */
    @AutoLog(value = "项目基本信息-通过id查询")
    @ApiOperation(value="项目基本信息-通过id查询", notes="项目基本信息-通过id查询")
    @GetMapping(value = "/queryById")
    @RequiresPermissions("project:basic:queryById")
    public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
        ProjBasicInfo projBasicInfo = projBasicInfoService.getById(id);
        return Result.OK(projBasicInfo);
    }

    /**
     * 导出excel
     */
    @RequestMapping(value = "/exportXls")
    @RequiresPermissions("project:basic:exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ProjBasicInfo projBasicInfo) {
        return super.exportXls(request, projBasicInfo, ProjBasicInfo.class, "项目基本信息");
    }

    /**
     * 通过excel导入数据
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    @RequiresPermissions("project:basic:importExcel")
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ProjBasicInfo.class);
    }
}
