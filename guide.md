# RuoYi-Vue 项目模块功能说明

## 项目概述

RuoYi-Vue是一个基于SpringBoot+Vue的前后端分离的快速开发框架，采用Spring Boot、Spring Security、Redis & JWT等技术栈，支持多终端认证系统和动态权限菜单配置。

## 项目结构

项目采用Maven多模块架构，各模块功能如下：

### ruoyi-ui（前端工程）
- 基于Vue和Element UI构建的前端工程
- 实现了动态路由、权限验证、登录认证等功能
- 提供了丰富的UI组件和页面模板
- 支持主题定制和国际化
- 集成了多种实用插件（如Echarts、Quill编辑器等）

### ruoyi-admin（后端启动模块）
- 项目的启动入口
- 提供系统初始化和配置加载
- 集成SpringBoot配置和Web环境
- 负责统一的异常处理和请求响应

### ruoyi-framework（框架核心模块）
- 提供框架的核心功能支持
- 实现安全认证和权限控制
- 配置Web过滤器和拦截器
- 管理系统任务调度
- 实现数据源和事务管理

### ruoyi-system（系统功能模块）
- 实现系统核心业务逻辑
- 包含用户、角色、菜单管理
- 提供部门、岗位等组织架构管理
- 实现字典、参数等系统配置功能
- 系统监控和日志管理

### ruoyi-common（公共工具模块）
- 提供各个模块的公共支持
- 包含通用工具类和公共配置
- 定义常量、枚举和通用注解
- 提供通用的数据结构和响应对象
- 集成第三方工具和服务

### ruoyi-generator（代码生成模块）
- 提供代码自动生成功能
- 支持自定义模板配置
- 可生成前后端完整代码
- 支持表单和列表等基础功能生成
- 提供代码预览和下载功能

### ruoyi-quartz（任务调度模块）
- 基于Quartz的定时任务管理
- 支持在线任务调度管理
- 提供定时任务执行日志
- 支持多种任务触发方式
- 实现任务暂停、恢复等操作

## 模块依赖关系

- ruoyi-admin依赖framework、system等模块
- ruoyi-framework依赖common模块
- ruoyi-system依赖common模块
- generator和quartz模块依赖common模块
- 所有模块都依赖common模块作为基础支持

## 技术特点

1. 采用前后端分离架构，提高开发效率
2. 使用JWT实现无状态认证
3. 实现动态权限控制和菜单配置
4. 提供代码生成器提升开发效率
5. 集成多种实用功能和开发组件

## 扩展性设计

1. 模块化架构，便于功能扩展
2. 提供丰富的扩展点和钩子函数
3. 支持自定义主题和页面布局
4. 可根据需求选择性集成功能模块
5. 支持多数据源配置和切换

## 后端接口文档

### 系统管理接口

#### 用户管理 (/system/user)
- GET /list - 获取用户列表
- POST /export - 导出用户数据
- POST /importData - 导入用户数据
- POST /importTemplate - 下载用户导入模板

#### 角色管理 (/system/role)
- GET /list - 获取角色列表
- GET /{roleId} - 获取角色详细信息
- POST /add - 新增角色
- PUT /edit - 修改角色
- DELETE /{roleIds} - 删除角色

#### 菜单管理 (/system/menu)
- GET /list - 获取菜单列表
- GET /{menuId} - 获取菜单详细信息
- POST /add - 新增菜单
- PUT /edit - 修改菜单
- DELETE /{menuId} - 删除菜单

#### 部门管理 (/system/dept)
- GET /list - 获取部门列表
- GET /{deptId} - 获取部门详细信息
- POST /add - 新增部门
- PUT /edit - 修改部门
- DELETE /{deptId} - 删除部门

#### 岗位管理 (/system/post)
- GET /list - 获取岗位列表
- GET /{postId} - 获取岗位详细信息
- POST /add - 新增岗位
- PUT /edit - 修改岗位
- DELETE /{postIds} - 删除岗位

### 系统监控接口

#### 在线用户 (/monitor/online)
- GET /list - 获取在线用户列表
- DELETE /{tokenId} - 强制退出用户

#### 操作日志 (/monitor/operlog)
- GET /list - 获取操作日志列表
- DELETE /{operIds} - 删除操作日志
- POST /clean - 清空操作日志

#### 登录日志 (/monitor/logininfor)
- GET /list - 获取登录日志列表
- DELETE /{infoIds} - 删除登录日志
- POST /clean - 清空登录日志

#### 服务监控 (/monitor/server)
- GET / - 获取服务器信息

### 系统工具接口

#### 通用接口 (/common)
- POST /upload - 文件上传
- GET /download - 文件下载
- GET /download/resource - 资源下载

#### 验证码 (/captcha)
- GET /captchaImage - 获取验证码

### 项目管理接口 (/system/project)
- GET /list - 获取项目列表
- GET /{projectId} - 获取项目详细信息
- POST /add - 新增项目
- PUT /edit - 修改项目
- DELETE /{projectIds} - 删除项目