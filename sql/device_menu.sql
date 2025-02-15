-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2000', '设备管理', '0', '4', 'device',           null,                                '1', 0, 'M', '0', '0', '', 'equipment',   'admin', sysdate(), '', null, '设备管理菜单');

-- 设备信息管理菜单
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2001', '设备信息', '2000', '1', 'info',      'device/info/index',                '1', 0, 'C', '0', '0', 'device:info:list',        'list',      'admin', sysdate(), '', null, '设备信息菜单');

-- 设备信息管理按钮
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2002', '设备信息查询', '2001', '1',  '#', '', '1', 0, 'F', '0', '0', 'device:info:query',        '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2003', '设备信息新增', '2001', '2',  '#', '', '1', 0, 'F', '0', '0', 'device:info:add',          '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2004', '设备信息修改', '2001', '3',  '#', '', '1', 0, 'F', '0', '0', 'device:info:edit',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2005', '设备信息删除', '2001', '4',  '#', '', '1', 0, 'F', '0', '0', 'device:info:remove',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2006', '设备信息导出', '2001', '5',  '#', '', '1', 0, 'F', '0', '0', 'device:info:export',       '#', 'admin', sysdate(), '', null, '');

-- 设备维护管理菜单
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2010', '设备维护', '2000', '2', 'maintenance', 'device/maintenance/index',        '1', 0, 'C', '0', '0', 'device:maintenance:list',  'tool',      'admin', sysdate(), '', null, '设备维护菜单');

-- 设备维护管理按钮
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2011', '设备维护查询', '2010', '1',  '#', '', '1', 0, 'F', '0', '0', 'device:maintenance:query',  '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2012', '设备维护新增', '2010', '2',  '#', '', '1', 0, 'F', '0', '0', 'device:maintenance:add',    '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2013', '设备维护修改', '2010', '3',  '#', '', '1', 0, 'F', '0', '0', 'device:maintenance:edit',   '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2014', '设备维护删除', '2010', '4',  '#', '', '1', 0, 'F', '0', '0', 'device:maintenance:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2015', '设备维护导出', '2010', '5',  '#', '', '1', 0, 'F', '0', '0', 'device:maintenance:export', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2016', '设备维护完成', '2010', '6',  '#', '', '1', 0, 'F', '0', '0', 'device:maintenance:complete', '#', 'admin', sysdate(), '', null, '');

-- 设备借用管理菜单
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2020', '设备借用', '2000', '3', 'borrow',     'device/borrow/index',             '1', 0, 'C', '0', '0', 'device:borrow:list',       'time',      'admin', sysdate(), '', null, '设备借用菜单');

-- 设备借用管理按钮
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2021', '设备借用查询', '2020', '1',  '#', '', '1', 0, 'F', '0', '0', 'device:borrow:query',       '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2022', '设备借用新增', '2020', '2',  '#', '', '1', 0, 'F', '0', '0', 'device:borrow:add',         '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2023', '设备借用修改', '2020', '3',  '#', '', '1', 0, 'F', '0', '0', 'device:borrow:edit',        '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2024', '设备借用删除', '2020', '4',  '#', '', '1', 0, 'F', '0', '0', 'device:borrow:remove',      '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2025', '设备借用导出', '2020', '5',  '#', '', '1', 0, 'F', '0', '0', 'device:borrow:export',      '#', 'admin', sysdate(), '', null, '');
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2026', '设备借用归还', '2020', '6',  '#', '', '1', 0, 'F', '0', '0', 'device:borrow:return',      '#', 'admin', sysdate(), '', null, '');