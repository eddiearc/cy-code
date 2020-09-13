# 数据库文档 

### tb_selection  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| id  | 选课信息ID | VARCHAR   |      |      |
| student_id  | 学生ID | VARCHAR   |      |      |
| course_id  | 课程ID | VARCHAR   |      |      |
| term  | 哪一个学期 | INT   |      |      |
| stage  | 选课阶段 | INT   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /selection/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"id": 选课信息ID,
	"studentId": 学生ID,
	"courseId": 课程ID,
	"term": 哪一个学期,
	"stage": 选课阶段,

},
.......
]
```



#### 分页查询数据  

##### url

> /selection/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /selection/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"id": 选课信息ID,
	"studentId": 学生ID,
	"courseId": 课程ID,
	"term": 哪一个学期,
	"stage": 选课阶段,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /selection/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /selection/findList.do
{
	"id": 选课信息ID,
	"studentId": 学生ID,
	"courseId": 课程ID,
	"term": 哪一个学期,
	"stage": 选课阶段,

}
```

##### 返回格式

```
[{
	"id": 选课信息ID,
	"studentId": 学生ID,
	"courseId": 课程ID,
	"term": 哪一个学期,
	"stage": 选课阶段,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /selection/findPage.do

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
POST /selection/findPage.do?page=1&size=10
{
	"id": 选课信息ID,
	"studentId": 学生ID,
	"courseId": 课程ID,
	"term": 哪一个学期,
	"stage": 选课阶段,

}
```

##### 返回格式

```
{rows:[{
	"id": 选课信息ID,
	"studentId": 学生ID,
	"courseId": 课程ID,
	"term": 哪一个学期,
	"stage": 选课阶段,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /selection/findById.do

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

> /selection/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| selection | true | selection | 实体对象 |

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

> /selection/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| selection | true | selection | 实体对象 |

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

> /selection/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /selection/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
