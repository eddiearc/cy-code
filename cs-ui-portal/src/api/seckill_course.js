import request from '@/utils/request'

/**
 * 获取选课全部信息
 * @param {*} query
 */
export function fetchList(query) {
  return request({
    url: '/stu/course/fetchListThisTerm',
    method: 'get',
    params: query
  })
}

/**
 * 获取选课的列表
 */
export function fetchStock(query) {
  return request({
    url: '/stu/course/stock',
    method: 'get',
    params: query
  })
}

/**
 * 点击选课后减少库存
 * @param {*} id
 */
export function seckillAdd(id) {
  return request({
    url: '/seckill/add',
    method: 'get',
    params: {
      'courseId': id
    }
  })
}
