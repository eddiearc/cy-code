# cy-code 


## CODE结构

- 前端模块`cs-ui-portal`

- 数据库模块及其接口文档`sql_doc`

- 后端模块`cy-code`

    - `course-selection-api`: 为前端提供`api`，`dubbo`服务消费者

    - `course-selection-server`

        - `course-selection-interface`: `pojo`实体类与服务接口定义

        - `course-selection-service`: 主要用于编写服务实现类及`Dao`接口，`dubbo`服务生产者




## 技术选型

`SpringBoot` + `Dubbo`  + `Redis` + `MySQL` + `Nginx`  + `RabbitMQ` + `Thymeleaf` + `Vue.js`

- SpringBoot：简化配置，易于开发
- Dubbo：项目以微服务的架构进行开发，易于后期新的模块功能的拓展
- Redis：
    - 缓存特性，用于存储课程信息，减轻高并发下的数据库的压力
    - 队列特性，用于选课系统的削峰
- MySQL：数据库
- Nginx：负载均衡
- RabbitMQ：在选课高峰期，使用延时队列去修改数据库中的数据，遵循分布式Base理论中的最终一致性，减小数据库压力（可用Redis替换）
- Thymeleaf：生成静态页面，减少渲染速度
- Vue.js

## 设计文档
[架构设计](https://gitee.com/eddievim/cy-code/wikis/%E6%9E%B6%E6%9E%84%E8%AE%BE%E8%AE%A1)
[数据库设计](https://gitee.com/eddievim/cy-code/wikis/%E6%95%B0%E6%8D%AE%E5%BA%93%E8%AE%BE%E8%AE%A1)
[系统权限设计](https://gitee.com/eddievim/cy-code/wikis/%E7%B3%BB%E7%BB%9F%E6%9D%83%E9%99%90%E8%AE%BE%E8%AE%A1)
[高并发选课流程设计](https://gitee.com/eddievim/cy-code/wikis/%E9%AB%98%E5%B9%B6%E5%8F%91%E9%80%89%E8%AF%BE%E6%B5%81%E7%A8%8B%E8%AE%BE%E8%AE%A1)

## 系统部署
[环境部署文档](https://gitee.com/eddievim/cy-code/wikis/Course-Selection%E7%8E%AF%E5%A2%83%E9%83%A8%E7%BD%B2)
