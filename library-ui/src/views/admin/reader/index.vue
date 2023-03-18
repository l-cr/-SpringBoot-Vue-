<template>
  <div class="app-container">
    <!-- 查询表单 -->
    <el-form :inline="true" :model="queryForm" ref="queryForm" size="small" style="margin-bottom: 20px">
      <el-form-item label="读者编号" prop="readerId">
        <el-input v-model="queryForm.readerId" placeholder="读者编号" clearable />
      </el-form-item>
      <el-form-item label="姓名" prop="username">
        <el-input v-model="queryForm.username" placeholder="姓名" clearable />
      </el-form-item>
      <el-form-item label="电话号码" prop="phone">
        <el-input v-model="queryForm.phone" placeholder="电话号码" clearable />
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input v-model="queryForm.address" placeholder="地址" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="querySubmit">查询</el-button>
        <el-button icon="el-icon-refresh" @click="resetForm('queryForm')">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 功能按钮组 -->
    <el-row :gutter="20" style="margin-bottom: 7px">
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
      <el-table-column prop="readerId" label="读者编号" width="180px" />
      <el-table-column prop="username" label="姓名" width="180px" />
      <el-table-column prop="gender" label="性别" width="160px">
        <template slot-scope="scope">
          {{ scope.row.gender == 1 ? '女' : '男' }}
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="电话号码" />
      <el-table-column prop="address" label="地址" show-overflow-tooltip />
      <el-table-column label="操作" width="280px">
        <template slot-scope="scope">
          <el-button size="mini" icon="el-icon-edit" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="mini" icon="el-icon-key" type="primary" @click="handleResetPwd(scope.row)">重置</el-button>
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
    <el-dialog :title="dialogTitle" :modal="false" :visible.sync="dialogFormVisible" width="42%" append-to-body>
      <el-form ref="dialogForm" :model="dialogForm" label-width="80px" inline>
        <el-form-item label="姓名" prop="username">
          <el-input v-model="dialogForm.username" autocomplete="off" />
        </el-form-item>
        <el-form-item v-show="dialogType == 'add'" label="密码" prop="password">
          <el-input v-model="dialogForm.password" autocomplete="off" />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="dialogForm.gender" style="width: 206.4px" placeholder="性别">
            <el-option label="男" value="0" />
            <el-option label="女" value="1" />
            <el-option label="未知" value="2" />
          </el-select>
        </el-form-item>
        <el-form-item label="电话号码" prop="phone">
          <el-input v-model="dialogForm.phone" maxlength="11" autocomplete="off" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="dialogForm.address" autocomplete="off" />
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
import { listReader, addReader, updateReader, deleteReader, resetPwd } from '@/api/reader'

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
      dialogForm: {}
    }
  },
  created() {
    this.getList()
  },
  methods: {
    //============== 读者API =============
    // 查询读者列表
    getList() {
      const queryParams = { ...this.queryForm, current: this.page.current, size: this.page.size }
      listReader(queryParams).then(({ data }) => {
        this.tableData = data.list
        this.page.total = data.total
      })
    },
    // 添加读者
    addData() {
      addReader(this.dialogForm).then(() => {
        this.msgSuccess('添加成功!')
        this.getList()
      })
    },
    // 修改读者
    updateData() {
      updateReader(this.dialogForm).then(() => {
        this.msgSuccess('修改成功!')
        this.getList()
      })
    },
    // 删除读者
    deleteData(params) {
      deleteReader(params).then(() => {
        this.msgSuccess('删除成功!')
        this.getList()
      })
    },

    //============== 查询表单 =================
    // 查询表单提交
    querySubmit() {
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
      let data = row.target ? this.multipleSelection.map(item => item.readerId) : row.readerId
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
      this.dialogTitle = '添加读者'
      this.showDialog()
    },
    // 编辑数据
    handleEdit(val) {
      this.dialogForm = val
      this.dialogType = 'edit'
      this.dialogTitle = '修改读者'
      this.showDialog()
    },
    // 重置密码
    handleResetPwd(row) {
      this.$prompt('请输入密码', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      })
        .then(({ value }) => {
          resetPwd({ readerId: row.readerId, password: value }).then(() => {
            this.msgSuccess('修改密码成功!')
          })
        })
        .catch(() => {
          // this.msgInfo('取消输入!')
        })
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
      if (this.dialogType == 'add') {
        this.addData()
      } else {
        this.updateData()
      }
      this.dialogFormVisible = false
    },

    //=============== 分页 ==================
    // 每页显示数改变
    handleSizeChange(val) {
      this.page.size = val
      this.getList()
    },
    // 当前页改变
    handleCurrentChange(val) {
      this.page.current = val
      this.getList()
    }
  }
}
</script>

<style>
</style>