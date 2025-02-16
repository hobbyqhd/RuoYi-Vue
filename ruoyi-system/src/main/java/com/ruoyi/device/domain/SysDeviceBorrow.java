package com.ruoyi.device.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备借用记录对象 sys_device_borrow
 * 
 * @author ruoyi
 */
public class SysDeviceBorrow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 借用记录ID */
    private Long borrowId;

    /** 设备ID */
    @Excel(name = "设备ID")
    private Long deviceId;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String deviceCode;
    
    

    /** 借用人 */
    @Excel(name = "借用人")
    private String borrower;

    /** 借用时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "借用时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date borrowTime;

    /** 预计归还时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "预计归还时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date expectedReturnTime;

    /** 实际归还时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "实际归还时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date actualReturnTime;

    /** 借用状态（0待审批 1已批准 2已拒绝 3已归还 4逾期） */
    @Excel(name = "借用状态", readConverterExp = "0=待审批,1=已批准,2=已拒绝,3=已归还,4=逾期")
    private String borrowStatus;

    /** 借用用途 */
    @Excel(name = "借用用途")
    private String borrowPurpose;

    /** 审批人 */
    @Excel(name = "审批人")
    private String approver;

    /** 审批时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "审批时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date approveTime;

    /** 审批备注 */
    @Excel(name = "审批备注")
    private String approveRemark;

    public void setBorrowId(Long borrowId) 
    {
        this.borrowId = borrowId;
    }

    public Long getBorrowId() 
    {
        return borrowId;
    }

    public void setDeviceId(Long deviceId) 
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId() 
    {
        return deviceId;
    }

    public void setBorrower(String borrower) 
    {
        this.borrower = borrower;
    }

    public String getBorrower() 
    {
        return borrower;
    }

    public void setBorrowTime(Date borrowTime) 
    {
        this.borrowTime = borrowTime;
    }

    public Date getBorrowTime() 
    {
        return borrowTime;
    }

    public void setExpectedReturnTime(Date expectedReturnTime) 
    {
        this.expectedReturnTime = expectedReturnTime;
    }

    public Date getExpectedReturnTime() 
    {
        return expectedReturnTime;
    }

    public void setActualReturnTime(Date actualReturnTime) 
    {
        this.actualReturnTime = actualReturnTime;
    }

    public Date getActualReturnTime() 
    {
        return actualReturnTime;
    }

    public void setBorrowStatus(String borrowStatus) 
    {
        this.borrowStatus = borrowStatus;
    }

    public String getBorrowStatus() 
    {
        return borrowStatus;
    }

    public void setBorrowPurpose(String borrowPurpose) 
    {
        this.borrowPurpose = borrowPurpose;
    }

    public String getBorrowPurpose() 
    {
        return borrowPurpose;
    }

    public void setApprover(String approver) 
    {
        this.approver = approver;
    }

    public String getApprover() 
    {
        return approver;
    }

    public void setApproveTime(Date approveTime) 
    {
        this.approveTime = approveTime;
    }

    public Date getApproveTime() 
    {
        return approveTime;
    }

    public void setApproveRemark(String approveRemark) 
    {
        this.approveRemark = approveRemark;
    }

    public String getApproveRemark() 
    {
        return approveRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("borrowId", getBorrowId())
            .append("deviceId", getDeviceId())
            .append("deviceCode", getDeviceCode())
            .append("borrower", getBorrower())
            .append("borrowTime", getBorrowTime())
            .append("expectedReturnTime", getExpectedReturnTime())
            .append("actualReturnTime", getActualReturnTime())
            .append("borrowStatus", getBorrowStatus())
            .append("borrowPurpose", getBorrowPurpose())
            .append("approver", getApprover())
            .append("approveTime", getApproveTime())
            .append("approveRemark", getApproveRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }
}