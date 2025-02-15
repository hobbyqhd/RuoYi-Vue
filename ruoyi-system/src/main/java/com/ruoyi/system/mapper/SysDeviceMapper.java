package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.SysDevice;

/**
 * 设备管理Mapper接口
 * 
 * @author ruoyi
 */
public interface SysDeviceMapper 
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
     * 删除设备管理
     * 
     * @param deviceId 设备管理主键
     * @return 结果
     */
    public int deleteSysDeviceByDeviceId(Long deviceId);

    /**
     * 批量删除设备管理
     * 
     * @param deviceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysDeviceByDeviceIds(Long[] deviceIds);
}