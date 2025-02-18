-- 添加超级管理员角色
INSERT INTO sys_role(id,role_name,role_code,description,create_by,create_time,update_by,update_time,tenant_id)
VALUES ('202402180301', '项目督办超级管理员', 'project_supervision_admin', '政府办超级管理员，可管理部门权限', 'admin', '2024-02-18 00:00:00', NULL, NULL, NULL);

-- 添加部门权限管理菜单
INSERT INTO sys_permission(id,parent_id,name,url,component,is_route,component_name,redirect,menu_type,perms,sort_no,always_show,icon,is_leaf,keep_alive,hidden,hide_tab,description,create_by,create_time,update_by,update_time,del_flag,rule_flag,status,internal_or_external)
VALUES ('202402180302', '202402180001', '部门权限管理', '/project/dept/list', 'project/dept/DeptPermissionList', 1, NULL, NULL, 1, NULL, 5.0, 0, 'team', 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0);

-- 添加部门权限管理按钮权限
INSERT INTO sys_permission(id,parent_id,name,url,component,is_route,component_name,redirect,menu_type,perms,sort_no,always_show,icon,is_leaf,keep_alive,hidden,hide_tab,description,create_by,create_time,update_by,update_time,del_flag,rule_flag,status,internal_or_external)
VALUES
('202402180303', '202402180302', '查询', NULL, NULL, 0, NULL, NULL, 2, 'project:dept:list', 1.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180304', '202402180302', '授权', NULL, NULL, 0, NULL, NULL, 2, 'project:dept:auth', 2.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180305', '202402180302', '撤销', NULL, NULL, 0, NULL, NULL, 2, 'project:dept:revoke', 3.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0);

-- 创建部门权限表
CREATE TABLE proj_dept_permission (
    id varchar(32) NOT NULL COMMENT '主键ID',
    dept_id varchar(32) NOT NULL COMMENT '部门ID',
    dept_name varchar(100) COMMENT '部门名称',
    valid_start_time datetime NOT NULL COMMENT '有效期开始时间',
    valid_end_time datetime NOT NULL COMMENT '有效期结束时间',
    status varchar(2) DEFAULT '1' COMMENT '状态(0-已撤销,1-有效)',
    create_by varchar(32) COMMENT '创建人',
    create_time datetime COMMENT '创建时间',
    update_by varchar(32) COMMENT '更新人',
    update_time datetime COMMENT '更新时间',
    tenant_id int(10) COMMENT '租户ID',
    PRIMARY KEY (id),
    KEY idx_dept_id (dept_id),
    KEY idx_valid_time (valid_start_time, valid_end_time)
) COMMENT='部门权限表';

-- 将所有权限授予超级管理员角色
INSERT INTO sys_role_permission(id,role_id,permission_id,data_rule_ids)
SELECT CONCAT('202402180400', LPAD(@rownum:=@rownum+1, 3, '0')), '202402180301', id, NULL
FROM sys_permission, (SELECT @rownum:=0) t
WHERE parent_id IN ('202402180001', '202402180002', '202402180003', '202402180004', '202402180021', '202402180302');
