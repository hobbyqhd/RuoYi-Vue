package com.ruoyi.web.controller.system;

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
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.SysProject;
import com.ruoyi.system.service.ISysProjectService;

/**
 * 项目管理Controller
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/project")
public class SysProjectController extends BaseController
{
    @Autowired
    private ISysProjectService projectService;

    /**
     * 获取项目列表
     */
    @PreAuthorize("@ss.hasPermi('system:project:list')")
    @GetMapping("/list")
    @Log(title = "项目管理", businessType = BusinessType.OTHER)
    public TableDataInfo list(SysProject project)
    {
        logger.debug("开始查询项目列表，查询参数：{}", project);

        startPage();
        List<SysProject> list = projectService.selectProjectList(project);
        logger.debug("查询到{}条项目记录，返回数据：{}", list.size(), list);
        return getDataTable(list);
    }

    /**
     * 获取项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:project:query')")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") Long projectId)
    {
        return success(projectService.selectProjectById(projectId));
    }

    /**
     * 新增项目
     */
    @PreAuthorize("@ss.hasPermi('system:project:add')")
    @Log(title = "项目管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysProject project)
    {
        project.setCreateBy(SecurityUtils.getUsername());
        return toAjax(projectService.insertProject(project));
    }

    /**
     * 修改项目
     */
    @PreAuthorize("@ss.hasPermi('system:project:edit')")
    @Log(title = "项目管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysProject project)
    {
        project.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(projectService.updateProject(project));
    }

    /**
     * 删除项目
     */
    @PreAuthorize("@ss.hasPermi('system:project:remove')")
    @Log(title = "项目管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable Long[] projectIds)
    {
        return toAjax(projectService.deleteProjectByIds(projectIds));
    }
}