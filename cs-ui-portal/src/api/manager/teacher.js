import request from '@/utils/request'

//查询所有教师信息
export function fetchList(query) {
    return request({
      url: '/teacher/findPage',
      method: 'post',
      params: query
    })
}

//根据教师id获取教师授课信息
export function getDetailInfo(teacherId) {
    return request({
        url: '/course/getDetailInfo?teacherId='+teacherId,
        method: 'get',
    })
}

//添加教师信息 createArticle
export function createArticle(data) {
    return request({
        url: '/teacher/add',
        method: 'post',
        data: data
    })
}

//根据id查询教师信息
export function getInfoById(id) {
    return request({
        url: '/teacher/findById?id='+id,
        method: 'get'
    })
}

//修改信息
export function updateArticle(data) {
    return request({
        url: '/teacher/update',
        method: 'post',
        data: data
    })
}