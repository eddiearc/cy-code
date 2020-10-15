import request from '@/utils/request'

/* 获取本学期选课全部信息*/
export function getCurrList() {
  return request({
    url: '/seckill/query/curr',
    method: 'get'
  })
}

/* 获取历史选课情况*/
export function getHistoryList() {
  return request({
    url: '/seckill/query/history',
    method: 'get'
  })
}
