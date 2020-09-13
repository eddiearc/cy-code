# 数据库文档 

### tb_student  表

| 字段名称 | 字段含义 | 字段类型| 字段长度 | 备注   |
| ---- | ---- | ------- | ---- | ---- |
| id  | 学生表ID，学号 | VARCHAR   |      |      |
| name  | 学生姓名 | VARCHAR   |      |      |
| password  | 密码 | VARCHAR   |      |      |
| sex  | 0:女 1:男 | INT   |      |      |
| id_number  | 居民身份证 | VARCHAR   |      |      |
| college  | 学院/系 | VARCHAR   |      |      |
| major  | 专业 | VARCHAR   |      |      |
| class  | 专业班级 | VARCHAR   |      |      |




### 接口列表

#### 查询所有数据  

##### url 

> /student/findAll.do

##### http请求方式 

> GET

##### 请求参数

无

##### 返回格式

```
[{
	"id": 学生表ID，学号,
	"name": 学生姓名,
	"password": 密码,
	"sex": 0:女 1:男,
	"idNumber": 居民身份证,
	"college": 学院/系,
	"major": 专业,
	"class": 专业班级,

},
.......
]
```



#### 分页查询数据  

##### url

> /student/findPage.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明    |
| ---- | ---- | ---- | ----- |
| page | true | int  | 页码    |
| size | true | int  | 每页记录数 |

例子：

```
GET /student/findPage.do?page=1&size=10
```

##### 返回格式

```
{rows:[{
	"id": 学生表ID，学号,
	"name": 学生姓名,
	"password": 密码,
	"sex": 0:女 1:男,
	"idNumber": 居民身份证,
	"college": 学院/系,
	"major": 专业,
	"class": 专业班级,

},
.......
],
total:100}
```



#### 条件查询数据  

##### url

> /student/findList.do

##### http请求方式

> POST

##### 请求参数

| 参数        | 必选   | 类型   | 说明           |
| --------- | ---- | ---- | ------------ |
| searchMap | true | Map  | 条件对象，格式如实体对象 |

例子：

```
POST /student/findList.do
{
	"id": 学生表ID，学号,
	"name": 学生姓名,
	"password": 密码,
	"sex": 0:女 1:男,
	"idNumber": 居民身份证,
	"college": 学院/系,
	"major": 专业,
	"class": 专业班级,

}
```

##### 返回格式

```
[{
	"id": 学生表ID，学号,
	"name": 学生姓名,
	"password": 密码,
	"sex": 0:女 1:男,
	"idNumber": 居民身份证,
	"college": 学院/系,
	"major": 专业,
	"class": 专业班级,

},
.......
]
```



#### 条件+分页查询数据  

##### url

> /student/findPage.do

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
POST /student/findPage.do?page=1&size=10
{
	"id": 学生表ID，学号,
	"name": 学生姓名,
	"password": 密码,
	"sex": 0:女 1:男,
	"idNumber": 居民身份证,
	"college": 学院/系,
	"major": 专业,
	"class": 专业班级,

}
```

##### 返回格式

```
{rows:[{
	"id": 学生表ID，学号,
	"name": 学生姓名,
	"password": 密码,
	"sex": 0:女 1:男,
	"idNumber": 居民身份证,
	"college": 学院/系,
	"major": 专业,
	"class": 专业班级,

},
.......
],
total:100}
```



#### 根据ID查询数据  

##### url

> /student/findById.do

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

> /student/add.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| student | true | student | 实体对象 |

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

> /student/update.do

##### http请求方式

> POST

##### 请求参数

| 参数       | 必选   | 类型       | 说明   |
| -------- | ---- | -------- | ---- |
| student | true | student | 实体对象 |

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

> /student/delete.do

##### http请求方式

> GET

##### 请求参数

| 参数   | 必选   | 类型   | 说明   |
| ---- | ---- | ---- | ---- |
| id   | true | int  | 主键   |

例子：

```
GET /student/delete.do?id=1
```

##### 返回格式：

```
{
  code:0,
  message:""
}
```

code为0表示成功，为1表示失败
