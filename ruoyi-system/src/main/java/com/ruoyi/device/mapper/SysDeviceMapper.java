package com.ruoyi.device.mapper;

import java.util.List;

import com.ruoyi.device.domain.SysDevice;

/**
 * 设备管理Mapper接口
 */
public interface SysDeviceMapper {
    /**
     * 查询设备列表
     * 
     * @param sysDevice 设备信息
     * @return 设备集合
     */
    public List<SysDevice> selectSysDeviceList(SysDevice sysDevice);

    /**
     * 查询设备信息
     * 
     * @param deviceId 设备ID
     * @return 设备信息
     */
    public SysDevice selectSysDeviceByDeviceId(Long deviceId);

    /**
     * 新增设备
     * 
     * @param sysDevice 设备信息
     * @return 结果
     */
    public int insertSysDevice(SysDevice sysDevice);

    /**
     * 修改设备
     * 
     * @param sysDevice 设备信息
     * @return 结果
     */
    public int updateSysDevice(SysDevice sysDevice);

    /**
     * 删除设备
     * 
     * @param deviceId 设备ID
     * @return 结果
     */
    public int deleteSysDeviceByDeviceId(Long deviceId);

    /**
     * 批量删除设备
     * 
     * @param deviceIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysDeviceByDeviceIds(Long[] deviceIds);
}