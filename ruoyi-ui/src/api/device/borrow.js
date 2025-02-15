import request from '@/utils/request'

// 查询设备借用记录列表
export function listBorrow(query) {
  return request({
    url: '/device/borrow/list',
    method: 'get',
    params: query
  })
}

// 查询设备借用记录详细
export function getBorrow(borrowId) {
  return request({
    url: '/device/borrow/' + borrowId,
    method: 'get'
  })
}

// 新增设备借用记录
export function addBorrow(data) {
  return request({
    url: '/device/borrow',
    method: 'post',
    data: data
  })
}

// 修改设备借用记录
export function updateBorrow(data) {
  return request({
    url: '/device/borrow',
    method: 'put',
    data: data
  })
}

// 删除设备借用记录
export function delBorrow(borrowId) {
  return request({
    url: '/device/borrow/' + borrowId,
    method: 'delete'
  })
}

// 归还设备
export function returnDevice(data) {
  return request({
    url: '/device/borrow/return',
    method: 'post',
    data: data
  })
}