<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>个人信息</span>
      </div>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="姓名">
          <el-input v-model="form.username" maxlength="20" />
        </el-form-item>
        <el-form-item label="性别">
          <el-radio v-model="form.gender" label="0">男</el-radio>
          <el-radio v-model="form.gender" label="1">女</el-radio>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input v-model="form.phone" maxlength="11" />
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" plain @click="handleSave">保存</el-button>
          <el-button type="danger" plain @click="handleEditPwd">修改密码</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { getInfo } from '@/api/login'
import { updateReader, resetPwd } from '@/api/reader'

export default {
  data() {
    return {
      form: {}
    }
  },
  created() {
    getInfo().then(({ data }) => {
      this.form = data
    })
  },
  methods: {
    handleSave() {
      updateReader(this.form).then(() => {
        this.msgSuccess('修改信息成功!')
      })
    },
    handleEditPwd() {
      this.$prompt('请输入密码', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      })
        .then(({ value }) => {
          resetPwd({ readerId: this.form.readerId, password: value }).then(() => {
            this.msgSuccess('修改密码成功!')
          })
        })
        .catch(() => {})
    }
  }
}
</script>

<style scoped>
.box-card {
  width: 60%;
  margin: auto;
  padding: 20px;
}
</style>