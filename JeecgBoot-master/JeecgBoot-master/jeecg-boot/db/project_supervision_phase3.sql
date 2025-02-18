-- 添加审批环节状态字典
INSERT INTO sys_dict(id,dict_name,dict_code,description,del_flag,create_by,create_time,update_by,update_time,type)
VALUES ('202402180401', '审批环节状态', 'approval_stage_status', '审批环节状态', 0, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0);

INSERT INTO sys_dict_item(id,dict_id,item_text,item_value,description,sort_order,status,create_by,create_time,update_by,update_time)
VALUES 
('202402180402', '202402180401', '未开始', '0', '环节未开始', 1, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180403', '202402180401', '准备中', '1', '正在准备材料', 2, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180404', '202402180401', '已提交', '2', '材料已提交', 3, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180405', '202402180401', '审批中', '3', '正在审批', 4, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180406', '202402180401', '已通过', '4', '审批通过', 5, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180407', '202402180401', '已驳回', '5', '审批驳回', 6, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180408', '202402180401', '无需办理', '6', '无需办理该环节', 7, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL);

-- 添加办理层级字典
INSERT INTO sys_dict(id,dict_name,dict_code,description,del_flag,create_by,create_time,update_by,update_time,type)
VALUES ('202402180409', '办理层级', 'approval_level', '审批办理层级', 0, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0);

INSERT INTO sys_dict_item(id,dict_id,item_text,item_value,description,sort_order,status,create_by,create_time,update_by,update_time)
VALUES 
('202402180410', '202402180409', '旗级', '1', '旗级审批', 1, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180411', '202402180409', '市级', '2', '市级审批', 2, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180412', '202402180409', '自治区级', '3', '自治区级审批', 3, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180413', '202402180409', '国家级', '4', '国家级审批', 4, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL);

-- 审批环节记录表
CREATE TABLE proj_approval_record (
    id varchar(32) NOT NULL COMMENT '主键ID',
    project_id varchar(32) NOT NULL COMMENT '项目ID',
    stage_id varchar(32) NOT NULL COMMENT '环节ID',
    old_status varchar(2) COMMENT '原状态',
    new_status varchar(2) NOT NULL COMMENT '新状态',
    remark text COMMENT '备注说明',
    operator varchar(32) COMMENT '操作人',
    operate_time datetime COMMENT '操作时间',
    create_by varchar(32) COMMENT '创建人',
    create_time datetime COMMENT '创建时间',
    update_by varchar(32) COMMENT '更新人',
    update_time datetime COMMENT '更新时间',
    del_flag int(1) DEFAULT 0 COMMENT '删除标记',
    tenant_id int(10) COMMENT '租户ID',
    PRIMARY KEY (id),
    KEY idx_project_stage (project_id, stage_id)
) COMMENT='审批环节记录表';

-- 修改审批环节表，添加新字段
ALTER TABLE proj_approval_stage
ADD COLUMN required_materials text COMMENT '所需材料清单',
ADD COLUMN current_materials text COMMENT '当前已有材料',
ADD COLUMN reject_reason text COMMENT '驳回原因',
ADD COLUMN next_stage_id varchar(32) COMMENT '下一环节ID',
ADD COLUMN prev_stage_id varchar(32) COMMENT '上一环节ID';
