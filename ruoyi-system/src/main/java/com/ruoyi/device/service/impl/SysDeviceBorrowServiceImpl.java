package com.ruoyi.device.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.device.domain.SysDevice;
import com.ruoyi.device.domain.SysDeviceBorrow;
import com.ruoyi.device.mapper.SysDeviceBorrowMapper;
import com.ruoyi.device.mapper.SysDeviceMapper;
import com.ruoyi.device.service.ISysDeviceBorrowService;

/**
 * 设备借用记录Service业务层处理
 * 
 * @author ruoyi
 */
@Service
public class SysDeviceBorrowServiceImpl implements ISysDeviceBorrowService
{
    @Autowired
    private SysDeviceBorrowMapper sysDeviceBorrowMapper;

    @Autowired
    private SysDeviceMapper sysDeviceMapper;

    /**
     * 查询设备借用记录
     * 
     * @param borrowId 借用记录ID
     * @return 设备借用记录
     */
    @Override
    public SysDeviceBorrow selectSysDeviceBorrowById(Long borrowId)
    {
        return sysDeviceBorrowMapper.selectSysDeviceBorrowById(borrowId);
    }

    /**
     * 查询设备借用记录列表
     * 
     * @param sysDeviceBorrow 设备借用记录
     * @return 设备借用记录
     */
    @Override
    public List<SysDeviceBorrow> selectSysDeviceBorrowList(SysDeviceBorrow sysDeviceBorrow)
    {
        return sysDeviceBorrowMapper.selectSysDeviceBorrowList(sysDeviceBorrow);
    }

    /**
     * 新增设备借用记录
     * 
     * @param sysDeviceBorrow 设备借用记录
     * @return 结果
     */
    @Override
    @Transactional
    public int insertSysDeviceBorrow(SysDeviceBorrow sysDeviceBorrow)
    {
        // 检查设备状态
        SysDevice device = sysDeviceMapper.selectSysDeviceByDeviceId(sysDeviceBorrow.getDeviceId());
        if (device == null)
        {
            throw new RuntimeException("设备不存在");
        }
        if ("1".equals(device.getBorrowStatus()))
        {
            throw new RuntimeException("该设备已被借用，无法重复借用");
        }
        if ("1".equals(device.getDeviceStatus()))
        {
            throw new RuntimeException("设备当前处于停用状态，无法借用");
        }
        if (!"0".equals(device.getDeviceStatus()))
        {
            throw new RuntimeException("设备当前状态异常，请联系管理员");
        }

        // 检查设备是否已被借用
        SysDeviceBorrow currentBorrow = sysDeviceBorrowMapper.selectCurrentBorrowByDeviceId(sysDeviceBorrow.getDeviceId());
        if (currentBorrow != null)
        {
            throw new RuntimeException("该设备已被借用");
        }

        // 验证预计归还时间
        Date now = DateUtils.getNowDate();
        if (sysDeviceBorrow.getExpectedReturnTime() == null || 
            sysDeviceBorrow.getExpectedReturnTime().before(now))
        {
            throw new RuntimeException("预计归还时间不能为空且必须晚于当前时间");
        }

        sysDeviceBorrow.setBorrowTime(now);
        sysDeviceBorrow.setBorrowStatus("0"); // 待审批
        sysDeviceBorrow.setCreateBy(SecurityUtils.getUsername());
        sysDeviceBorrow.setCreateTime(now);
        return sysDeviceBorrowMapper.insertSysDeviceBorrow(sysDeviceBorrow);
    }

    /**
     * 修改设备借用记录
     * 
     * @param sysDeviceBorrow 设备借用记录
     * @return 结果
     */
    @Override
    public int updateSysDeviceBorrow(SysDeviceBorrow sysDeviceBorrow)
    {
        sysDeviceBorrow.setUpdateBy(SecurityUtils.getUsername());
        sysDeviceBorrow.setUpdateTime(DateUtils.getNowDate());
        return sysDeviceBorrowMapper.updateSysDeviceBorrow(sysDeviceBorrow);
    }

    /**
     * 批量删除设备借用记录
     * 
     * @param borrowIds 需要删除的设备借用记录ID
     * @return 结果
     */
    @Override
    public int deleteSysDeviceBorrowByIds(Long[] borrowIds)
    {
        return sysDeviceBorrowMapper.deleteSysDeviceBorrowByIds(borrowIds);
    }

    /**
     * 删除设备借用记录信息
     * 
     * @param borrowId 设备借用记录ID
     * @return 结果
     */
    @Override
    public int deleteSysDeviceBorrowById(Long borrowId)
    {
        return sysDeviceBorrowMapper.deleteSysDeviceBorrowById(borrowId);
    }

