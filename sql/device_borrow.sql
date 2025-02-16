-- 创建设备借用记录表
CREATE TABLE `sys_device_borrow` (
  `borrow_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '借用记录ID',
  `device_id` bigint(20) NOT NULL COMMENT '设备ID',
  `borrower` varchar(64) NOT NULL COMMENT '借用人',
  `borrow_time` datetime NOT NULL COMMENT '借用时间',
  `expected_return_time` datetime NOT NULL COMMENT '预计归还时间',
  `actual_return_time` datetime DEFAULT NULL COMMENT '实际归还时间',
  `borrow_status` char(1) NOT NULL COMMENT '借用状态（0待审批 1已批准 2已拒绝 3已归还 4逾期）',
  `borrow_purpose` varchar(500) DEFAULT NULL COMMENT '借用用途',
  `approver` varchar(64) DEFAULT NULL COMMENT '审批人',
  `approve_time` datetime DEFAULT NULL COMMENT '审批时间',
  `approve_remark` varchar(500) DEFAULT NULL COMMENT '审批备注',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`borrow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT='设备借用记录表';

-- 添加设备借用状态字典
insert into sys_dict_type values(12, '设备借用状态', 'sys_device_borrow_status', '0', 'admin', sysdate(), '', null, '设备借用状态列表');

-- 设备借用状态字典数据
insert into sys_dict_data values(50, 1, '待审批', '0', 'sys_device_borrow_status', '', 'warning', 'N', '0', 'admin', sysdate(), '', null, '待审批状态');
insert into sys_dict_data values(51, 2, '已批准', '1', 'sys_device_borrow_status', '', 'success', 'N', '0', 'admin', sysdate(), '', null, '已批准状态');
insert into sys_dict_data values(52, 3, '已拒绝', '2', 'sys_device_borrow_status', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '已拒绝状态');
insert into sys_dict_data values(53, 4, '已归还', '3', 'sys_device_borrow_status', '', 'info', 'N', '0', 'admin', sysdate(), '', null, '已归还状态');
insert into sys_dict_data values(54, 5, '逾期', '4', 'sys_device_borrow_status', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '逾期状态');

-- 设备借用菜单
insert into sys_menu values('2007', '设备借用', '2000', '4', 'borrow', 'device/borrow/index', '', 1, 0, 'C', '0', '0', 'device:borrow:list', 'time', 'admin', sysdate(), '', null, '设备借用菜单');
-- 设备借用按钮
insert into sys_menu values('2008', '设备借用查询', '2007', '1', '', '', '', 1, 0, 'F', '0', '0', 'device:borrow:query', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2009', '设备借用新增', '2007', '2', '', '', '', 1, 0, 'F', '0', '0', 'device:borrow:add', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2010', '设备借用修改', '2007', '3', '', '', '', 1, 0, 'F', '0', '0', 'device:borrow:edit', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2011', '设备借用删除', '2007', '4', '', '', '', 1, 0, 'F', '0', '0', 'device:borrow:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2012', '设备借用导出', '2007', '5', '', '', '', 1, 0, 'F', '0', '0', 'device:borrow:export', '#', 'admin', sysdate(), '', null, '');

-- 修改设备表，添加借用状态字段
ALTER TABLE sys_device ADD COLUMN borrow_status char(1) DEFAULT '0' COMMENT '借用状态（0未借出 1已借出）';

-- 设备借用状态字典
insert into sys_dict_type values(13, '设备借出状态', 'sys_device_borrow_flag', '0', 'admin', sysdate(), '', null, '设备借出状态列表');

-- 设备借出状态字典数据
insert into sys_dict_data values(55, 1, '未借出', '0', 'sys_device_borrow_flag', '', 'success', 'N', '0', 'admin', sysdate(), '', null, '设备未借出');
