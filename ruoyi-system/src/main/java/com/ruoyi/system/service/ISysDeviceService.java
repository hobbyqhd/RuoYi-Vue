package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.SysDevice;

/**
 * 设备管理Service接口
 * 
 * @author ruoyi
 */
public interface ISysDeviceService 
{
    /**
     * 查询设备管理列表
     * 
     * @param sysDevice 设备管理信息
     * @return 设备管理集合
     */
    public List<SysDevice> selectSysDeviceList(SysDevice sysDevice);

    /**
     * 查询设备管理详细信息
     * 
     * @param deviceId 设备管理主键
     * @return 设备管理信息
     */
    public SysDevice selectSysDeviceByDeviceId(Long deviceId);

    /**
     * 新增设备管理
     * 
     * @param sysDevice 设备管理信息
     * @return 结果
     */
    public int insertSysDevice(SysDevice sysDevice);

    /**
     * 修改设备管理
     * 
     * @param sysDevice 设备管理信息
     * @return 结果
     */
    public int updateSysDevice(SysDevice sysDevice);

    /**
     * 批量删除设备管理
     * 
     * @param deviceIds 需要删除的设备管理主键集合
     * @return 结果
     */
    public int deleteSysDeviceByDeviceIds(Long[] deviceIds);

    /**
     * 删除设备管理信息
     * 
     * @param deviceId 设备管理主键
     * @return 结果
     */
    public int deleteSysDeviceByDeviceId(Long deviceId);

    /**
     * 检查设备是否可借用
     * 
     * @param deviceId 设备ID
     * @return 结果
     */
    public boolean checkDeviceCanBeLoan(Long deviceId);

    /**
     * 更新设备状态
     * 
     * @param deviceId 设备ID
     * @param status 设备状态
     * @return 结果
     */
    public int updateDeviceStatus(Long deviceId, String status);

    /**
     * 检查设备是否可以维修
     * 
     * @param deviceId 设备ID
     * @return 结果
     */
    public boolean checkDeviceCanBeMaintained(Long deviceId);

    /**
     * 检查设备是否可以报废
     * 
     * @param deviceId 设备ID
     * @return 结果
     */
    public boolean checkDeviceCanBeDiscarded(Long deviceId);
}