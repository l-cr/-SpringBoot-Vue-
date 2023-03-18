<template>
  <div class="layout">
    <side-bar />
    <el-container>
      <el-header> <nav-bar /> </el-header>
      <el-main> <app-main /> </el-main>
    </el-container>
  </div>
</template>

<script>
import NavBar from './nav-bar'
import SideBar from './side-bar'
import AppMain from './app-main'

export default {
  components: { NavBar, SideBar, AppMain }
}
</script>

<style scoped>
.layout {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  overflow-y: auto;
  /* background-color: #e9eef3; */
}

.el-container {
  margin-left: 200px;
}

.el-header,
.el-footer {
  padding: 21px;
  color: #333;
  text-align: center;
  line-height: 60px;
  background-color: #ffffff;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.el-main {
  overflow-x: hidden;
}
</style>