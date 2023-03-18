import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '@/layout'
import { getToken } from '@/utils/auth'
import { getInfo } from '@/api/login'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/login'),
    hidden: true // 设置hidden表示在菜单栏中不显示
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'dashboard',
        component: () => import('@/views/dashboard'),
        meta: { title: '控制台', icon: 'dashboard' }
      }
    ]
  },
  {
    path: '/admin/book',
    component: Layout,
    children: [
      {
        path: '',
        name: 'book',
        component: () => import('@/views/admin/book'),
        meta: { title: '图书管理', icon: 'book', role: 'admin' }
      }
    ]
  },
  {
    path: '/admin/reader',
    component: Layout,
    children: [
      {
        path: '',
        name: 'reader',
        component: () => import('@/views/admin/reader'),
        meta: { title: '读者管理', icon: 'reader', role: 'admin' }
      }
    ]
  },
  {
    path: '/admin/lend-record',
    component: Layout,
    children: [
      {
        path: '',
        name: 'lend-record',
        component: () => import('@/views/admin/lend-record'),
        meta: { title: '借阅管理', icon: 'lend-record', role: 'admin' }
      }
    ]
  },
  {
    path: '/reader/book',
    component: Layout,
    children: [
      {
        path: '',
        name: 'book',
        component: () => import('@/views/reader/book'),
        meta: { title: '图书查询', icon: 'book', role: 'reader' }
      }
    ]
  },
  {
    path: '/reader/lend-record',
    component: Layout,
    children: [
      {
        path: '',
        name: 'lend-record',
        component: () => import('@/views/reader/lend-record'),
        meta: { title: '借阅记录', icon: 'lend-record', role: 'reader' }
      }
    ]
  },
  {
    path: '/reader/profile',
    component: Layout,
    children: [
      {
        path: '',
        name: 'profile',
        component: () => import('@/views/reader/profile'),
        meta: { title: '读者信息', icon: 'reader', role: 'reader' }
      }
    ]
  }
]

const router = new VueRouter({
  // mode: 'history', //去掉url中的#
  routes
})

router.beforeEach(async (to, from, next) => {
  if (to.path === '/login') {
    next()
  } else {
    const token = getToken()
    let res
    await getInfo(getToken()).then(({ data }) => {
      res = data
    })
    // console.log(res)
    if (!token || !res) {
      next('/login')
    } else {
      next()
    }
  }
})

// 避免elementui面包屑重复跳转报错
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

export default router
