package com.ruoyi.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.domain.SysDevice;
import com.ruoyi.system.mapper.SysDeviceMapper;
import com.ruoyi.system.service.ISysDeviceService;

/**
 * 设备管理Service业务层处理
 * 
 */
@Service
public class SysDeviceServiceImpl implements ISysDeviceService
{
    /** 设备状态：正常 */
    private static final String DEVICE_STATUS_NORMAL = "0";

    /** 设备状态：借出 */
    private static final String DEVICE_STATUS_LOANED = "1";

    /** 设备状态：维修中 */
    private static final String DEVICE_STATUS_MAINTAINING = "2";

    /** 设备状态：报废 */
    private static final String DEVICE_STATUS_DISCARDED = "3";
    @Autowired
    private SysDeviceMapper sysDeviceMapper;

    /**
     * 查询设备管理列表
     * 
     * @param sysDevice 设备管理信息
     * @return 设备管理集合
     */
    @Override
    public List<SysDevice> selectSysDeviceList(SysDevice sysDevice)
    {
        return sysDeviceMapper.selectSysDeviceList(sysDevice);
    }

    /**
     * 查询设备管理详细信息
     * 
     * @param deviceId 设备管理主键
     * @return 设备管理信息
     */
    @Override
    public SysDevice selectSysDeviceByDeviceId(Long deviceId)
    {
        return sysDeviceMapper.selectSysDeviceByDeviceId(deviceId);
    }

    /**
     * 新增设备管理
     * 
     * @param sysDevice 设备管理信息
     * @return 结果
     */
    @Override
    public int insertSysDevice(SysDevice sysDevice)
    {
        return sysDeviceMapper.insertSysDevice(sysDevice);
    }

    /**
     * 修改设备管理
     * 
     * @param sysDevice 设备管理信息
     * @return 结果
     */
    @Override
    public int updateSysDevice(SysDevice sysDevice)
    {
        return sysDeviceMapper.updateSysDevice(sysDevice);
    }

    /**
     * 删除设备管理信息
     * 
     * @param deviceId 设备管理主键
     * @return 结果
     */
    @Override
    public int deleteSysDeviceByDeviceId(Long deviceId)
    {
        return sysDeviceMapper.deleteSysDeviceByDeviceId(deviceId);
    }

    /**
     * 批量删除设备管理
     * 
     * @param deviceIds 需要删除的设备管理主键集合
     * @return 结果
     */
    @Override
    public int deleteSysDeviceByDeviceIds(Long[] deviceIds)
    {
        return sysDeviceMapper.deleteSysDeviceByDeviceIds(deviceIds);
    }

    /**
     * 检查设备是否可借用
     * 
     * @param deviceId 设备ID
     * @return 结果
     */
    @Override
    public boolean checkDeviceCanBeLoan(Long deviceId)
    {
        SysDevice device = sysDeviceMapper.selectSysDeviceByDeviceId(deviceId);
        return device != null && DEVICE_STATUS_NORMAL.equals(device.getDeviceStatus());
    }

    /**
     * 更新设备状态
     * 
     * @param deviceId 设备ID
     * @param status 设备状态
     * @return 结果
     */
    @Override
    public int updateDeviceStatus(Long deviceId, String status)
    {
        SysDevice device = sysDeviceMapper.selectSysDeviceByDeviceId(deviceId);
        if (device == null)
        {
            return 0;
        }
        device.setDeviceStatus(status);
        return sysDeviceMapper.updateSysDevice(device);
    }

    /**
     * 检查设备是否可以维修
     * 
     * @param deviceId 设备ID
     * @return 结果
     */
    @Override
    public boolean checkDeviceCanBeMaintained(Long deviceId)
    {
        SysDevice device = sysDeviceMapper.selectSysDeviceByDeviceId(deviceId);
        return device != null && !DEVICE_STATUS_DISCARDED.equals(device.getDeviceStatus()); // 非报废设备可以维修
    }

    /**
     * 检查设备是否可以报废
     * 
     * @param deviceId 设备ID
     * @return 结果
     */
    @Override
    public boolean checkDeviceCanBeDiscarded(Long deviceId)
    {
        SysDevice device = sysDeviceMapper.selectSysDeviceByDeviceId(deviceId);
        return device != null && !DEVICE_STATUS_LOANED.equals(device.getDeviceStatus()); // 非借出状态可以报废
    }
}
