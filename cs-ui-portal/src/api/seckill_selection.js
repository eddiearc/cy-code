import request from '@/utils/request'

/**
 * 获取本学期选课全部信息
 */
export function getCurrentList() {
  return request({
    url: '/stu/selection/current',
    method: 'get'
  })
}

/**
 * 获取历史选课情况
 */
export function getHistoryList() {
  return request({
    url: '/stu/selection/history',
    method: 'get'
  })
}

/**
 * 取消选课接口
 * @param {*} id
 */
export function seckillRemove(id) {
  return request({
    url: '/stu/sk/rm?courseId=' + id,
    method: 'get'
  })
}

//统计课程分类课程数量用于图表显示
export function countCategory() {
  return request({
    url: '/category/countCategory',
    method: 'get'
  })
}