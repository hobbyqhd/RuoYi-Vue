-- 设备维护类型字典类型
insert into sys_dict_type(dict_name, dict_type, status, create_by, remark)
values ('设备维护类型', 'sys_device_maintenance_type', '0', 'admin', '设备维护类型');

-- 设备维护类型字典数据
insert into sys_dict_data(dict_sort, dict_label, dict_value, dict_type, status, create_by, remark)
values (1, '常规保养', '1', 'sys_device_maintenance_type', '0', 'admin', '常规保养'),
       (2, '故障维修', '2', 'sys_device_maintenance_type', '0', 'admin', '故障维修'),
       (3, '定期检查', '3', 'sys_device_maintenance_type', '0', 'admin', '定期检查'),
       (4, '紧急维护', '4', 'sys_device_maintenance_type', '0', 'admin', '紧急维护'),
       (5, '预防性维护', '5', 'sys_device_maintenance_type', '0', 'admin', '预防性维护');