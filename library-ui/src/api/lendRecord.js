import request from '@/utils/request'

// 查询借阅记录列表
export function listLendRecord(params) {
  return request({
    url: '/lendRecord/list',
    method: 'get',
    params
  })
}

// 添加借阅记录
export function addLendRecord(data) {
  return request({
    url: '/lendRecord/add',
    method: 'post',
    data
  })
}

// 修改借阅记录
export function updateLendRecord(data) {
  return request({
    url: '/lendRecord/update',
    method: 'post',
    data
  })
}

// 删除借阅记录
export function deleteLendRecord(params) {
  return request({
    url: '/lendRecord/delete',
    method: 'get',
    params
  })
}
