package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备管理对象 sys_device
 * 
 * @author ruoyi
 */
public class SysDevice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备ID */
    private Long deviceId;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String deviceCode;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String deviceName;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private String deviceType;

    /** 设备状态（0正常 1借出 2维修中 3报废） */
    @Excel(name = "设备状态", readConverterExp = "0=正常,1=借出,2=维修中,3=报废")
    private String deviceStatus;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String specification;

    /** 购入日期 */
    @Excel(name = "购入日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String purchaseDate;

    /** 购入价格 */
    @Excel(name = "购入价格")
    private String purchasePrice;

    /** 制造商 */
    @Excel(name = "制造商")
    private String manufacturer;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /** 型号 */
    @Excel(name = "型号")
    private String model;

    public String getModel() {
        return model;
    }

    /**  */
    private String modelNumber;
    public String getModelNumber() {
        return modelNumber;
    }
    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public SysDevice(String location) {
        this.location = location;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    /** 保管人 */
    @Excel(name = "保管人")
    private String custodian;

    /** 存放位置 */
    @Excel(name = "存放位置")
    private String location;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setDeviceId(Long deviceId) 
    {
        this.deviceId = deviceId;
    }
 
    /** 无参构造方法 */
    public SysDevice() {}

    public Long getDeviceId() 
    {
        return deviceId;
    }

    public void setDeviceCode(String deviceCode) 
    {
        this.deviceCode = deviceCode;
    }

    public String getDeviceCode() 
    {
        return deviceCode;
    }

    public void setDeviceName(String deviceName) 
    {
        this.deviceName = deviceName;
    }

    public String getDeviceName() 
    {
        return deviceName;
    }

    public void setDeviceType(String deviceType) 
    {
        this.deviceType = deviceType;
    }

    public String getDeviceType() 
    {
        return deviceType;
    }

    public void setDeviceStatus(String deviceStatus) 
    {
        this.deviceStatus = deviceStatus;
    }

    public String getDeviceStatus() 
    {
        return deviceStatus;
    }

    public void setSpecification(String specification) 
    {
        this.specification = specification;
    }

    public String getSpecification() 
    {
        return specification;
    }

    public void setPurchaseDate(String purchaseDate) 
    {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseDate() 
    {
        return purchaseDate;
    }

    public void setCustodian(String custodian) 
    {
        this.custodian = custodian;
    }

    public String getCustodian() 
    {
        return custodian;
    }

    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deviceId", getDeviceId())
            .append("deviceCode", getDeviceCode())
            .append("deviceName", getDeviceName())
            .append("deviceType", getDeviceType())
            .append("specification", getSpecification())
            .append("purchaseDate", getPurchaseDate())
            .append("custodian", getCustodian())
            .append("location", getLocation())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}