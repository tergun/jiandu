-- 项目管理菜单
INSERT INTO sys_permission(id,parent_id,name,url,component,is_route,component_name,redirect,menu_type,perms,sort_no,always_show,icon,is_leaf,keep_alive,hidden,hide_tab,description,create_by,create_time,update_by,update_time,del_flag,rule_flag,status,internal_or_external)
VALUES ('202402180001', NULL, '项目管理', '/project', 'layouts/RouteView', 1, NULL, NULL, 0, NULL, 1.0, 0, 'project', 0, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0);

-- 项目基本信息菜单
INSERT INTO sys_permission(id,parent_id,name,url,component,is_route,component_name,redirect,menu_type,perms,sort_no,always_show,icon,is_leaf,keep_alive,hidden,hide_tab,description,create_by,create_time,update_by,update_time,del_flag,rule_flag,status,internal_or_external)
VALUES ('202402180002', '202402180001', '项目基本信息', '/project/basic/list', 'project/basic/ProjBasicInfoList', 1, NULL, NULL, 1, NULL, 1.0, 0, 'form', 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0);

-- 项目审批环节菜单
INSERT INTO sys_permission(id,parent_id,name,url,component,is_route,component_name,redirect,menu_type,perms,sort_no,always_show,icon,is_leaf,keep_alive,hidden,hide_tab,description,create_by,create_time,update_by,update_time,del_flag,rule_flag,status,internal_or_external)
VALUES ('202402180003', '202402180001', '项目审批环节', '/project/approval/list', 'project/approval/ProjApprovalStageList', 1, NULL, NULL, 1, NULL, 2.0, 0, 'audit', 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0);

-- 项目文件管理菜单
INSERT INTO sys_permission(id,parent_id,name,url,component,is_route,component_name,redirect,menu_type,perms,sort_no,always_show,icon,is_leaf,keep_alive,hidden,hide_tab,description,create_by,create_time,update_by,update_time,del_flag,rule_flag,status,internal_or_external)
VALUES ('202402180004', '202402180001', '项目文件管理', '/project/file/list', 'project/file/ProjFileList', 1, NULL, NULL, 1, NULL, 3.0, 0, 'folder', 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0);

-- 项目基本信息按钮权限
INSERT INTO sys_permission(id,parent_id,name,url,component,is_route,component_name,redirect,menu_type,perms,sort_no,always_show,icon,is_leaf,keep_alive,hidden,hide_tab,description,create_by,create_time,update_by,update_time,del_flag,rule_flag,status,internal_or_external)
VALUES
('202402180005', '202402180002', '查询', NULL, NULL, 0, NULL, NULL, 2, 'project:basic:list', 1.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180006', '202402180002', '添加', NULL, NULL, 0, NULL, NULL, 2, 'project:basic:add', 2.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180007', '202402180002', '编辑', NULL, NULL, 0, NULL, NULL, 2, 'project:basic:edit', 3.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180008', '202402180002', '删除', NULL, NULL, 0, NULL, NULL, 2, 'project:basic:delete', 4.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180009', '202402180002', '导入', NULL, NULL, 0, NULL, NULL, 2, 'project:basic:importExcel', 5.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180010', '202402180002', '导出', NULL, NULL, 0, NULL, NULL, 2, 'project:basic:exportXls', 6.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0);

-- 项目审批环节按钮权限
INSERT INTO sys_permission(id,parent_id,name,url,component,is_route,component_name,redirect,menu_type,perms,sort_no,always_show,icon,is_leaf,keep_alive,hidden,hide_tab,description,create_by,create_time,update_by,update_time,del_flag,rule_flag,status,internal_or_external)
VALUES
('202402180011', '202402180003', '查询', NULL, NULL, 0, NULL, NULL, 2, 'project:approval:list', 1.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180012', '202402180003', '添加', NULL, NULL, 0, NULL, NULL, 2, 'project:approval:add', 2.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180013', '202402180003', '编辑', NULL, NULL, 0, NULL, NULL, 2, 'project:approval:edit', 3.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180014', '202402180003', '删除', NULL, NULL, 0, NULL, NULL, 2, 'project:approval:delete', 4.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180015', '202402180003', '导入', NULL, NULL, 0, NULL, NULL, 2, 'project:approval:importExcel', 5.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180016', '202402180003', '导出', NULL, NULL, 0, NULL, NULL, 2, 'project:approval:exportXls', 6.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0);

-- 项目文件按钮权限
INSERT INTO sys_permission(id,parent_id,name,url,component,is_route,component_name,redirect,menu_type,perms,sort_no,always_show,icon,is_leaf,keep_alive,hidden,hide_tab,description,create_by,create_time,update_by,update_time,del_flag,rule_flag,status,internal_or_external)
VALUES
('202402180017', '202402180004', '查询', NULL, NULL, 0, NULL, NULL, 2, 'project:file:list', 1.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180018', '202402180004', '上传', NULL, NULL, 0, NULL, NULL, 2, 'project:file:upload', 2.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180019', '202402180004', '删除', NULL, NULL, 0, NULL, NULL, 2, 'project:file:delete', 3.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180020', '202402180004', '导出', NULL, NULL, 0, NULL, NULL, 2, 'project:file:exportXls', 4.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0);
