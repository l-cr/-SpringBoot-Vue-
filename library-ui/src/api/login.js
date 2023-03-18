import { getToken, clearToken } from '@/utils/auth'
import request from '@/utils/request'

// 登录
export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    data
  })
}

// 获取用户信息
export function getInfo() {
  return request({
    url: '/getInfo?token=' + getToken(),
    method: 'get'
  })
}

// 退出登录
export function logout(token) {
  return clearToken()
}
