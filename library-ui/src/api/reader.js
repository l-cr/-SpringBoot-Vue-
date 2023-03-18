import request from '@/utils/request'

// 查询读者列表
export function listReader(params) {
  return request({
    url: '/reader/list',
    method: 'get',
    params
  })
}

// 添加读者
export function addReader(data) {
  return request({
    url: '/reader/add',
    method: 'post',
    data
  })
}

// 修改读者
export function updateReader(data) {
  return request({
    url: '/reader/update',
    method: 'post',
    data
  })
}

// 删除读者
export function deleteReader(params) {
  return request({
    url: '/reader/delete?ids=' + params,
    method: 'get'
  })
}

// 重置密码
export function resetPwd(params) {
  return request({
    url: '/reader/resetPwd',
    method: 'post',
    params
  })
}
