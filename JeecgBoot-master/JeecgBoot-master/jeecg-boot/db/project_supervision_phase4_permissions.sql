-- 项目活跃度监测菜单
INSERT INTO sys_permission(id,parent_id,name,url,component,is_route,component_name,redirect,menu_type,perms,sort_no,always_show,icon,is_leaf,keep_alive,hidden,hide_tab,description,create_by,create_time,update_by,update_time,del_flag,rule_flag,status,internal_or_external)
VALUES ('202402180801', '202402180001', '活跃度监测', '/project/activity/list', 'project/activity/ProjActivityMonitorList', 1, NULL, NULL, 1, NULL, 8.0, 0, 'dashboard', 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0);

-- 活跃度监测按钮权限
INSERT INTO sys_permission(id,parent_id,name,url,component,is_route,component_name,redirect,menu_type,perms,sort_no,always_show,icon,is_leaf,keep_alive,hidden,hide_tab,description,create_by,create_time,update_by,update_time,del_flag,rule_flag,status,internal_or_external)
VALUES
('202402180802', '202402180801', '查询', NULL, NULL, 0, NULL, NULL, 2, 'project:activity:list', 1.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180803', '202402180801', '记录更新', NULL, NULL, 0, NULL, NULL, 2, 'project:activity:edit', 2.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180804', '202402180801', '更新状态', NULL, NULL, 0, NULL, NULL, 2, 'project:activity:edit', 3.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180805', '202402180801', '批量更新', NULL, NULL, 0, NULL, NULL, 2, 'project:activity:edit', 4.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180806', '202402180801', '导出', NULL, NULL, 0, NULL, NULL, 2, 'project:activity:exportXls', 5.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0);

-- 更新项目进度按钮权限
INSERT INTO sys_permission(id,parent_id,name,url,component,is_route,component_name,redirect,menu_type,perms,sort_no,always_show,icon,is_leaf,keep_alive,hidden,hide_tab,description,create_by,create_time,update_by,update_time,del_flag,rule_flag,status,internal_or_external)
VALUES
('202402180807', '202402180002', '更新进展描述', NULL, NULL, 0, NULL, NULL, 2, 'project:progress:edit', 7.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180808', '202402180002', '更新形象进度', NULL, NULL, 0, NULL, NULL, 2, 'project:progress:edit', 8.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0);

-- 将新权限授予超级管理员角色
INSERT INTO sys_role_permission(id,role_id,permission_id,data_rule_ids)
SELECT CONCAT('202402180900', LPAD(@rownum:=@rownum+1, 3, '0')), '202402180301', id, NULL
FROM sys_permission, (SELECT @rownum:=0) t
WHERE id IN ('202402180801', '202402180802', '202402180803', '202402180804', '202402180805', '202402180806', '202402180807', '202402180808');

-- 将项目进度权限授予项目牵头部门角色
INSERT INTO sys_role_permission(id,role_id,permission_id,data_rule_ids)
SELECT CONCAT('202402180910', LPAD(@rownum:=@rownum+1, 3, '0')), '202402180302', id, NULL
FROM sys_permission, (SELECT @rownum:=0) t
WHERE id IN ('202402180807', '202402180808');