    /**
     * 审批设备借用申请
     * 
     * @param sysDeviceBorrow 设备借用记录
     * @return 结果
     */
    @Override
    @Transactional
    public int approveSysDeviceBorrow(SysDeviceBorrow sysDeviceBorrow)
    {
        // 获取原借用记录
        SysDeviceBorrow originalBorrow = sysDeviceBorrowMapper.selectSysDeviceBorrowById(sysDeviceBorrow.getBorrowId());
        if (originalBorrow == null)
        {
            throw new RuntimeException("借用记录不存在");
        }

        // 检查记录状态是否为待审批
        if (!"0".equals(originalBorrow.getBorrowStatus()))
        {
            throw new RuntimeException("该记录已审批，不能重复审批");
        }

        // 如果审批通过，检查设备当前是否可借
        if ("1".equals(sysDeviceBorrow.getBorrowStatus()))
        {
            SysDeviceBorrow currentBorrow = sysDeviceBorrowMapper.selectCurrentBorrowByDeviceId(originalBorrow.getDeviceId());
            if (currentBorrow != null && !currentBorrow.getBorrowId().equals(originalBorrow.getBorrowId()))
            {
                throw new RuntimeException("该设备已被其他人借用");
            }

            // 更新设备状态为已借出
            SysDevice device = sysDeviceMapper.selectSysDeviceByDeviceId(originalBorrow.getDeviceId());
            if (device == null)
            {
                throw new RuntimeException("设备不存在");
            }
            if (!"0".equals(device.getDeviceStatus()))
            {
                throw new RuntimeException("设备当前状态不可借用");
            }
            device.setDeviceStatus("1"); // 已借出
            sysDeviceMapper.updateSysDevice(device);
        }

        sysDeviceBorrow.setApprover(SecurityUtils.getUsername());
        sysDeviceBorrow.setApproveTime(DateUtils.getNowDate());
        sysDeviceBorrow.setUpdateBy(SecurityUtils.getUsername());
        sysDeviceBorrow.setUpdateTime(DateUtils.getNowDate());

        return sysDeviceBorrowMapper.updateSysDeviceBorrow(sysDeviceBorrow);
    }

    /**
     * 归还设备
     * 
     * @param borrowId 借用记录ID
     * @return 结果
     */
    @Override
    @Transactional
    public int returnDevice(Long borrowId)
    {
        // 获取借用记录
        SysDeviceBorrow borrow = sysDeviceBorrowMapper.selectSysDeviceBorrowById(borrowId);
        if (borrow == null)
        {
            throw new RuntimeException("借用记录不存在");
        }

        // 检查记录状态是否为已批准或逾期
        if (!"1".equals(borrow.getBorrowStatus()) && !"5".equals(borrow.getBorrowStatus()))
        {
            throw new RuntimeException("只有已批准或逾期状态的借用记录才能执行归还操作");
        }

        Date now = DateUtils.getNowDate();
        // 判断是否逾期归还
        if (borrow.getExpectedReturnTime() != null && now.after(borrow.getExpectedReturnTime()))
        {
            // 更新为逾期归还状态
            borrow.setBorrowStatus("4"); // 逾期归还
            // 记录逾期信息
            borrow.setRemark("设备逾期归还，预计归还时间：" + 
                DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, borrow.getExpectedReturnTime()) + 
                "，实际归还时间：" + 
                DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, now));
        }
        else
        {
            borrow.setBorrowStatus("3"); // 正常归还
        }

        borrow.setActualReturnTime(now);
        borrow.setUpdateBy(SecurityUtils.getUsername());
        borrow.setUpdateTime(now);

        // 更新设备状态
        SysDevice device = sysDeviceMapper.selectSysDeviceByDeviceId(borrow.getDeviceId());
        if (device == null)
        {
            throw new RuntimeException("设备不存在");
        }
        device.setDeviceStatus("0"); // 设置为可用状态
        device.setBorrowStatus("0"); // 设置为未借出状态
        sysDeviceMapper.updateSysDevice(device);

        return sysDeviceBorrowMapper.updateSysDeviceBorrow(borrow);
    }

    /**
     * 查询设备的当前借用记录
     * 
     * @param deviceId 设备ID
     * @return 设备借用记录
     */
    @Override
    public SysDeviceBorrow selectCurrentBorrowByDeviceId(Long deviceId)
    {
        return sysDeviceBorrowMapper.selectCurrentBorrowByDeviceId(deviceId);
    }
}