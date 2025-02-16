package com.ruoyi.device.mapper;

import java.util.List;

import com.ruoyi.device.domain.SysDeviceBorrow;

/**
 * 设备借用记录Mapper接口
 * 
 * @author ruoyi
 */
public interface SysDeviceBorrowMapper
{
    /**
     * 查询设备借用记录
     * 
     * @param borrowId 借用记录ID
     * @return 设备借用记录
     */
    public SysDeviceBorrow selectSysDeviceBorrowById(Long borrowId);

    /**
     * 查询设备借用记录列表
     * 
     * @param sysDeviceBorrow 设备借用记录
     * @return 设备借用记录集合
     */
    public List<SysDeviceBorrow> selectSysDeviceBorrowList(SysDeviceBorrow sysDeviceBorrow);

    /**
     * 新增设备借用记录
     * 
     * @param sysDeviceBorrow 设备借用记录
     * @return 结果
     */
    public int insertSysDeviceBorrow(SysDeviceBorrow sysDeviceBorrow);

    /**
     * 修改设备借用记录
     * 
     * @param sysDeviceBorrow 设备借用记录
     * @return 结果
     */
    public int updateSysDeviceBorrow(SysDeviceBorrow sysDeviceBorrow);

    /**
     * 删除设备借用记录
     * 
     * @param borrowId 借用记录ID
     * @return 结果
     */
    public int deleteSysDeviceBorrowById(Long borrowId);

    /**
     * 批量删除设备借用记录
     * 
     * @param borrowIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysDeviceBorrowByIds(Long[] borrowIds);

    /**
     * 查询设备的当前借用记录
     * 
     * @param deviceId 设备ID
     * @return 设备借用记录
     */
    public SysDeviceBorrow selectCurrentBorrowByDeviceId(Long deviceId);
}