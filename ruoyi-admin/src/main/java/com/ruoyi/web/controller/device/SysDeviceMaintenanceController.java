package com.ruoyi.web.controller.device;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.device.domain.SysDeviceMaintenance;
import com.ruoyi.device.service.ISysDeviceMaintenanceService;

/**
 * 设备维护记录 信息操作处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/device/maintenance")
public class SysDeviceMaintenanceController extends BaseController
{
    @Autowired
    private ISysDeviceMaintenanceService maintenanceService;

    /**
     * 查询设备维护记录列表
     */
    @PreAuthorize("@ss.hasPermi('maintenance:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysDeviceMaintenance maintenance)
    {
        startPage();
        List<SysDeviceMaintenance> list = maintenanceService.selectSysDeviceMaintenanceList(maintenance);
        return getDataTable(list);
    }

    /**
     * 获取设备维护记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('maintenance:query')")
    @GetMapping(value = "/{maintenanceId}")
    public AjaxResult getInfo(@PathVariable("maintenanceId") Long maintenanceId)
    {
        return success(maintenanceService.selectSysDeviceMaintenanceByMaintenanceId(maintenanceId));
    }

    /**
     * 新增设备维护记录
     */
    @PreAuthorize("@ss.hasPermi('maintenance:add')")
    @Log(title = "设备维护记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysDeviceMaintenance maintenance)
    {
        return toAjax(maintenanceService.insertSysDeviceMaintenance(maintenance));
    }

    /**
     * 修改设备维护记录
     */
    @PreAuthorize("@ss.hasPermi('maintenance:edit')")
    @Log(title = "设备维护记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysDeviceMaintenance maintenance)
    {
        return toAjax(maintenanceService.updateSysDeviceMaintenance(maintenance));
    }

    /**
     * 删除设备维护记录
     */
    @PreAuthorize("@ss.hasPermi('maintenance:remove')")
    @Log(title = "设备维护记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{maintenanceIds}")
    public AjaxResult remove(@PathVariable Long[] maintenanceIds)
    {
        return toAjax(maintenanceService.deleteSysDeviceMaintenanceByMaintenanceIds(maintenanceIds));
    }

    /**
     * 查询设备的维护记录
     */
    @PreAuthorize("@ss.hasPermi('maintenance:list')")
    @GetMapping("/device/{deviceId}")
    public AjaxResult getDeviceMaintenance(@PathVariable("deviceId") Long deviceId)
    {
        return success(maintenanceService.selectMaintenancesByDeviceId(deviceId));
    }

    /**
     * 开始设备维护
     */
    @PreAuthorize("@ss.hasPermi('maintenance:edit')")
    @Log(title = "设备维护记录", businessType = BusinessType.UPDATE)
    @PutMapping("/start/{maintenanceId}")
    public AjaxResult startMaintenance(@PathVariable("maintenanceId") Long maintenanceId)
    {
        return toAjax(maintenanceService.startMaintenance(maintenanceId));
    }

    /**
     * 完成设备维护
     */
    @PreAuthorize("@ss.hasPermi('maintenance:edit')")
    @Log(title = "设备维护记录", businessType = BusinessType.UPDATE)
    @PutMapping("/complete/{maintenanceId}")
    public AjaxResult completeMaintenance(@PathVariable("maintenanceId") Long maintenanceId,
            @RequestBody SysDeviceMaintenance maintenance)
    {
        maintenance.setMaintenanceId(maintenanceId);
        return toAjax(maintenanceService.completeMaintenance(maintenance));
    }
}