-- 项目进度表
CREATE TABLE proj_progress (
    id varchar(32) NOT NULL COMMENT '主键ID',
    project_id varchar(32) NOT NULL COMMENT '项目ID',
    progress_desc text COMMENT '进展描述',
    progress_percent decimal(5,2) COMMENT '形象进度',
    last_active_time datetime COMMENT '最近活跃时间',
    department varchar(100) COMMENT '部门',
    create_by varchar(32) COMMENT '创建人',
    create_time datetime COMMENT '创建时间',
    update_by varchar(32) COMMENT '更新人',
    update_time datetime COMMENT '更新时间',
    del_flag int(1) DEFAULT 0 COMMENT '删除标记',
    tenant_id int(10) COMMENT '租户ID',
    PRIMARY KEY (id),
    KEY idx_project_id (project_id),
    KEY idx_department (department)
) COMMENT='项目进度表';

-- 添加项目基本信息表的新字段
ALTER TABLE proj_basic_info 
ADD COLUMN last_progress_update datetime COMMENT '最近进度更新时间',
ADD COLUMN last_active_time datetime COMMENT '最近活跃时间',
ADD COLUMN progress_percent decimal(5,2) COMMENT '当前形象进度',
ADD COLUMN department_id varchar(32) COMMENT '部门ID',
ADD COLUMN department_name varchar(100) COMMENT '部门名称';

-- 添加字典数据
INSERT INTO sys_dict(id,dict_name,dict_code,description,del_flag,create_by,create_time,update_by,update_time,type)
VALUES ('202402180101', '项目进度状态', 'project_progress_status', '项目进度状态', 0, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0);

INSERT INTO sys_dict_item(id,dict_id,item_text,item_value,description,sort_order,status,create_by,create_time,update_by,update_time)
VALUES 
('202402180102', '202402180101', '未开始', '0', '项目未开始', 1, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180103', '202402180101', '进行中', '1', '项目进行中', 2, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180104', '202402180101', '已完成', '2', '项目已完成', 3, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180105', '202402180101', '已暂停', '3', '项目已暂停', 4, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL);
