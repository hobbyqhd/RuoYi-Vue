package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.SysProject;

/**
 * 项目管理Mapper接口
 * 
 * @author ruoyi
 */
public interface SysProjectMapper
{
    /**
     * 查询项目列表
     * 
     * @param project 项目信息
     * @return 项目集合
     */
    public List<SysProject> selectProjectList(SysProject project);

    /**
     * 查询项目信息
     * 
     * @param projectId 项目ID
     * @return 项目信息
     */
    public SysProject selectProjectById(Long projectId);

    /**
     * 新增项目
     * 
     * @param project 项目信息
     * @return 结果
     */
    public int insertProject(SysProject project);

    /**
     * 修改项目
     * 
     * @param project 项目信息
     * @return 结果
     */
    public int updateProject(SysProject project);

    /**
     * 删除项目
     * 
     * @param projectId 项目ID
     * @return 结果
     */
    public int deleteProjectById(Long projectId);

    /**
     * 批量删除项目
     * 
     * @param projectIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectByIds(Long[] projectIds);
}