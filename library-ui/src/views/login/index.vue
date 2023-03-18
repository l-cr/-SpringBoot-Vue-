<template>
  <div v-loading="logining" class="login-container">
    <el-form :model="form" :rules="rules" status-icon ref="form" class="login-page">
      <h3 class="title">系统登录</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="form.username" auto-complete="off" placeholder="用户名" clearable></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          type="password"
          v-model="form.password"
          auto-complete="off"
          placeholder="密码"
          show-password
          clearable
          @keypress.enter.native="handleSubmit"
        ></el-input>
      </el-form-item>
      <!-- <el-checkbox v-model="rememberme" class="rememberme">记住密码</el-checkbox> -->
      <el-form-item>
        <el-radio v-model="form.role" label="admin">管理员</el-radio>
        <el-radio v-model="form.role" label="reader">读者</el-radio>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="width: 100%" @click="handleSubmit">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { login } from '@/api/login'
import { setRole, setToken } from '@/utils/auth'

export default {
  data() {
    return {
      // 按钮加载
      logining: false,
      // 表单属性
      form: {
        username: undefined,
        password: undefined,
        role: 'admin'
      },
      // 表单校验规则
      rules: {
        username: [{ required: true, message: '请输入账号', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
      }
      // rememberme: false,
    }
  },
  methods: {
    // 表单提交
    handleSubmit() {
      this.$refs.form.validate(valid => {
        if (valid) {
          this.logining = true
          login(this.form).then(res => {
            if (res.code === 200) {
              setToken(res.data) // 保存token到session中
              setRole(this.form.role) // 设置用户角色
              this.$router.push({ path: '/' })
            } else {
              this.$alert(res.msg, '信息', {
                confirmButtonText: '好的'
              })
            }
          })
        }
        this.logining = false
      })
    }
  }
}
</script>

<style scoped>
.login-container {
  position: fixed;
  width: 100%;
  height: 100%;
  background: url('../../assets/img/background.svg');
  background-size: contain;
}
.login-page {
  border-radius: 5px;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
label.el-checkbox.rememberme {
  margin: 0px 0px 15px;
  text-align: left;
}
</style>