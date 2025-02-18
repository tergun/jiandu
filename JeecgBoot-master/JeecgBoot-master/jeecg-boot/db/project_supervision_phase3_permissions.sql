-- 审批流程菜单
INSERT INTO sys_permission(id,parent_id,name,url,component,is_route,component_name,redirect,menu_type,perms,sort_no,always_show,icon,is_leaf,keep_alive,hidden,hide_tab,description,create_by,create_time,update_by,update_time,del_flag,rule_flag,status,internal_or_external)
VALUES ('202402180601', '202402180001', '审批流程', '/project/process/list', 'project/process/ProjApprovalProcessList', 1, NULL, NULL, 1, NULL, 6.0, 0, 'audit', 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0);

-- 审批流程按钮权限
INSERT INTO sys_permission(id,parent_id,name,url,component,is_route,component_name,redirect,menu_type,perms,sort_no,always_show,icon,is_leaf,keep_alive,hidden,hide_tab,description,create_by,create_time,update_by,update_time,del_flag,rule_flag,status,internal_or_external)
VALUES
('202402180602', '202402180601', '查询', NULL, NULL, 0, NULL, NULL, 2, 'project:process:list', 1.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180603', '202402180601', '提交', NULL, NULL, 0, NULL, NULL, 2, 'project:process:submit', 2.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180604', '202402180601', '处理', NULL, NULL, 0, NULL, NULL, 2, 'project:process:handle', 3.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180605', '202402180601', '撤回', NULL, NULL, 0, NULL, NULL, 2, 'project:process:withdraw', 4.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180606', '202402180601', '导出', NULL, NULL, 0, NULL, NULL, 2, 'project:process:exportXls', 5.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0);

-- 更新审批环节按钮权限
INSERT INTO sys_permission(id,parent_id,name,url,component,is_route,component_name,redirect,menu_type,perms,sort_no,always_show,icon,is_leaf,keep_alive,hidden,hide_tab,description,create_by,create_time,update_by,update_time,del_flag,rule_flag,status,internal_or_external)
VALUES
('202402180607', '202402180003', '更新状态', NULL, NULL, 0, NULL, NULL, 2, 'project:approval:updateStatus', 7.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180608', '202402180003', '更新办理信息', NULL, NULL, 0, NULL, NULL, 2, 'project:approval:updateProcessInfo', 8.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180609', '202402180003', '上传审批文件', NULL, NULL, 0, NULL, NULL, 2, 'project:approval:uploadApprovalFile', 9.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0),
('202402180610', '202402180003', '上传说明材料', NULL, NULL, 0, NULL, NULL, 2, 'project:approval:uploadExplanation', 10.0, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-02-18 00:00:00', NULL, NULL, 0, 0, '1', 0);

-- 将新权限授予超级管理员角色
INSERT INTO sys_role_permission(id,role_id,permission_id,data_rule_ids)
SELECT CONCAT('202402180700', LPAD(@rownum:=@rownum+1, 3, '0')), '202402180301', id, NULL
FROM sys_permission, (SELECT @rownum:=0) t
WHERE id IN ('202402180601', '202402180602', '202402180603', '202402180604', '202402180605', '202402180606', '202402180607', '202402180608', '202402180609', '202402180610');
