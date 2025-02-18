-- 项目活跃度监测表
CREATE TABLE proj_activity_monitor (
    id varchar(32) NOT NULL COMMENT '主键ID',
    project_id varchar(32) NOT NULL COMMENT '项目ID',
    last_update_time datetime COMMENT '最后更新时间',
    last_update_type varchar(50) COMMENT '更新类型',
    last_update_content text COMMENT '更新内容',
    is_active tinyint(1) DEFAULT 1 COMMENT '是否活跃',
    inactive_days int DEFAULT 0 COMMENT '不活跃天数',
    create_by varchar(32) COMMENT '创建人',
    create_time datetime COMMENT '创建时间',
    update_by varchar(32) COMMENT '更新人',
    update_time datetime COMMENT '更新时间',
    del_flag int(1) DEFAULT 0 COMMENT '删除标记',
    tenant_id int(10) COMMENT '租户ID',
    PRIMARY KEY (id),
    KEY idx_project (project_id)
) COMMENT='项目活跃度监测表';

-- 添加活跃度状态字典
INSERT INTO sys_dict(id,dict_name,dict_code,description,del_flag,create_by,create_time,update_by,update_time,type)
VALUES ('202402180701', '活跃度状态', 'activity_status', '项目活跃度状态', 0, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0);

INSERT INTO sys_dict_item(id,dict_id,item_text,item_value,description,sort_order,status,create_by,create_time,update_by,update_time)
VALUES 
('202402180702', '202402180701', '活跃', '1', '7天内有更新', 1, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180703', '202402180701', '不活跃', '0', '7天内无更新', 2, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL);

-- 修改项目进度表，添加活跃度相关字段
ALTER TABLE proj_progress
ADD COLUMN last_active_time datetime COMMENT '最后活跃时间',
ADD COLUMN activity_status varchar(2) COMMENT '活跃状态';

-- 添加进度更新类型字典
INSERT INTO sys_dict(id,dict_name,dict_code,description,del_flag,create_by,create_time,update_by,update_time,type)
VALUES ('202402180704', '进度更新类型', 'progress_update_type', '项目进度更新类型', 0, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0);

INSERT INTO sys_dict_item(id,dict_id,item_text,item_value,description,sort_order,status,create_by,create_time,update_by,update_time)
VALUES 
('202402180705', '202402180704', '基础信息更新', '1', '基础信息变更', 1, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180706', '202402180704', '进度描述更新', '2', '进度描述变更', 2, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180707', '202402180704', '形象进度更新', '3', '形象进度变更', 3, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180708', '202402180704', '前期手续更新', '4', '前期手续变更', 4, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180709', '202402180704', '审批流程更新', '5', '审批流程变更', 5, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL);
