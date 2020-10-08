import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/course/list',
    method: 'get',
    params: query
  })
}
