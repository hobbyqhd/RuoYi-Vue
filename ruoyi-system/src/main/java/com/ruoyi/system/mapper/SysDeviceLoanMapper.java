package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.SysDeviceLoan;

/**
 * 设备借用记录Mapper接口
 * 
 * @author ruoyi
 */
public interface SysDeviceLoanMapper 
{
    /**
     * 查询设备借用记录列表
     * 
     * @param sysDeviceLoan 设备借用记录信息
     * @return 设备借用记录集合
     */
    public List<SysDeviceLoan> selectSysDeviceLoanList(SysDeviceLoan sysDeviceLoan);

    /**
     * 查询设备借用记录详细信息
     * 
     * @param loanId 设备借用记录主键
     * @return 设备借用记录信息
     */
    public SysDeviceLoan selectSysDeviceLoanByLoanId(Long loanId);

    /**
     * 新增设备借用记录
     * 
     * @param sysDeviceLoan 设备借用记录信息
     * @return 结果
     */
    public int insertSysDeviceLoan(SysDeviceLoan sysDeviceLoan);

    /**
     * 修改设备借用记录
     * 
     * @param sysDeviceLoan 设备借用记录信息
     * @return 结果
     */
    public int updateSysDeviceLoan(SysDeviceLoan sysDeviceLoan);

    /**
     * 删除设备借用记录
     * 
     * @param loanId 设备借用记录主键
     * @return 结果
     */
    public int deleteSysDeviceLoanByLoanId(Long loanId);

    /**
     * 批量删除设备借用记录
     * 
     * @param loanIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysDeviceLoanByLoanIds(Long[] loanIds);

    /**
     * 根据设备ID查询未归还的借用记录
     * 
     * @param deviceId 设备ID
     * @return 设备借用记录信息
     */
    public SysDeviceLoan selectUnreturnedLoanByDeviceId(Long deviceId);
}