-- 项目进度菜单
INSERT INTO sys_permission(id,parent_id,name,url,component,is_route,component_name,redirect,menu_type,perms,sort_no,always_show,icon,is_leaf,keep_alive,hidden,hide_tab,description,create_by,create_time,update_by,update_time,del_flag,rule_flag,status,internal_or_external)
VALUES ('202402180021', '202402180001', '项目进度', '/project/progress/list', 'project/progress/ProjProgressList', 1, NULL, NULL, 1, NULL, 4.0, 0, 'bar-chart', 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0);

-- 项目进度按钮权限
INSERT INTO sys_permission(id,parent_id,name,url,component,is_route,component_name,redirect,menu_type,perms,sort_no,always_show,icon,is_leaf,keep_alive,hidden,hide_tab,description,create_by,create_time,update_by,update_time,del_flag,rule_flag,status,internal_or_external)
VALUES
('202402180022', '202402180021', '查询', NULL, NULL, 0, NULL, NULL, 2, 'project:progress:list', 1.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180023', '202402180021', '添加', NULL, NULL, 0, NULL, NULL, 2, 'project:progress:add', 2.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180024', '202402180021', '编辑', NULL, NULL, 0, NULL, NULL, 2, 'project:progress:edit', 3.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180025', '202402180021', '删除', NULL, NULL, 0, NULL, NULL, 2, 'project:progress:delete', 4.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180026', '202402180021', '导入', NULL, NULL, 0, NULL, NULL, 2, 'project:progress:importExcel', 5.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180027', '202402180021', '导出', NULL, NULL, 0, NULL, NULL, 2, 'project:progress:exportXls', 6.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0);

-- 添加字典数据
INSERT INTO sys_dict(id,dict_name,dict_code,description,del_flag,create_by,create_time,update_by,update_time,type)
VALUES ('202402180201', '项目活跃度', 'project_activity_level', '项目活跃度状态', 0, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0);

INSERT INTO sys_dict_item(id,dict_id,item_text,item_value,description,sort_order,status,create_by,create_time,update_by,update_time)
VALUES 
('202402180202', '202402180201', '活跃', '1', '7天内有更新', 1, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180203', '202402180201', '一般', '2', '15天内有更新', 2, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL),
('202402180204', '202402180201', '不活跃', '3', '超过15天未更新', 3, 1, 'admin', '2024-02-18 00:00:00', NULL, NULL);
