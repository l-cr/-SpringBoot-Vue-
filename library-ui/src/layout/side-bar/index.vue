<template>
  <div class="sidebar-container" :style="{ backgroundColor: '#30333c' }">
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <el-menu :collapse="isCollapse" router :default-active="activeRoute" background-color="#30333c" text-color="#fff">
        <template v-for="route in routes">
          <el-submenu v-if="route.children && route.children.length > 1" :index="route.path" :key="route.path">
            <!-- 一级菜单标题 -->
            <template slot="title">
              <svg class="icon" aria-hidden="true">
                <use :xlink:href="'#icon' + route.meta.icon"></use>
              </svg>
              <span>{{ route.meta.title }}</span>
            </template>
            <!-- 二级菜单 -->
            <el-menu-item
              v-for="subRoute in route.children"
              :key="subRoute.path"
              :index="route.path + '/' + subRoute.path"
            >
              <svg class="icon" aria-hidden="true">
                <use :xlink:href="'#icon' + subRoute.meta.icon"></use>
              </svg>
              <span>{{ subRoute.meta.title }}</span>
            </el-menu-item>
          </el-submenu>

          <el-menu-item v-else :index="route.path + route.children[0].path" :key="route.path">
            <svg class="icon" aria-hidden="true">
              <use :xlink:href="'#icon' + route.children[0].meta.icon"></use>
            </svg>
            <span>{{ route.children[0].meta.title }}</span>
          </el-menu-item>
        </template>
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script>
import { getRole } from '@/utils/auth'

export default {
  data() {
    return {
      isCollapse: false
    }
  },
  computed: {
    routes() {
      // console.log(this.$router.options.routes.filter(item => !item.hidden))
      // console.log(getRole())
      let role = getRole()
      return this.$router.options.routes.filter(item => {
        return !item.hidden && (item.path == '/' || item.children[0].meta.role == role)
      })
    },
    activeRoute() {
      return this.$route.path
    }
  }
}
</script>

<style>
.scrollbar-wrapper.el-scrollbar__wrap {
  overflow-x: hidden !important;
}

.el-scrollbar__bar.is-horizontal {
  display: none !important;
}

.scrollbar-wrapper.el-scrollbar__wrap {
  margin-right: -18px !important;
}
</style>

<style scoped>
.sidebar-container {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  z-index: 9999;
  /* width: 200px; */
  height: 100%;
  font-size: 0;
  transition: width 0.28s;
  overflow: hidden;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}

.el-menu:not(.el-menu--collapse) {
  width: 200px !important;
}

.el-scrollbar {
  height: 100%;
}

/* .el-menu {
  width: 100%;
} */

.el-submenu-item i .el-menu-item i {
  margin-right: 16px;
}

.icon {
  width: 18px;
  height: 18px;
  padding-right: 14px;
}
</style>