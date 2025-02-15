package com.ruoyi.device.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.device.domain.SysDeviceMaintenance;
import com.ruoyi.device.mapper.SysDeviceMaintenanceMapper;
import com.ruoyi.device.service.ISysDeviceMaintenanceService;
import com.ruoyi.device.service.ISysDeviceService;

/**
 * 设备维护Service业务层处理
 */
@Service
public class SysDeviceMaintenanceServiceImpl implements ISysDeviceMaintenanceService {
    @Autowired
    private SysDeviceMaintenanceMapper maintenanceMapper;

    @Autowired
    private ISysDeviceService deviceService;

    /**
     * 查询设备维护记录列表
     */
    @Override
    public List<SysDeviceMaintenance> selectSysDeviceMaintenanceList(SysDeviceMaintenance maintenance) {
        return maintenanceMapper.selectSysDeviceMaintenanceList(maintenance);
    }

    /**
     * 查询设备维护记录详细信息
     */
    @Override
    public SysDeviceMaintenance selectSysDeviceMaintenanceByMaintenanceId(Long maintenanceId) {
        return maintenanceMapper.selectSysDeviceMaintenanceByMaintenanceId(maintenanceId);
    }

    /**
     * 新增设备维护记录
     */
    @Override
    public int insertSysDeviceMaintenance(SysDeviceMaintenance maintenance) {
        return maintenanceMapper.insertSysDeviceMaintenance(maintenance);
    }

    /**
     * 修改设备维护记录
     */
    @Override
    public int updateSysDeviceMaintenance(SysDeviceMaintenance maintenance) {
        return maintenanceMapper.updateSysDeviceMaintenance(maintenance);
    }

    /**
     * 删除设备维护记录信息
     */
    @Override
    public int deleteSysDeviceMaintenanceByMaintenanceIds(Long[] maintenanceIds) {
        return maintenanceMapper.deleteSysDeviceMaintenanceByMaintenanceIds(maintenanceIds);
    }

    /**
     * 删除设备维护记录信息
     */
    @Override
    public int deleteSysDeviceMaintenanceByMaintenanceId(Long maintenanceId) {
        return maintenanceMapper.deleteSysDeviceMaintenanceByMaintenanceId(maintenanceId);
    }

    /**
     * 查询设备的维护记录
     */
    @Override
    public List<SysDeviceMaintenance> selectMaintenancesByDeviceId(Long deviceId) {
        return maintenanceMapper.selectMaintenancesByDeviceId(deviceId);
    }

    /**
     * 开始设备维护
     */
    @Override
    public int startMaintenance(Long maintenanceId) {
        SysDeviceMaintenance maintenance = maintenanceMapper.selectSysDeviceMaintenanceByMaintenanceId(maintenanceId);
        if (maintenance != null) {
            maintenance.setStartTime(new java.sql.Date(System.currentTimeMillis()));
            maintenance.setMaintenanceStatus("1"); // 设置状态为维护中
            deviceService.updateDeviceStatus(maintenance.getDeviceId(), "2"); // 设置设备状态为维护中
            return maintenanceMapper.updateSysDeviceMaintenance(maintenance);
        }
        return 0;
    }

    /**
     * 完成设备维护
     */
    @Override
    public int completeMaintenance(SysDeviceMaintenance maintenance) {
        SysDeviceMaintenance existingMaintenance = maintenanceMapper.selectSysDeviceMaintenanceByMaintenanceId(maintenance.getMaintenanceId());
        if (existingMaintenance != null) {
            existingMaintenance.setEndTime(new java.sql.Date(System.currentTimeMillis()));
            existingMaintenance.setResult(maintenance.getResult());
            existingMaintenance.setCost(maintenance.getCost());
            existingMaintenance.setMaintenanceStatus("2"); // 设置状态为已完成
            deviceService.updateDeviceStatus(existingMaintenance.getDeviceId(), "0"); // 设置设备状态为正常
            return maintenanceMapper.updateSysDeviceMaintenance(existingMaintenance);
        }
        return 0;
    }
}