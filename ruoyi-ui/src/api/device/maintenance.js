import request from '@/utils/request'

// 查询设备维护记录列表
export function listMaintenance(query) {
  return request({
    url: '/device/maintenance/list',
    method: 'get',
    params: query
  })
}

// 查询设备维护记录详细
export function getMaintenance(maintenanceId) {
  return request({
    url: '/device/maintenance/' + maintenanceId,
    method: 'get'
  })
}

// 新增设备维护记录
export function addMaintenance(data) {
  return request({
    url: '/device/maintenance',
    method: 'post',
    data: data
  })
}

// 修改设备维护记录
export function updateMaintenance(data) {
  return request({
    url: '/device/maintenance',
    method: 'put',
    data: data
  })
}

// 删除设备维护记录
export function delMaintenance(maintenanceId) {
  return request({
    url: '/device/maintenance/' + maintenanceId,
    method: 'delete'
  })
}

// 导出设备维护记录
export function exportMaintenance(query) {
  return request({
    url: '/device/maintenance/export',
    method: 'get',
    params: query
  })
}

// 根据设备ID获取维护记录
export function getMaintenancesByDeviceId(deviceId) {
  return request({
    url: '/device/maintenance/device/' + deviceId,
    method: 'get'
  })
}