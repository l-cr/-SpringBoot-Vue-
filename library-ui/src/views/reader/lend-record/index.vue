<template>
  <div class="app-container">
    <!-- 查询表单 -->
    <el-form :inline="true" :model="queryForm" ref="queryForm" size="small" style="margin-bottom: 20px">
      <el-form-item label="图书名称" prop="bookName">
        <el-input v-model="queryForm.bookName" placeholder="图书名称" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="querySubmit">查询</el-button>
        <el-button icon="el-icon-refresh" @click="resetForm('queryForm')">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 表格数据 -->
    <el-table ref="table" :data="tableData">
      <el-table-column prop="isbn" label="图书编号" />
      <el-table-column prop="bookName" label="图书名称" />
      <el-table-column prop="readerId" label="读者编号" />
      <el-table-column prop="username" label="读者姓名" />
      <el-table-column prop="lendDate" label="借阅日期" />
      <el-table-column prop="returnDate" label="归还日期" />
      <el-table-column prop="status" label="状态">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status == 0" type="success">未归还</el-tag>
          <el-tag v-else type="warning">已归还</el-tag>
        </template>
      </el-table-column>
      <el-table-column v-if="false" label="操作" width="230px">
        <template slot-scope="scope">
          <el-button size="mini" icon="el-icon-edit" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="mini" icon="el-icon-delete" type="danger" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      background
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="page.current"
      :page-size="page.size"
      :page-sizes="[2, 5, 10, 20, 100]"
      layout="total, sizes, prev, pager, next, jumper"
      :total="page.total"
    >
    </el-pagination>
  </div>
</template>

<script>
import { getInfo } from '@/api/login'
import { listLendRecord } from '@/api/lendRecord'

export default {
  data() {
    return {
      // 查询表单数据
      queryForm: {},
      // 表格数据
      tableData: [],
      // 分页数据
      page: {
        current: 1, // 当前页
        size: 10, // 每页显示数
        total: undefined // 总计
      },
      readerId: undefined
    }
  },
  computed: {
    route() {
      return this.$route.path
    }
  },
  created() {
    getInfo().then(({ data }) => {
      this.readerId = data.readerId
      this.getList()
    })
  },
  watch: {
    route(val) {
      if (val == '/reader/lend-record') {
        this.getList()
      }
    }
  },
  methods: {
    //============== 借阅记录API =============
    // 查询借阅记录列表
    getList() {
      const queryParams = {
        ...this.queryForm,
        current: this.page.current,
        size: this.page.size,
        readerId: this.readerId
      }
      listLendRecord(queryParams).then(({ data }) => {
        this.tableData = data.list
        this.page.total = data.total
      })
    },

    //============== 查询表单 =================
    // 查询表单提交
    querySubmit() {
      console.log(this.queryForm)
      this.getList()
    },
    // 重置表单
    resetForm(formName) {
      this.$refs[formName].resetFields()
      this.getList()
    },

    //=============== 分页 ==================
    // 每页显示数改变
    handleSizeChange(val) {
      // console.log(`每页 ${val} 条`)
      this.page.size = val
      this.getList()
    },
    // 当前页改变
    handleCurrentChange(val) {
      // console.log(`当前页: ${val}`)
      this.page.current = val
      this.getList()
    }
  }
}
</script>

<style>
</style>