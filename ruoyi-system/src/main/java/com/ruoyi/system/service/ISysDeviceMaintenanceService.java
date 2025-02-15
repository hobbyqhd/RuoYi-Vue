package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.SysDeviceMaintenance;

/**
 * 设备维护记录Service接口
 * 
 * @author ruoyi
 */
public interface ISysDeviceMaintenanceService 
{
    /**
     * 查询设备维护记录列表
     * 
     * @param sysDeviceMaintenance 设备维护记录信息
     * @return 设备维护记录集合
     */
    public List<SysDeviceMaintenance> selectSysDeviceMaintenanceList(SysDeviceMaintenance sysDeviceMaintenance);

    /**
     * 查询设备维护记录详细信息
     * 
     * @param maintenanceId 设备维护记录主键
     * @return 设备维护记录信息
     */
    public SysDeviceMaintenance selectSysDeviceMaintenanceByMaintenanceId(Long maintenanceId);

    /**
     * 新增设备维护记录
     * 
     * @param sysDeviceMaintenance 设备维护记录信息
     * @return 结果
     */
    public int insertSysDeviceMaintenance(SysDeviceMaintenance sysDeviceMaintenance);

    /**
     * 修改设备维护记录
     * 
     * @param sysDeviceMaintenance 设备维护记录信息
     * @return 结果
     */
    public int updateSysDeviceMaintenance(SysDeviceMaintenance sysDeviceMaintenance);

    /**
     * 批量删除设备维护记录
     * 
     * @param maintenanceIds 需要删除的设备维护记录主键集合
     * @return 结果
     */
    public int deleteSysDeviceMaintenanceByMaintenanceIds(Long[] maintenanceIds);

    /**
     * 删除设备维护记录信息
     * 
     * @param maintenanceId 设备维护记录主键
     * @return 结果
     */
    public int deleteSysDeviceMaintenanceByMaintenanceId(Long maintenanceId);

    /**
     * 查询设备的维护记录
     * 
     * @param deviceId 设备ID
     * @return 设备维护记录集合
     */
    public List<SysDeviceMaintenance> selectMaintenancesByDeviceId(Long deviceId);

    /**
     * 开始设备维护
     * 
     * @param maintenanceId 维护记录ID
     * @return 结果
     */
    public int startMaintenance(Long maintenanceId);

    /**
     * 完成设备维护
     * 
     * @param maintenance 维护记录信息
     * @return 结果
     */
    public int completeMaintenance(SysDeviceMaintenance maintenance);
}