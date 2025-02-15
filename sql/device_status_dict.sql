-- 设备状态字典类型
insert into sys_dict_type(dict_name, dict_type, status, create_by, remark)
values ('设备状态', 'sys_device_status', '0', 'admin', '设备状态列表');

-- 设备状态字典数据
insert into sys_dict_data(dict_sort, dict_label, dict_value, dict_type, status, create_by, remark)
values (1, '正常', '1', 'sys_device_status', '0', 'admin', '设备正常使用中');

insert into sys_dict_data(dict_sort, dict_label, dict_value, dict_type, status, create_by, remark)
values (2, '借出', '2', 'sys_device_status', '0', 'admin', '设备已借出');

insert into sys_dict_data(dict_sort, dict_label, dict_value, dict_type, status, create_by, remark)
values (3, '维修中', '3', 'sys_device_status', '0', 'admin', '设备正在维修');

insert into sys_dict_data(dict_sort, dict_label, dict_value, dict_type, status, create_by, remark)
values (4, '报废', '4', 'sys_device_status', '0', 'admin', '设备已报废');