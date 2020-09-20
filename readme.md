# cy-code



## 结构示意图

- 前端模块

- 数据库模块及其接口文档

- 后端模块`cy-code`

    - `course-selection-api`

        为前端提供`api`，`dubbo`服务消费者

    - `course-selection-server`

        - `course-selection-interface`

            `pojo`实体类与服务接口定义

        - `course-selection-service`

            主要用于编写服务实现类及`Dao`接口，`dubbo`服务生产者





## 选课系统

### 技术选型

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



### 重难点

#### 多线程处理

使用线程池，进行压力测试



#### 集群部署与环境

业务服务利用`Dubbo` + `zookeeper`部署，`MySQL`、`Redis`、`Nginx`部署

结合`Docker`自动化部署？



#### 用户权限管理

`SpringSecurity`or`Shiro`？

如何设计？