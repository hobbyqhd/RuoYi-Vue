-- 设备维护状态字典类型
insert into sys_dict_type(dict_name, dict_type, status, create_by, remark)
values ('设备维护状态', 'sys_device_maintenance_status', '0', 'admin', '设备维护状态');

-- 设备维护状态字典数据
insert into sys_dict_data(dict_sort, dict_label, dict_value, dict_type, status, create_by, remark)
values (1, '待维护', '0', 'sys_device_maintenance_status', '0', 'admin', '待维护'),
       (2, '维护中', '1', 'sys_device_maintenance_status', '0', 'admin', '维护中'),
       (3, '已完成', '2', 'sys_device_maintenance_status', '0', 'admin', '已完成'),
       (4, '已取消', '3', 'sys_device_maintenance_status', '0', 'admin', '已取消');