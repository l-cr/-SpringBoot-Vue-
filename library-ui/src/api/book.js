import request from '@/utils/request'

// 查询图书列表
export function listBook(params) {
  return request({
    url: '/book/list',
    method: 'get',
    params
  })
}

// 添加图书
export function addBook(data) {
  return request({
    url: '/book/add',
    method: 'post',
    data
  })
}

// 修改图书
export function updateBook(data) {
  return request({
    url: '/book/update',
    method: 'post',
    data
  })
}

// 删除图书
export function deleteBook(params) {
  return request({
    url: '/book/delete?isbns=' + params,
    method: 'get'
  })
}

//================ 读者操作 ==================

// 借阅图书
export function lendBook(params) {
  return request({
    url: '/book/lendBook',
    method: 'get',
    params
  })
}

// 借阅图书
export function returnBook(params) {
  return request({
    url: '/book/returnBook',
    method: 'get',
    params
  })
}
