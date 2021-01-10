import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/user/findPage',
    method: 'get',
    params: query
  })
}

export function fetchArticle(id) {
  return request({
    url: '/vue-element-admin/article/detail',
    method: 'get',
    params: { id }
  })
}

export function fetchPv(pv) {
  return request({
    url: '/vue-element-admin/article/pv',
    method: 'get',
    params: { pv }
  })
}
//添加user
export function createArticle(data) {
  return request({
    url: '/user/add',
    method: 'post',
    data: data
  })
}

export function updateArticle(data) {
  return request({
    url: '/user/update',
    method: 'post',
    data: data
  })
}
//根据id查询user信息
export function getUserInfo(id) {
  return request({
    url: '/user/findById?id='+id,
    method: 'get',
  })
}

//删除user
export function deleteUserInfo(id) {
  return request({
    url: '/user/delete?id='+id,
    method: 'get',
  })
}