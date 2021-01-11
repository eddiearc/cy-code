import request from '@/utils/request'

//查询所有课程分类信息
export function fetchList(query) {
    return request({
      url: '/category/findPage',
      method: 'post',
      params: query
    })
}

//添加课程分类信息 
export function createArticle(data) {
    return request({
      url: '/category/add',
      method: 'post',
      data: data
    })
}

//根据id获取信息 getInfoById
export function getInfoById(id) {
    return request({
      url: '/category/findById?id='+id,
      method: 'get'
    })
}

//更新 
export function updateArticle(data) {
    return request({
      url: '/category/update',
      method: 'post',
      data: data
    })
}

//删除
export function deleteInfo(id) {
    return request({
      url: '/category/delete?id='+id,
      method: 'get',
    })
  }