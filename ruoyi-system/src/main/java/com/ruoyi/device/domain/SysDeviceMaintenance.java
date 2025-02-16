package com.ruoyi.device.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备维护记录对象 sys_device_maintenance
 * 
 * @author ruoyi
 */
public class SysDeviceMaintenance extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 维护记录ID */
    private Long maintenanceId;

    /** 设备ID */
    @Excel(name = "设备ID")
    private Long deviceId;

    /** 设备编号  */
    @Excel(name = "设备编号")
    private String deviceCode;

    public void setDeviceCode(String deviceCode) 
    {
        this.deviceCode = deviceCode;
    }

    public String getDeviceCode() 
    {
        return deviceCode;
    }
    /** 设备名称 */
    @Excel(name = "设备名称")
    private String deviceName;

    public void setDeviceName(String deviceName) 
    {
        this.deviceName = deviceName;
    }

    public String getDeviceName() 
    {
        return deviceName;
    }

    /** 维护类型（0保养 1维修 2检查） */
    @Excel(name = "维护类型", readConverterExp = "0=保养,1=维修,2=检查")
    private String maintenanceType;

    /** 维护状态（0待处理 1处理中 2已完成） */
    @Excel(name = "维护状态", readConverterExp = "0=待处理,1=处理中,2=已完成")
    private String maintenanceStatus;

    /** 维护开始时间 */
    @Excel(name = "维护开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 维护结束时间 */
    @Excel(name = "维护结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 维护人员 */
    @Excel(name = "维护人员")
    private String maintainer;

    /** 维护内容 */
    @Excel(name = "维护内容")
    private String maintenanceContent;

    /** 维护结果 */
    @Excel(name = "维护结果")
    private String maintenanceResult;

    /** 维护费用 */
    @Excel(name = "维护费用")
    private Double maintenanceCost;

    /** 故障描述 */
    @Excel(name = "故障描述")
    private String faultDescription;

    /** 备考 */
    @Excel(name = "备考")
    private String remark;
    
    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setFaultDescription(String faultDescription) 
    {
        this.faultDescription = faultDescription;
    }

    public String getFaultDescription() 
    {
        return faultDescription;
    }
    /** 计划时间 */
    @Excel(name = "计划时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date planTime;

    public void setPlanTime(Date planTime) 
    {
        this.planTime = planTime;
    }

    public Date getPlanTime() 
    {
        return planTime;
    }

    public void setMaintenanceId(Long maintenanceId) 
    {
        this.maintenanceId = maintenanceId;
    }

    public Long getMaintenanceId() 
    {
        return maintenanceId;
    }

    public void setDeviceId(Long deviceId) 
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId() 
    {
        return deviceId;
    }

    public void setMaintenanceType(String maintenanceType) 
    {
        this.maintenanceType = maintenanceType;
    }

    public String getMaintenanceType() 
    {
        return maintenanceType;
    }

    public void setMaintenanceStatus(String maintenanceStatus) 
    {
        this.maintenanceStatus = maintenanceStatus;
    }

    public String getMaintenanceStatus() 
    {
        return maintenanceStatus;
    }

    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }

    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }

    public void setMaintainer(String maintainer) 
    {
        this.maintainer = maintainer;
    }

    public String getMaintainer() 
    {
        return maintainer;
    }

    public void setMaintenanceContent(String maintenanceContent) 
    {
        this.maintenanceContent = maintenanceContent;
    }

    public String getMaintenanceContent() 
    {
        return maintenanceContent;
    }

    public void setMaintenanceResult(String maintenanceResult) 
    {
        this.maintenanceResult = maintenanceResult;
    }

    public String getMaintenanceResult() 
    {
        return maintenanceResult;
    }

    public void setMaintenanceCost(Double cost) 
    {
        this.maintenanceCost = cost;
    }

    public Double getMaintenanceCost() 
    {
        return maintenanceCost;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("maintenanceId", getMaintenanceId())
            .append("deviceId", getDeviceId())
            .append("maintenanceType", getMaintenanceType())
            .append("maintenanceStatus", getMaintenanceStatus())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("maintainer", getMaintainer())
            .append("maintenanceContent", getMaintenanceContent())
            .append("maintenanceResult", getMaintenanceResult())
            .append("cost", getMaintenanceCost())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("faultDescription", getFaultDescription())
            .append("maintenanceCost", getMaintenanceCost())
            .toString();
    }
}