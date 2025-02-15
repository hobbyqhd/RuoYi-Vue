package com.ruoyi.device.mapper;

import java.util.List;

import com.ruoyi.device.domain.SysDeviceMaintenance;

/**
 * 设备维护记录Mapper接口
 * 
 * @author ruoyi
 */
public interface SysDeviceMaintenanceMapper 
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
     * 删除设备维护记录
     * 
     * @param maintenanceId 设备维护记录主键
     * @return 结果
     */
    public int deleteSysDeviceMaintenanceByMaintenanceId(Long maintenanceId);

    /**
     * 批量删除设备维护记录
     * 
     * @param maintenanceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysDeviceMaintenanceByMaintenanceIds(Long[] maintenanceIds);

    /**
     * 查询设备的维护记录
     * 
     * @param deviceId 设备ID
     * @return 设备维护记录集合
     */
    public List<SysDeviceMaintenance> selectMaintenancesByDeviceId(Long deviceId);
}