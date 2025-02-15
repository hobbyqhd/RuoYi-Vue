package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.SysDeviceLoan;

/**
 * 设备借用记录Service接口
 * 
 * @author ruoyi
 */
public interface ISysDeviceLoanService 
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
     * 批量删除设备借用记录
     * 
     * @param loanIds 需要删除的设备借用记录主键集合
     * @return 结果
     */
    public int deleteSysDeviceLoanByLoanIds(Long[] loanIds);

    /**
     * 删除设备借用记录信息
     * 
     * @param loanId 设备借用记录主键
     * @return 结果
     */
    public int deleteSysDeviceLoanByLoanId(Long loanId);

    /**
     * 申请借用设备
     * 
     * @param sysDeviceLoan 设备借用记录信息
     * @return 结果
     */
    public int applyDeviceLoan(SysDeviceLoan sysDeviceLoan);

    /**
     * 审批设备借用申请
     * 
     * @param loanId 借用记录ID
     * @param status 审批状态
     * @param approver 审批人
     * @param approvalComment 审批意见
     * @return 结果
     */
    public int approveDeviceLoan(Long loanId, String status, String approver, String approvalComment);

    /**
     * 归还设备
     * 
     * @param loanId 借用记录ID
     * @return 结果
     */
    public int returnDevice(Long loanId);

    /**
     * 查询设备未归还的借用记录
     * 
     * @param deviceId 设备ID
     * @return 设备借用记录信息
     */
    public SysDeviceLoan selectUnreturnedLoanByDeviceId(Long deviceId);
}