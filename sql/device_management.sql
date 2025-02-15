-- ----------------------------
-- 1、设备信息表
-- ----------------------------
drop table if exists sys_device;
create table sys_device (
  device_id           bigint(20)      not null auto_increment    comment '设备ID',
  device_name         varchar(50)     not null                   comment '设备名称',
  device_code         varchar(64)     not null                   comment '设备编号',
  device_type         varchar(20)     not null                   comment '设备类型',
  device_status       char(1)         default '0'                comment '设备状态（0正常 1借出 2维修中 3报废）',
  purchase_date       datetime                                    comment '购入日期',
  purchase_price      decimal(10,2)                              comment '购入价格',
  manufacturer        varchar(100)                               comment '制造商',
  model_number        varchar(100)                               comment '型号',
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
  borrow_id           bigint(20)      not null auto_increment    comment '借用ID',
  device_id           bigint(20)      not null                   comment '设备ID',
  user_id             bigint(20)      not null                   comment '借用人ID',
  borrow_time         datetime        not null                   comment '借用时间',
  expected_return_time datetime                                  comment '预计归还时间',
  actual_return_time  datetime                                   comment '实际归还时间',
  borrow_status       char(1)         default '0'                comment '借用状态（0待审批 1已批准 2已拒绝 3已归还）',
  borrow_purpose      varchar(500)                               comment '借用用途',
  approval_by         varchar(64)                                comment '审批人',
  approval_time       datetime                                   comment '审批时间',
  approval_comment    varchar(500)                               comment '审批意见',
  create_by           varchar(64)     default ''                 comment '创建者',
  create_time         datetime                                   comment '创建时间',
  update_by           varchar(64)     default ''                 comment '更新者',
  update_time         datetime                                   comment '更新时间',
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
  maintenance_content varchar(500)                               comment '维护内容',
  create_by           varchar(64)     default ''                 comment '创建者',
  create_time         datetime                                   comment '创建时间',
  update_by           varchar(64)     default ''                 comment '更新者',
  update_time         datetime                                   comment '更新时间',
  primary key (maintenance_id)
) engine=innodb auto_increment=100 comment = '设备维护记录表';