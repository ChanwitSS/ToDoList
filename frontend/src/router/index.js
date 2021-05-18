import Vue from 'vue'
import VueRouter from 'vue-router'
import Homepage from '../views/Homepage.vue'
import AddAssignment from '../views/AddAssignment.vue'
import 'ant-design-vue/dist/antd.css';

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Homepage',
    component: Homepage
  },
  {
    path: '/addAssignment',
    name: 'AddAssignment',
    component: AddAssignment
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
