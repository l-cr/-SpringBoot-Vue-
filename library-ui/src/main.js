import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import '@/assets/styles/index.css' // 全局css样式

import '@/utils/index.js' // 全局工具类

import '@/assets/icon/iconfont.js' // 图标
import '@/assets/icon/iconfont.css'

Vue.config.productionTip = false

Vue.use(ElementUI)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
