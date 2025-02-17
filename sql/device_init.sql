-- ----------------------------
-- 1、设备信息表
-- ----------------------------
drop table if exists sys_device;
create table sys_device (
  device_id           bigint(20)      not null auto_increment    comment '设备ID',
  device_name         varchar(50)     not null                   comment '设备名称',
  device_code         varchar(64)     not null                   comment '设备编号',
  device_type         varchar(20)     not null                   comment '设备类型',
  device_status       char(1)         default '1'                comment '设备状态（1正常 2借出 3维修中 4报废）',
  purchase_date       datetime                                    comment '购入日期',
  purchase_price      decimal(10,2)                              comment '购入价格',
  manufacturer        varchar(100)                               comment '制造商',
  model_number        varchar(100)                               comment '型号',
  custodian           varchar(100)                               comment '保管人',
  location            varchar(255)                               comment '存放位置',
  remark              varchar(500)                               comment '备注',
  create_by           varchar(64)     default ''                 comment '创建者',
  create_time         datetime                                   comment '创建时间',
  update_by           varchar(64)     default ''                 comment '更新者',
  update_time         datetime                                   comment '更新时间',
  primary key (device_id)
) engine=innodb auto_increment=100 comment = '设备信息表';

-- ----------------------------
-- 2、设备借用记录表
-- ----------------------------
drop table if exists sys_device_borrow;
create table sys_device_borrow (
  borrow_id           bigint(20)      not null auto_increment    comment '借用记录ID',
  device_id           bigint(20)      not null                   comment '设备ID',
  borrower            varchar(64)     not null                   comment '借用人',
  borrow_time         datetime        not null                   comment '借用时间',
  expected_return_time datetime       not null                   comment '预计归还时间',
  actual_return_time  datetime                                   comment '实际归还时间',
  borrow_status       char(1)         not null                   comment '借用状态（0待审批 1已批准 2已拒绝 3已归还 4逾期）',
  borrow_purpose      varchar(500)                               comment '借用用途',
  approver            varchar(64)                                comment '审批人',
  approve_time        datetime                                   comment '审批时间',
  approve_remark      varchar(500)                               comment '审批备注',
  create_by           varchar(64)     default ''                 comment '创建者',
  create_time         datetime                                   comment '创建时间',
  update_by           varchar(64)     default ''                 comment '更新者',
  update_time         datetime                                   comment '更新时间',
  remark              varchar(500)                               comment '备注',
  primary key (borrow_id)
) engine=innodb auto_increment=100 comment = '设备借用记录表';

-- ----------------------------
-- 3、设备维护记录表
-- ----------------------------
drop table if exists sys_device_maintenance;
create table sys_device_maintenance (
  maintenance_id      bigint(20)      not null auto_increment    comment '维护ID',
  device_id           bigint(20)      not null                   comment '设备ID',
  maintenance_type    char(1)         not null                   comment '维护类型（1定期保养 2故障维修）',
  maintenance_status  char(1)         default '0'                comment '维护状态（0进行中 1已完成）',
  start_time          datetime                                   comment '开始时间',
  end_time            datetime                                   comment '结束时间',
  maintenance_cost    decimal(10,2)                              comment '维护费用',
  maintainer          varchar(64)                                comment '维护人员',
  fault_description   varchar(500)                               comment '故障描述',
  maintenance_result  varchar(500)                               comment '维护结果',
  create_by           varchar(64)     default ''                 comment '创建者',
  create_time         datetime                                   comment '创建时间',
  update_by           varchar(64)     default ''                 comment '更新者',
  update_time         datetime                                   comment '更新时间',
  remark              varchar(500)                               comment '备注',
  primary key (maintenance_id)
) engine=innodb auto_increment=100 comment = '设备维护记录表';

