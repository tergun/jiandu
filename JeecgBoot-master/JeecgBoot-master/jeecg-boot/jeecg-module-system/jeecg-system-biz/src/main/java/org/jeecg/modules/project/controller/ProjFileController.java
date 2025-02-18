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
import org.jeecg.modules.project.entity.ProjFile;
import org.jeecg.modules.project.service.IProjFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @Description: 项目文件
 * @Author: jeecg-boot
 * @Date: 2024-02-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="项目文件")
@RestController
@RequestMapping("/project/file")
public class ProjFileController extends JeecgController<ProjFile, IProjFileService> {
    @Autowired
    private IProjFileService projFileService;

    /**
     * 分页列表查询
     */
    @AutoLog(value = "项目文件-分页列表查询")
    @ApiOperation(value="项目文件-分页列表查询", notes="项目文件-分页列表查询")
    @GetMapping(value = "/list")
    @RequiresPermissions("project:file:list")
    public Result<?> queryPageList(ProjFile projFile,
                                 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                 HttpServletRequest req) {
        QueryWrapper<ProjFile> queryWrapper = QueryGenerator.initQueryWrapper(projFile, req.getParameterMap());
        Page<ProjFile> page = new Page<>(pageNo, pageSize);
        IPage<ProjFile> pageList = projFileService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 上传文件
     */
    @AutoLog(value = "项目文件-上传")
    @ApiOperation(value="项目文件-上传", notes="项目文件-上传")
    @PostMapping(value = "/upload")
    @RequiresPermissions("project:file:upload")
    public Result<?> upload(@RequestParam("file") MultipartFile file,
                          @RequestParam(name="projectId",required=true) String projectId,
                          @RequestParam(name="stageId",required=false) String stageId) {
        try {
            String bizPath = "project/" + projectId;
            String url = CommonUtils.upload(file, bizPath, "");
            
            // 保存文件信息
            ProjFile projFile = new ProjFile();
            projFile.setProjectId(projectId);
            projFile.setStageId(stageId);
            projFile.setFileName(file.getOriginalFilename());
            projFile.setFilePath(url);
            projFile.setFileType(file.getContentType());
            projFile.setFileSize(file.getSize());
            projFileService.save(projFile);
            
            return Result.OK(url);
        } catch (Exception e) {
            log.error("文件上传失败", e);
            return Result.error("文件上传失败：" + e.getMessage());
        }
    }

    /**
     * 通过id删除
     */
    @AutoLog(value = "项目文件-通过id删除")
    @ApiOperation(value="项目文件-通过id删除", notes="项目文件-通过id删除")
    @DeleteMapping(value = "/delete")
    @RequiresPermissions("project:file:delete")
    public Result<?> delete(@RequestParam(name="id",required=true) String id) {
        projFileService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     */
    @AutoLog(value = "项目文件-批量删除")
    @ApiOperation(value="项目文件-批量删除", notes="项目文件-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    @RequiresPermissions("project:file:deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        this.projFileService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功！");
    }

    /**
     * 通过id查询
     */
    @AutoLog(value = "项目文件-通过id查询")
    @ApiOperation(value="项目文件-通过id查询", notes="项目文件-通过id查询")
    @GetMapping(value = "/queryById")
    @RequiresPermissions("project:file:queryById")
    public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
        ProjFile projFile = projFileService.getById(id);
        return Result.OK(projFile);
    }

    /**
     * 导出excel
     */
    @RequestMapping(value = "/exportXls")
    @RequiresPermissions("project:file:exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ProjFile projFile) {
        return super.exportXls(request, projFile, ProjFile.class, "项目文件");
    }

    /**
     * 通过excel导入数据
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    @RequiresPermissions("project:file:importExcel")
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ProjFile.class);
    }
}
