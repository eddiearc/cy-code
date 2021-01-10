import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/student/findPage',
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
//新增学生信息
export function createArticle(data) {
  return request({
    url: '/student/add',
    method: 'post',
    data
  })
}
//修改学生信息
export function updateArticle(data) {
  return request({
    url: '/student/update',
    method: 'post',
    data: data
  })
}
//根据学号获取学生信息
export function getStudentInfo(id) {
  return request({
    url: '/student/findById?id='+id,
    method: 'get',
  })
}
