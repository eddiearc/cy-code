import request from '@/utils/request'

/* 获取选课全部信息*/
export function fetchList(query) {
  return request({
    url: '/course/findPage',
    method: 'get',
    params: query
  })
}

/* 点击选课后减少库存*/
export function seckillAdd(id) {
  return request({
    url: '/seckill/add',
    method: 'get',
    params: {
      'courseId': id
    }
  })
}
