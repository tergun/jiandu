-- 项目基本信息表
CREATE TABLE proj_basic_info (
    id varchar(32) NOT NULL COMMENT '主键ID',
    project_name varchar(100) NOT NULL COMMENT '项目名称',
    dispatch_level varchar(50) NOT NULL COMMENT '调度层级',
    investment_body varchar(100) NOT NULL COMMENT '投资主体',
    construction_nature varchar(50) NOT NULL COMMENT '建设性质',
    total_investment decimal(20,2) NOT NULL COMMENT '总投资',
    plan_investment_2025 decimal(20,2) NOT NULL COMMENT '2025年计划完成投资',
    plan_start_time datetime COMMENT '计划开复工时间',
    plan_end_time datetime COMMENT '计划完工时间',
    leader varchar(50) COMMENT '分管领导',
    responsible_dept varchar(100) COMMENT '责任单位',
    create_by varchar(32) COMMENT '创建人',
    create_time datetime COMMENT '创建时间',
    update_by varchar(32) COMMENT '更新人',
    update_time datetime COMMENT '更新时间',
    del_flag int(1) DEFAULT 0 COMMENT '删除标记',
    tenant_id int(10) COMMENT '租户ID',
    PRIMARY KEY (id)
) COMMENT='项目基本信息表';

-- 项目审批环节表
CREATE TABLE proj_approval_stage (
    id varchar(32) NOT NULL COMMENT '主键ID',
    project_id varchar(32) NOT NULL COMMENT '项目ID',
    stage_name varchar(100) NOT NULL COMMENT '环节名称',
    status varchar(20) NOT NULL COMMENT '状态(0-否,1-是,2-无需办理)',
    deadline datetime COMMENT '办理期限',
    approval_level varchar(50) COMMENT '办理层级(1-旗级,2-市级,3-自治区级,4-国家级)',
    file_info text COMMENT '组卷情况',
    approval_info text COMMENT '审批情况',
    create_by varchar(32) COMMENT '创建人',
    create_time datetime COMMENT '创建时间',
    update_by varchar(32) COMMENT '更新人',
    update_time datetime COMMENT '更新时间',
    del_flag int(1) DEFAULT 0 COMMENT '删除标记',
    tenant_id int(10) COMMENT '租户ID',
    PRIMARY KEY (id),
    KEY idx_project_id (project_id)
) COMMENT='项目审批环节表';

-- 项目文件表
CREATE TABLE proj_file (
    id varchar(32) NOT NULL COMMENT '主键ID',
    project_id varchar(32) NOT NULL COMMENT '项目ID',
    stage_id varchar(32) COMMENT '审批环节ID',
    file_name varchar(200) NOT NULL COMMENT '文件名称',
    file_path varchar(500) NOT NULL COMMENT '文件路径',
    file_type varchar(50) COMMENT '文件类型',
    file_size bigint COMMENT '文件大小(字节)',
    create_by varchar(32) COMMENT '创建人',
    create_time datetime COMMENT '创建时间',
    update_by varchar(32) COMMENT '更新人',
    update_time datetime COMMENT '更新时间',
    del_flag int(1) DEFAULT 0 COMMENT '删除标记',
    tenant_id int(10) COMMENT '租户ID',
    PRIMARY KEY (id),
    KEY idx_project_stage (project_id, stage_id)
) COMMENT='项目文件表';
