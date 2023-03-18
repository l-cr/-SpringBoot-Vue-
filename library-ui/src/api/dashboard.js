import request from '@/utils/request'

// 总计
export function count() {
  return request({
    url: '/dashboard/count',
    method: 'get'
  })
}
