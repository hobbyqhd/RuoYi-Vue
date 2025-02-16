package com.ruoyi.web.controller.device;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.device.domain.SysDeviceBorrow;
import com.ruoyi.device.service.ISysDeviceBorrowService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备借用记录Controller
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/device/borrow")
public class SysDeviceBorrowController extends BaseController
{
    @Autowired
    private ISysDeviceBorrowService sysDeviceBorrowService;

    /**
     * 查询设备借用记录列表
     */
    @PreAuthorize("@ss.hasPermi('device:borrow:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysDeviceBorrow sysDeviceBorrow)
    {
        startPage();
        List<SysDeviceBorrow> list = sysDeviceBorrowService.selectSysDeviceBorrowList(sysDeviceBorrow);
        return getDataTable(list);
    }

    /**
     * 导出设备借用记录列表
     */
    @PreAuthorize("@ss.hasPermi('device:borrow:export')")
    @Log(title = "设备借用记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysDeviceBorrow sysDeviceBorrow)
    {
        List<SysDeviceBorrow> list = sysDeviceBorrowService.selectSysDeviceBorrowList(sysDeviceBorrow);
        ExcelUtil<SysDeviceBorrow> util = new ExcelUtil<SysDeviceBorrow>(SysDeviceBorrow.class);
        return util.exportExcel(list, "设备借用记录");
    }

    /**
     * 获取设备借用记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('device:borrow:query')")
    @GetMapping(value = "/{borrowId}")
    public AjaxResult getInfo(@PathVariable("borrowId") Long borrowId)
    {
        return success(sysDeviceBorrowService.selectSysDeviceBorrowById(borrowId));
    }

    /**
     * 新增设备借用记录
     */
    @PreAuthorize("@ss.hasPermi('device:borrow:add')")
    @Log(title = "设备借用记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysDeviceBorrow sysDeviceBorrow)
    {
        return toAjax(sysDeviceBorrowService.insertSysDeviceBorrow(sysDeviceBorrow));
    }

    /**
     * 修改设备借用记录
     */
    @PreAuthorize("@ss.hasPermi('device:borrow:edit')")
    @Log(title = "设备借用记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysDeviceBorrow sysDeviceBorrow)
    {
        return toAjax(sysDeviceBorrowService.updateSysDeviceBorrow(sysDeviceBorrow));
    }

    /**
     * 删除设备借用记录
     */
    @PreAuthorize("@ss.hasPermi('device:borrow:remove')")
    @Log(title = "设备借用记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{borrowIds}")
    public AjaxResult remove(@PathVariable Long[] borrowIds)
    {
        return toAjax(sysDeviceBorrowService.deleteSysDeviceBorrowByIds(borrowIds));
    }

    /**
     * 审批设备借用申请
     */
    @PreAuthorize("@ss.hasPermi('device:borrow:edit')")
    @Log(title = "设备借用审批", businessType = BusinessType.UPDATE)
    @PutMapping("/approve")
    public AjaxResult approve(@RequestBody SysDeviceBorrow sysDeviceBorrow)
    {
        return toAjax(sysDeviceBorrowService.approveSysDeviceBorrow(sysDeviceBorrow));
    }

    /**
     * 归还设备
     */
    @PreAuthorize("@ss.hasPermi('device:borrow:edit')")
    @Log(title = "设备归还", businessType = BusinessType.UPDATE)
    @PutMapping("/return/{borrowId}")
    public AjaxResult returnDevice(@PathVariable("borrowId") Long borrowId)
    {
        return toAjax(sysDeviceBorrowService.returnDevice(borrowId));
    }
}