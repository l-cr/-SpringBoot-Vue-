<template>
  <div class="app-container">
    <!-- 查询表单 -->
    <el-form :inline="true" :model="queryForm" ref="queryForm" size="small" style="margin-bottom: 20px">
      <el-form-item label="图书作者" prop="author">
        <el-input v-model="queryForm.author" placeholder="图书作者" clearable />
      </el-form-item>
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
      <el-table-column prop="bookName" label="图书名称" width="240px" />
      <el-table-column prop="author" label="作者" />
      <el-table-column prop="publisher" label="出版社" />
      <el-table-column prop="intro" label="简介" width="240px" show-overflow-tooltip />
      <el-table-column
        prop="status"
        label="状态"
        :filters="[
          { text: '未借阅', value: '0' },
          { text: '已借阅', value: '1' }
        ]"
        :filter-method="filterTag"
        filter-placement="bottom-end"
      >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status == 0" type="success">未借阅</el-tag>
          <el-tag v-else type="warning">已借阅</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="230px">
        <template slot-scope="scope">
          <el-button
            :disabled="scope.row.status == 1"
            size="mini"
            icon="el-icon-reading"
            type="primary"
            plain
            @click="handleLend(scope.row)"
          >
            借阅
          </el-button>
          <el-button
            :disabled="scope.row.status == 0"
            size="mini"
            icon="el-icon-refresh-right"
            type="danger"
            plain
            @click="handleReturn(scope.row)"
          >
            归还
          </el-button>
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
import { listBook, lendBook, returnBook } from '@/api/book'

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
      // 借阅参数
      params: {
        isbn: undefined,
        readerId: undefined
      }
    }
  },
  created() {
    this.getList()
    getInfo().then(({ data }) => {
      this.params.readerId = data.readerId
    })
  },
  methods: {
    //============== 图书API =============
    // 查询图书列表
    getList() {
      const queryParams = { ...this.queryForm, current: this.page.current, size: this.page.size }
      listBook(queryParams).then(({ data }) => {
        // console.log(res.data.records)
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

    //============== 表格 ==================
    // 借阅图书
    handleLend(row) {
      // console.log(row)
      this.params.isbn = row.isbn
      console.log(this.params)
      lendBook(this.params).then(() => {
        this.msgSuccess('借阅成功!')
        this.getList()
      })
    },
    // 归还图书
    handleReturn(row) {
      // console.log(row)
      this.params.isbn = row.isbn
      returnBook(this.params).then(() => {
        this.msgSuccess('归还成功!')
        this.getList()
      })
    },
    filterTag(value, row) {
      console.log('value = ' + value)
      console.log(row.status)
      return row.status === value
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

<style >
.app-container .el-table__column-filter-trigger i {
  font-weight: 700 !important;
  font-size: 14px !important;
}
</style>