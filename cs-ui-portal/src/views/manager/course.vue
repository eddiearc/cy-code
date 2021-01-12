<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.name" placeholder="请输入课程名称" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        搜索
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        新增
      </el-button>
      <el-button v-waves :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">
        导出Excel
      </el-button>
    </div>

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
      @sort-change="sortChange"
    >
      <el-table-column label="课程编号" prop="id" sortable="custom" align="center" width="80" :class-name="getSortClass('id')">
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="课程名称" align="center" width="200px">
        <template slot-scope="{row}">
          <span>{{ row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="学分" align="center" width="100">
        <template slot-scope="{row}">
          <span>{{ row.credit }}</span>
        </template>
      </el-table-column>
      <el-table-column label="上课周数时间地点" align="center" width="350px">
        <template slot-scope="{row}">
          <span>{{ ' [ ' + row.durationStart + ' ~ ' + row.durationEnd + ' ]  ' + row.time + '  ' + row.place }}</span>
        </template>
      </el-table-column>
      <el-table-column label="任课教师" align="center" width="100">
        <template slot-scope="{row}">
          <span>{{ row.teacherName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="课程类别" align="center" width="100">
        <template>
          <span>计算机选修</span>
        </template>
      </el-table-column>
      <el-table-column label="课程总人数" align="center" width="100">
        <template slot-scope="{row}">
          <span>{{ row.total }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="{row}">
          <el-button v-waves :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-edit" @click="handleUpdate(row.id)">
            编辑
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
    
    <!-- 新增课程信息 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="课程序号" prop="id">
          <el-input v-model="temp.id" placeholder="Please enter" :disabled="dialogStatus==='create'? false : true"/>
        </el-form-item>
        <el-form-item label="课程名称" prop="name">
          <el-input v-model="temp.name" placeholder="Please enter" />
        </el-form-item>
        <el-form-item label="学分" prop="credit">
          <el-input v-model="temp.credit" placeholder="Please enter" />
        </el-form-item>
        <el-form-item label="上课时间" prop="time">
          <el-input v-model="temp.time" placeholder="'周三3-4'" />
        </el-form-item>
        <el-form-item label="开始周数" prop="durationStart">
          <el-input v-model="temp.durationStart" placeholder="Please enter" />
        </el-form-item>
        <el-form-item label="结束周数" prop="durationEnd">
          <el-input v-model="temp.durationEnd" placeholder="Please enter" />
        </el-form-item>
        <el-form-item label="上课地点" prop="place">
          <el-input v-model="temp.place" placeholder="Please enter" />
        </el-form-item>
        <el-form-item label="授课形式" prop="online">
          <el-input v-model="temp.online" placeholder="0为线下 1为网课" />
        </el-form-item>
        <el-form-item label="授课教师" prop="teacherId">
          <el-select v-model="temp.teacherId" placeholder="请选择">
          <el-option
            v-for="item in teacherIds"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
        </el-form-item>
        <el-form-item label="课程类别" prop="categoryId">
          <el-select v-model="temp.categoryId" placeholder="请选择">
          <el-option
            v-for="item in categoryList"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
        </el-form-item>
        <el-form-item label="总人数" prop="total">
          <el-input v-model="temp.total" placeholder="Please enter" />
        </el-form-item>
        <div class="dialog-footer">
          <el-button @click="dialogFormVisible = false">
            取消
          </el-button>
          <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
            完成
          </el-button>
        </div>
      </el-form>
    </el-dialog>

    <el-dialog :visible.sync="dialogPvVisible" title="Reading statistics">
      <el-table :data="pvData" border fit highlight-current-row style="width: 100%">
        <el-table-column prop="key" label="Channel" />
        <el-table-column prop="pv" label="Pv" />
      </el-table>
      <span class="dialog-footer">
        <el-button type="primary" @click="dialogPvVisible = false">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { fetchList, fetchPv, createArticle, updateArticle,teacherList,getCourseInfo,categoryInfo } from '@/api/manager/course.js'
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination

const calendarTypeOptions = [
  { key: 'CN', display_name: 'China' },
  { key: 'US', display_name: 'USA' },
  { key: 'JP', display_name: 'Japan' },
  { key: 'EU', display_name: 'Eurozone' }
]

// arr to obj, such as { CN : "China", US : "USA" }
const calendarTypeKeyValue = calendarTypeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.display_name
  return acc
}, {})

export default {
  name: 'ComplexTable',
  components: { Pagination },
  directives: { waves },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'info',
        deleted: 'danger'
      }
      return statusMap[status]
    },
    typeFilter(type) {
      return calendarTypeKeyValue[type]
    }
  },
  data() {
    return {
      categoryList: null,
      teacherIds: null,
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        size: 20,
        importance: undefined,
        title: undefined,
        type: undefined
      },
      importanceOptions: [1, 2, 3],
      calendarTypeOptions,
      sortOptions: [{ label: 'ID Ascending', key: '+id' }, { label: 'ID Descending', key: '-id' }],
      statusOptions: ['published', 'draft', 'deleted'],
      showReviewer: false,
      temp: {
        id: null,
        name: '',
        time: new Date(),
        place: '',
        teacherName: '',
        total: ''
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: 'Edit',
        create: 'Create'
      },
      dialogPvVisible: false,
      pvData: [],
      rules: {
        type: [{ required: true, message: 'type is required', trigger: 'change' }],
        timestamp: [{ type: 'date', required: true, message: 'timestamp is required', trigger: 'change' }],
        title: [{ required: true, message: 'title is required', trigger: 'blur' }]
      },
      downloadLoading: false
    }
  },
  created() {
    this.getList()
    this.getTeacherList()
    this.getCategoryList()
  },
  methods: {
    //获取课程类别列表
    getCategoryList(){
      categoryInfo().then(response => {
        this.categoryList = response
      })
    },
    //获取老师id列表
    getTeacherList(){
      teacherList().then(response => {
        this.teacherIds = response
      })
    },
    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.list = response.rows
        this.total = response.total

        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleModifyStatus(row, status) {
      this.$message({
        message: '操作Success',
        type: 'success'
      })
      row.status = status
    },
    sortChange(data) {
      const { prop, order } = data
      if (prop === 'id') {
        this.sortByID(order)
      }
    },
    sortByID(order) {
      if (order === 'ascending') {
        this.listQuery.sort = '+id'
      } else {
        this.listQuery.sort = '-id'
      }
      this.handleFilter()
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        importance: 1,
        remark: '',
        timestamp: new Date(),
        title: '',
        status: 'published',
        type: ''
      }
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.temp.stock = this.temp.total
          for(let i = 0;i<this.teacherIds.length;i++){
            if(this.teacherIds[i].id == this.temp.teacherId){
              this.temp.teacherName = this.teacherIds[i].name
            }
          }
          this.temp.term = 0
          createArticle(this.temp).then(() => {
            this.list.unshift(this.temp)
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: '添加课程成功',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleUpdate(row) {
      getCourseInfo(row).then(response => {
        this.temp = response
      })
      // this.temp = Object.assign({}, row) // copy obj
      // this.temp.timestamp = new Date(this.temp.timestamp)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
      console.log(1)
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          //const tempData = Object.assign({}, this.temp)
          //tempData.timestamp = +new Date(tempData.timestamp) // change Thu Nov 30 2017 16:41:05 GMT+0800 (CST) to 1512031311464
          updateArticle(this.temp).then(() => {
            const index = this.list.findIndex(v => v.id === this.temp.id)
            this.list.splice(index, 1, this.temp)
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: '成功修改课程信息',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleDelete(row, index) {
      this.$notify({
        title: 'Success',
        message: 'Delete Successfully',
        type: 'success',
        duration: 2000
      })
      this.list.splice(index, 1)
    },
    handleFetchPv(pv) {
      fetchPv(pv).then(response => {
        this.pvData = response.data.pvData
        this.dialogPvVisible = true
      })
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['课程序号', '课程名称', '学分', '上课周数时间地点', '任课教师', '课程类别', '课程总人数']
        const filterVal = ['id', 'name', 'credit', 'time_place', 'teacherName', 'category', 'total']
        const data = this.formatJson(filterVal)
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: '课程信息'
        })
        this.downloadLoading = false
      })
    },
    formatJson(filterVal) {
      return this.list.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else if (j === 'time_place') {
          return ' [ ' + v['durationStart'] + ' ~ ' + v['durationEnd'] + ' ]  ' + v['time'] + '  ' + v['place']
        } else if (j === 'category') {
          return '计算机选修'
        } else {
          return v[j]
        }
      }))
    },
    getSortClass: function(key) {
      const sort = this.listQuery.sort
      return sort === `+${key}` ? 'ascending' : 'descending'
    }
  }
}
</script>
