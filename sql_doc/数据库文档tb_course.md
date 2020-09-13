# 数据库文档 

### tb_course  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| id  | 选修的班级号 | VARCHAR   |      |      |
| name  | 选修课名称 | VARCHAR   |      |      |
| term  | 学期标识 | INT   |      |      |
| credit  | 学分 | INT   |      |      |
| time  | 上课具体时间 | VARCHAR   |      |      |
| duration_start  | 开始的周数 | INT   |      |      |
| duration_end  | 结束的周数 | INT   |      |      |
| place  | 上课地点 | VARCHAR   |      |      |
| online  | 是否是网课，0:是， 1:不是 | INT   |      |      |
| teacher_id  | 上课老师的工号 | VARCHAR   |      |      |
| teacher_name  | 上课老师的姓名，冗余字段 | VARCHAR   |      |      |
| category_id  | 课程类别Id | INT   |      |      |
| count  | 课程数 | INT   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /course/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"id": 选修的班级号,
	"name": 选修课名称,
	"term": 学期标识,
	"credit": 学分,
	"time": 上课具体时间,
	"durationStart": 开始的周数,
	"durationEnd": 结束的周数,
	"place": 上课地点,
	"online": 是否是网课，0:是， 1:不是,
	"teacherId": 上课老师的工号,
	"teacherName": 上课老师的姓名，冗余字段,
	"categoryId": 课程类别Id,
	"count": 课程数,

},
.......
]
```



#### 分页查询数据  

##### url

> /course/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /course/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"id": 选修的班级号,
	"name": 选修课名称,
	"term": 学期标识,
	"credit": 学分,
	"time": 上课具体时间,
	"durationStart": 开始的周数,
	"durationEnd": 结束的周数,
	"place": 上课地点,
	"online": 是否是网课，0:是， 1:不是,
	"teacherId": 上课老师的工号,
	"teacherName": 上课老师的姓名，冗余字段,
	"categoryId": 课程类别Id,
	"count": 课程数,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /course/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /course/findList.do
{
	"id": 选修的班级号,
	"name": 选修课名称,
	"term": 学期标识,
	"credit": 学分,
	"time": 上课具体时间,
	"durationStart": 开始的周数,
	"durationEnd": 结束的周数,
	"place": 上课地点,
	"online": 是否是网课，0:是， 1:不是,
	"teacherId": 上课老师的工号,
	"teacherName": 上课老师的姓名，冗余字段,
	"categoryId": 课程类别Id,
	"count": 课程数,

}
```

##### 返回格式

```
[{
	"id": 选修的班级号,
	"name": 选修课名称,
	"term": 学期标识,
	"credit": 学分,
	"time": 上课具体时间,
	"durationStart": 开始的周数,
	"durationEnd": 结束的周数,
	"place": 上课地点,
	"online": 是否是网课，0:是， 1:不是,
	"teacherId": 上课老师的工号,
	"teacherName": 上课老师的姓名，冗余字段,
	"categoryId": 课程类别Id,
	"count": 课程数,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /course/findPage.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |
| page      | true | int  | 页码（GET）      |
| size      | true | int  | 每页记录数（GET）   |

例子：

```
POST /course/findPage.do?page=1&size=10
{
	"id": 选修的班级号,
	"name": 选修课名称,
	"term": 学期标识,
	"credit": 学分,
	"time": 上课具体时间,
	"durationStart": 开始的周数,
	"durationEnd": 结束的周数,
	"place": 上课地点,
	"online": 是否是网课，0:是， 1:不是,
	"teacherId": 上课老师的工号,
	"teacherName": 上课老师的姓名，冗余字段,
	"categoryId": 课程类别Id,
	"count": 课程数,

}
```

##### 返回格式

```
{rows:[{
	"id": 选修的班级号,
	"name": 选修课名称,
	"term": 学期标识,
	"credit": 学分,
	"time": 上课具体时间,
	"durationStart": 开始的周数,
	"durationEnd": 结束的周数,
	"place": 上课地点,
	"online": 是否是网课，0:是， 1:不是,
	"teacherId": 上课老师的工号,
	"teacherName": 上课老师的姓名，冗余字段,
	"categoryId": 课程类别Id,
	"count": 课程数,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /course/findById.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键  |

例子：

```

```



#### 增加数据  

##### url

> /course/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| course | true | course | 实体对象 |

##### 返回格式

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败



#### 修改数据  

##### url

> /course/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| course | true | course | 实体对象 |

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败



#### 删除数据  

##### url

> /course/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /course/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
