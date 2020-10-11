import request from '@/utils/request'

/* 获取本学期选课全部信息*/
export function getParsentList() {
  return request({
    url: '/seckill/query/curr',
    method: 'get'
  })
}

/* 获取历史选课情况*/
export function getPastList() {
  return request({
    url: '/seckill/query/history',
    method: 'get'
  })
}
