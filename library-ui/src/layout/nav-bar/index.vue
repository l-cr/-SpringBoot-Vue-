<template>
  <div class="navbar-container">
    <!-- 面包屑导航 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <transition-group name="breadcrumb" mode="out-in">
        <el-breadcrumb-item v-for="route in routes" :key="route.path" :to="{ path: route.path }">
          {{ route.meta.title }}
        </el-breadcrumb-item>
      </transition-group>
    </el-breadcrumb>

    <!-- 右侧管理工具 -->
    <div class="setting">
      <div style="display: inline-block; padding-right: 20px; font-size: 14px">欢迎用户：{{ username }}</div>
      <el-dropdown trigger="click">
        <i class="el-icon-s-tools"></i>
        <el-dropdown-menu slot="dropdown">
          <router-link to="/">
            <el-dropdown-item> 首页 </el-dropdown-item>
          </router-link>
          <el-dropdown-item divided @click.native="logout"> 退出登录 </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import { getInfo, logout } from '@/api/login'

export default {
  data() {
    return {
      username: undefined
    }
  },
  computed: {
    routes() {
      return this.$route.matched.filter(item => {
        return item.meta.title
      })
    }
  },
  created() {
    getInfo().then(({ data }) => {
      this.username = data.username
      if (!this.username) {
        // 未登录
        this.$router.push('/')
      }
    })
  },
  methods: {
    logout() {
      logout()
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
.navbar-container {
  display: block;
  text-align: left;
  line-height: 1;
}

.el-breadcrumb,
.setting {
  display: inline-block;
}

.setting {
  float: right;
}

.setting i {
  font-size: 21px;
}

.setting i:hover {
  color: #409eff;
  cursor: pointer;
}

a {
  text-decoration: unset;
}
</style>