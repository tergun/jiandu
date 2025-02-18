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
import org.jeecg.modules.project.entity.ProjProgress;
import org.jeecg.modules.project.service.IProjProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @Description: 项目进度
 * @Author: jeecg-boot
 * @Date: 2024-02-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="项目进度")
@RestController
@RequestMapping("/project/progress")
public class ProjProgressController extends JeecgController<ProjProgress, IProjProgressService> {
    @Autowired
    private IProjProgressService projProgressService;

    /**
     * 分页列表查询
     */
    @AutoLog(value = "项目进度-分页列表查询")
    @ApiOperation(value="项目进度-分页列表查询", notes="项目进度-分页列表查询")
    @GetMapping(value = "/list")
    @RequiresPermissions("project:progress:list")
    public Result<?> queryPageList(ProjProgress projProgress,
                                 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                 HttpServletRequest req) {
        QueryWrapper<ProjProgress> queryWrapper = QueryGenerator.initQueryWrapper(projProgress, req.getParameterMap());
        Page<ProjProgress> page = new Page<>(pageNo, pageSize);
        IPage<ProjProgress> pageList = projProgressService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 更新项目进展描述
     */
    @AutoLog(value = "项目进度-更新进展描述")
    @ApiOperation(value="项目进度-更新进展描述", notes="项目进度-更新进展描述")
    @PostMapping(value = "/updateDescription")
    @RequiresPermissions("project:progress:edit")
    public Result<?> updateDescription(@RequestParam(name="projectId",required=true) String projectId,
                                     @RequestParam(name="description",required=true) String description,
                                     HttpServletRequest req) {
        String username = req.getRemoteUser();
        boolean success = projProgressService.updateProgressDescription(projectId, description, username);
        return success ? Result.OK("更新成功！") : Result.error("更新失败！");
    }

    /**
     * 更新形象进度
     */
    @AutoLog(value = "项目进度-更新形象进度")
    @ApiOperation(value="项目进度-更新形象进度", notes="项目进度-更新形象进度")
    @PostMapping(value = "/updateVisualProgress")
    @RequiresPermissions("project:progress:edit")
    public Result<?> updateVisualProgress(@RequestParam(name="projectId",required=true) String projectId,
                                        @RequestParam(name="percentage",required=true) Integer percentage,
                                        HttpServletRequest req) {
        if (percentage < 0 || percentage > 100) {
            return Result.error("进度百分比必须在0-100之间！");
        }
        
        String username = req.getRemoteUser();
        boolean success = projProgressService.updateVisualProgress(projectId, percentage, username);
        return success ? Result.OK("更新成功！") : Result.error("更新失败！");
    }

    /**
     * 导出excel
     */
    @RequestMapping(value = "/exportXls")
    @RequiresPermissions("project:progress:exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ProjProgress projProgress) {
        return super.exportXls(request, projProgress, ProjProgress.class, "项目进度");
    }

    /**
     * 通过excel导入数据
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    @RequiresPermissions("project:progress:importExcel")
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ProjProgress.class);
    }

    /**
     * 分页列表查询
     */
    @AutoLog(value = "项目进度-分页列表查询")
    @ApiOperation(value="项目进度-分页列表查询", notes="项目进度-分页列表查询")
    @GetMapping(value = "/list")
    @RequiresPermissions("project:progress:list")
    public Result<?> queryPageList(ProjProgress projProgress,
                                 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                 HttpServletRequest req) {
        QueryWrapper<ProjProgress> queryWrapper = QueryGenerator.initQueryWrapper(projProgress, req.getParameterMap());
        Page<ProjProgress> page = new Page<>(pageNo, pageSize);
        IPage<ProjProgress> pageList = projProgressService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     */
    @AutoLog(value = "项目进度-添加")
    @ApiOperation(value="项目进度-添加", notes="项目进度-添加")
    @PostMapping(value = "/add")
    @RequiresPermissions("project:progress:add")
    public Result<?> add(@RequestBody ProjProgress projProgress) {
        projProgressService.save(projProgress);
        // 更新活跃时间
        projProgressService.updateActiveTime(projProgress.getProjectId());
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     */
    @AutoLog(value = "项目进度-编辑")
    @ApiOperation(value="项目进度-编辑", notes="项目进度-编辑")
    @PutMapping(value = "/edit")
    @RequiresPermissions("project:progress:edit")
    public Result<?> edit(@RequestBody ProjProgress projProgress) {
        projProgressService.updateById(projProgress);
        // 更新活跃时间
        projProgressService.updateActiveTime(projProgress.getProjectId());
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     */
    @AutoLog(value = "项目进度-通过id删除")
    @ApiOperation(value="项目进度-通过id删除", notes="项目进度-通过id删除")
    @DeleteMapping(value = "/delete")
    @RequiresPermissions("project:progress:delete")
    public Result<?> delete(@RequestParam(name="id",required=true) String id) {
        projProgressService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     */
    @AutoLog(value = "项目进度-批量删除")
    @ApiOperation(value="项目进度-批量删除", notes="项目进度-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    @RequiresPermissions("project:progress:deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        this.projProgressService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功！");
    }

    /**
     * 通过id查询
     */
    @AutoLog(value = "项目进度-通过id查询")
    @ApiOperation(value="项目进度-通过id查询", notes="项目进度-通过id查询")
    @GetMapping(value = "/queryById")
    @RequiresPermissions("project:progress:queryById")
    public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
        ProjProgress projProgress = projProgressService.getById(id);
        return Result.OK(projProgress);
    }

    /**
     * 导出excel
     */
    @RequestMapping(value = "/exportXls")
    @RequiresPermissions("project:progress:exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ProjProgress projProgress) {
        return super.exportXls(request, projProgress, ProjProgress.class, "项目进度");
    }

    /**
     * 通过excel导入数据
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    @RequiresPermissions("project:progress:importExcel")
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ProjProgress.class);
    }
}
