-- 添加审批流程状态字典
INSERT INTO sys_dict(id,dict_name,dict_code,description,del_flag,create_by,create_time,update_by,update_time,type)
VALUES ('202402180501', '审批流程状态', 'approval_process_status', '审批流程状态', 0, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0);

INSERT INTO sys_dict_item(id,dict_id,item_text,item_value,description,sort_order,status,create_by,create_time,update_by,update_time)
VALUES 
('202402180502', '202402180501', '待提交', '0', '材料待提交', 1, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180503', '202402180501', '审核中', '1', '材料审核中', 2, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180504', '202402180501', '已通过', '2', '审核已通过', 3, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180505', '202402180501', '已驳回', '3', '审核已驳回', 4, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180506', '202402180501', '已撤回', '4', '申请已撤回', 5, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL);

-- 审批流程表
CREATE TABLE proj_approval_process (
    id varchar(32) NOT NULL COMMENT '主键ID',
    project_id varchar(32) NOT NULL COMMENT '项目ID',
    stage_id varchar(32) NOT NULL COMMENT '环节ID',
    process_status varchar(2) NOT NULL COMMENT '流程状态',
    current_handler varchar(32) COMMENT '当前处理人',
    submit_time datetime COMMENT '提交时间',
    handle_time datetime COMMENT '处理时间',
    handle_result varchar(2) COMMENT '处理结果',
    handle_opinion text COMMENT '处理意见',
    create_by varchar(32) COMMENT '创建人',
    create_time datetime COMMENT '创建时间',
    update_by varchar(32) COMMENT '更新人',
    update_time datetime COMMENT '更新时间',
    del_flag int(1) DEFAULT 0 COMMENT '删除标记',
    tenant_id int(10) COMMENT '租户ID',
    PRIMARY KEY (id),
    KEY idx_project_stage (project_id, stage_id)
) COMMENT='审批流程表';

-- 修改审批环节表，添加流程相关字段
ALTER TABLE proj_approval_stage
ADD COLUMN process_id varchar(32) COMMENT '当前流程ID',
ADD COLUMN submit_materials text COMMENT '已提交材料清单',
ADD COLUMN handle_dept varchar(100) COMMENT '办理部门',
ADD COLUMN handle_user varchar(32) COMMENT '办理人员';
