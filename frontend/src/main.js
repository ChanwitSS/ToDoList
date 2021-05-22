import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'  
import 'ant-design-vue/dist/antd.css';
import {Table,Input,DatePicker,Button,Divider,Form,Select,Radio, Popconfirm} from 'ant-design-vue'

Vue.config.productionTip = false
Vue.use(Table)
Vue.use(Input)
Vue.use(DatePicker)
Vue.use(Button)
Vue.use(Divider)
Vue.use(Form)
Vue.use(Select)
Vue.use(Radio)
Vue.use(Popconfirm)

Vue.use(VueAxios, axios);

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
