<template>
  <div class="app-container">
    <!-- 查询表单 -->
    <el-form :inline="true" :model="queryForm" ref="queryForm" size="small" style="margin-bottom: 20px">
      <el-form-item label="图书名称" prop="bookName">
        <el-input v-model="queryForm.bookName" placeholder="图书名称" clearable />
      </el-form-item>
      <el-form-item label="读者姓名" prop="username">
        <el-input v-model="queryForm.username" placeholder="读者姓名" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="querySubmit">查询</el-button>
        <el-button icon="el-icon-refresh" @click="resetForm('queryForm')">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 功能按钮组 -->
    <el-row v-if="false" :gutter="20" style="margin-bottom: 7px">
      <el-col :span="1.5">
        <el-button type="primary" size="small" icon="el-icon-plus" plain @click="handleAdd">添加</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          size="small"
          icon="el-icon-edit"
          plain
          @click="handleEdit(multipleSelection[0])"
          :disabled="editDisabled"
          >编辑</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          size="small"
          icon="el-icon-delete"
          plain
          @click="handleDelete"
          :disabled="removeDisabled"
          >删除</el-button
        >
      </el-col>
    </el-row>

    <!-- 表格数据 -->
    <el-table ref="table" :data="tableData" @selection-change="handleSelectionChange">
      <el-table-column type="selection" />
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
      <el-table-column label="操作" width="230px">
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

    <!-- 编辑或添加对话框 -->
    <el-dialog :title="dialogTitle" :modal="false" :visible.sync="dialogFormVisible" width="35%" append-to-body>
      <el-form ref="dialogForm" :model="dialogForm" label-width="120px">
        <el-form-item label="借阅日期" prop="lendDate">
          <el-date-picker
            v-model="dialogForm.lendDate"
            type="datetime"
            placeholder="借阅日期"
            default-time="12:00:00"
            :picker-options="pickerOptions"
            value-format="yyyy-MM-dd HH:mm:ss"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="归还日期" prop="returnDate">
          <el-date-picker
            v-model="dialogForm.returnDate"
            type="datetime"
            placeholder="归还日期"
            default-time="12:00:00"
            :picker-options="pickerOptions"
            value-format="yyyy-MM-dd HH:mm:ss"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否归还" prop="status">
          <el-radio v-model="dialogForm.status" label="0">未归还</el-radio>
          <el-radio v-model="dialogForm.status" label="1">已归还</el-radio>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancelDialog">取 消</el-button>
        <el-button type="primary" @click="submitDialog">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listLendRecord, addLendRecord, updateLendRecord, deleteLendRecord } from '@/api/lendRecord'

export default {
  data() {
    return {
      // 查询表单数据
      queryForm: {},
      // 按钮禁用
      editDisabled: true,
      removeDisabled: true,
      // 表格数据
      tableData: [],
      // 多选数据
      multipleSelection: [],
      // 分页数据
      page: {
        current: 1, // 当前页
        size: 10, // 每页显示数
        total: undefined // 总计
      },
      // 对话框类型[add|edit]
      dialogType: '',
      // 对话框是否可见
      dialogFormVisible: false,
      // 对话框标题
      dialogTitle: undefined,
      // 对话框数据
      dialogForm: {},
      // 日期选择器
      pickerOptions: {
        shortcuts: [
          {
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date())
            }
          },
          {
            text: '昨天',
            onClick(picker) {
              const date = new Date()
              date.setTime(date.getTime() - 3600 * 1000 * 24)
              picker.$emit('pick', date)
            }
          },
          {
            text: '一周前',
            onClick(picker) {
              const date = new Date()
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7)
              picker.$emit('pick', date)
            }
          }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    //============== 借阅记录API =============
    // 查询借阅记录列表
    getList() {
      const queryParams = { ...this.queryForm, current: this.page.current, size: this.page.size }
      listLendRecord(queryParams).then(({ data }) => {
        this.tableData = data.list
        this.page.total = data.total
      })
    },
    // 添加借阅记录
    addData() {
      addLendRecord(this.dialogForm).then(() => {
        this.msgSuccess('添加成功!')
        this.getList()
      })
    },
    // 修改借阅记录
    updateData() {
      const data = {
        isbn: this.dialogForm.isbn,
        readerId: this.dialogForm.readerId,
        lendDate: this.dialogForm.lendDate,
        returnDate: this.dialogForm.returnDate,
        status: this.dialogForm.status
      }
      updateLendRecord(data).then(() => {
        this.msgSuccess('修改成功!')
        this.getList()
      })
    },
    // 删除借阅记录
    deleteData(params) {
      deleteLendRecord(params).then(() => {
        this.msgSuccess('删除成功!')
        this.getList()
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
    // 多选改变
    handleSelectionChange(val) {
      this.multipleSelection = val
      this.editDisabled = !(val.length === 1)
      this.removeDisabled = !(val.length > 0)
    },
    // 删除行数据
    handleDelete(row) {
      let data = { readerId: row.readerId, isbn: row.isbn }
      this.$confirm('此操作将永久删除该行数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.deleteData(data)
        })
        .catch(() => {})
    },
    // 添加数据
    handleAdd() {
      // 清除对话框表单数据
      this.dialogForm = {}
      this.dialogType = 'add'
      this.dialogTitle = '添加借阅记录'
      this.showDialog()
    },
    // 编辑数据
    handleEdit(val) {
      console.log(val)
      this.dialogForm = val
      this.dialogType = 'edit'
      this.dialogTitle = '修改借阅记录'
      this.showDialog()
    },

    //============== 添加或编辑对话框 ===================
    // 显示对话框
    showDialog() {
      this.dialogFormVisible = true
    },
    // 对话框取消
    cancelDialog() {
      this.dialogFormVisible = false
    },
    // 对话框提交
    submitDialog() {
      console.log(this.dialogForm)
      if (this.dialogType == 'add') {
        // console.log('执行添加表单')
        this.addData()
      } else {
        // console.log('执行修改表单')
        this.updateData()
      }
      this.dialogFormVisible = false
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