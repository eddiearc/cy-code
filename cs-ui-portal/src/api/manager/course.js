import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/course/findPage',
    method: 'post',
    params: query
  })
}

//获取课程分类列表 categoryInfo
export function categoryInfo() {
  return request({
    url: '/category/findAll',
    method: 'get'
  })
}
//根据课程id查询课程信息
export function getCourseInfo(id) {
  return request({
    url: '/course/findById?id='+id,
    method: 'get'
  })
}
//获取教师id列表
export function teacherList() {
  return request({
    url: '/teacher/findAll',
    method: 'get',
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
// 添加课程信息
export function createArticle(data) {
  return request({
    url: '/course/add',
    method: 'post',
    data
  })
}
//修改课程信息
export function updateArticle(data) {
  return request({
    url: '/course/update',
    method: 'post',
    data: data
  })
}