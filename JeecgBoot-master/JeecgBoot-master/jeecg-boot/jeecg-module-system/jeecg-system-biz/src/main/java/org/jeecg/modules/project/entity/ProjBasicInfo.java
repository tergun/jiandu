package org.jeecg.modules.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: 项目基本信息
 * @Author: jeecg-boot
 * @Date: 2024-02-18
 * @Version: V1.0
 */
@Data
@TableName("proj_basic_info")
public class ProjBasicInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @Excel(name = "项目名称", width = 15)
    private String projectName;

    @Excel(name = "调度层级", width = 10)
    private String dispatchLevel;

    @Excel(name = "投资主体", width = 15)
    private String investmentBody;

    @Excel(name = "建设性质", width = 10)
    private String constructionNature;

    @Excel(name = "总投资", width = 15)
    private BigDecimal totalInvestment;

    @Excel(name = "2025年计划完成投资", width = 15)
    private BigDecimal planInvestment2025;

    @Excel(name = "计划开复工时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date planStartTime;

    @Excel(name = "计划完工时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date planEndTime;

    @Excel(name = "分管领导", width = 10)
    private String leader;

    @Excel(name = "责任单位", width = 15)
    private String responsibleDept;

    private String createBy;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String updateBy;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Integer delFlag;

    private Integer tenantId;
}