-- ----------------------------
-- 4、字典类型数据
-- ----------------------------
-- 设备状态字典
insert into sys_dict_type values(11, '设备状态', 'sys_device_status', '0', 'admin', sysdate(), '', null, '设备状态列表');
-- 设备借用状态字典
insert into sys_dict_type values(12, '设备借用状态', 'sys_device_borrow_status', '0', 'admin', sysdate(), '', null, '设备借用状态列表');
-- ----------------------------
-- 5、字典数据
-- ----------------------------
-- 设备状态字典数据
insert into sys_dict_data values(45, 1, '正常', '1', 'sys_device_status', '', 'success', 'N', '0', 'admin', sysdate(), '', null, '设备正常使用中');
insert into sys_dict_data values(46, 2, '借出', '2', 'sys_device_status', '', 'info', 'N', '0', 'admin', sysdate(), '', null, '设备已借出');
insert into sys_dict_data values(47, 3, '维修中', '3', 'sys_device_status', '', 'warning', 'N', '0', 'admin', sysdate(), '', null, '设备正在维修');
insert into sys_dict_data values(48, 4, '报废', '4', 'sys_device_status', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '设备已报废');

-- 设备借用状态字典数据
insert into sys_dict_data values(50, 1, '待审批', '0', 'sys_device_borrow_status', '', 'warning', 'N', '0', 'admin', sysdate(), '', null, '待审批状态');
insert into sys_dict_data values(51, 2, '已批准', '1', 'sys_device_borrow_status', '', 'success', 'N', '0', 'admin', sysdate(), '', null, '已批准状态');
insert into sys_dict_data values(52, 3, '已拒绝', '2', 'sys_device_borrow_status', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '已拒绝状态');
insert into sys_dict_data values(53, 4, '已归还', '3', 'sys_device_borrow_status', '', 'info', 'N', '0', 'admin', sysdate(), '', null, '已归还状态');
insert into sys_dict_data values(54, 5, '逾期', '4', 'sys_device_borrow_status', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '逾期状态');

-- ----------------------------
-- 6、菜单权限
-- ----------------------------
-- 设备管理菜单
insert into sys_menu values('2000', '设备管理', '0', '4', 'device', null, '', 1, 0, 'M', '0', '0', '', 'server', 'admin', sysdate(), '', null, '设备管理菜单');
-- 设备信息菜单
insert into sys_menu values('2001', '设备信息', '2000', '1', 'info', 'device/info/index', '', 1, 0, 'C', '0', '0', 'device:info:list', 'build', 'admin', sysdate(), '', null, '设备信息菜单');
-- 设备信息按钮
insert into sys_menu values('2002', '设备信息查询', '2001', '1', '', '', '', 1, 0, 'F', '0', '0', 'device:info:query', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2003', '设备信息新增', '2001', '2', '', '', '', 1, 0, 'F', '0', '0', 'device:info:add', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2004', '设备信息修改', '2001', '3', '', '', '', 1, 0, 'F', '0', '0', 'device:info:edit', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2005', '设备信息删除', '2001', '4', '', '', '', 1, 0, 'F', '0', '0', 'device:info:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2006', '设备信息导出', '2001', '5', '', '', '', 1, 0, 'F', '0', '0', 'device:info:export', '#', 'admin', sysdate(), '', null, '');

-- 设备借用菜单
insert into sys_menu values('2007', '设备借用', '2000', '4', 'borrow', 'device/borrow/index', '', 1, 0, 'C', '0', '0', 'device:borrow:list', 'time', 'admin', sysdate(), '', null, '设备借用菜单');
-- 设备借用按钮
insert into sys_menu values('2008', '设备借用查询', '2007', '1', '', '', '', 1, 0, 'F', '0', '0', 'device:borrow:query', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2009', '设备借用新增', '2007', '2', '', '', '', 1, 0, 'F', '0', '0', 'device:borrow:add', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2010', '设备借用修改', '2007', '3', '', '', '', 1, 0, 'F', '0', '0', 'device:borrow:edit', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2011', '设备借用删除', '2007', '4', '', '', '', 1, 0, 'F', '0', '0', 'device:borrow:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2012', '设备借用导出', '2007', '5', '', '', '', 1, 0, 'F', '0', '0', 'device:borrow:export', '#', 'admin', sysdate(), '', null, '');