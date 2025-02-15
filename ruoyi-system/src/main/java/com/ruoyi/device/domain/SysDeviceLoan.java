package com.ruoyi.device.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备借用记录对象 sys_device_loan
 * 
 * @author ruoyi
 */
public class SysDeviceLoan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 借用记录ID */
    private Long loanId;

    /** 设备ID */
    @Excel(name = "设备ID")
    private Long deviceId;

    /** 借用人 */
    @Excel(name = "借用人")
    private String borrower;

    /** 借用时间 */
    @Excel(name = "借用时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private String loanTime;

    /** 预计归还时间 */
    @Excel(name = "预计归还时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private String expectedReturnTime;

    /** 实际归还时间 */
    @Excel(name = "实际归还时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private String actualReturnTime;

    /** 借用用途 */
    @Excel(name = "借用用途")
    private String purpose;

    /** 借用状态（0待审批 1已批准 2已拒绝 3已归还 4逾期未还） */
    @Excel(name = "借用状态", readConverterExp = "0=待审批,1=已批准,2=已拒绝,3=已归还,4=逾期未还")
    private String status;

    /** 审批人 */
    @Excel(name = "审批人")
    private String approver;

    /** 审批时间 */
    @Excel(name = "审批时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private String approvalTime;

    /** 审批意见 */
    @Excel(name = "审批意见")
    private String approvalComment;

    public void setLoanId(Long loanId) 
    {
        this.loanId = loanId;
    }

    public Long getLoanId() 
    {
        return loanId;
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

    public void setLoanTime(String loanTime) 
    {
        this.loanTime = loanTime;
    }

    public String getLoanTime() 
    {
        return loanTime;
    }

    public void setExpectedReturnTime(String expectedReturnTime) 
    {
        this.expectedReturnTime = expectedReturnTime;
    }

    public String getExpectedReturnTime() 
    {
        return expectedReturnTime;
    }

    public void setActualReturnTime(String actualReturnTime) 
    {
        this.actualReturnTime = actualReturnTime;
    }

    public String getActualReturnTime() 
    {
        return actualReturnTime;
    }

    public void setPurpose(String purpose) 
    {
        this.purpose = purpose;
    }

    public String getPurpose() 
    {
        return purpose;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setApprover(String approver) 
    {
        this.approver = approver;
    }

    public String getApprover() 
    {
        return approver;
    }

    public void setApprovalTime(String approvalTime) 
    {
        this.approvalTime = approvalTime;
    }

    public String getApprovalTime() 
    {
        return approvalTime;
    }

    public void setApprovalComment(String approvalComment) 
    {
        this.approvalComment = approvalComment;
    }

    public String getApprovalComment() 
    {
        return approvalComment;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("loanId", getLoanId())
            .append("deviceId", getDeviceId())
            .append("borrower", getBorrower())
            .append("loanTime", getLoanTime())
            .append("expectedReturnTime", getExpectedReturnTime())
            .append("actualReturnTime", getActualReturnTime())
            .append("purpose", getPurpose())
            .append("status", getStatus())
            .append("approver", getApprover())
            .append("approvalTime", getApprovalTime())
            .append("approvalComment", getApprovalComment())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}