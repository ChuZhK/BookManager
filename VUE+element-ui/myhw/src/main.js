
import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
// 引入路由
import router from "./router"
import * as ElIcons from '@element-plus/icons'

const app = createApp(App)
Object.keys(ElIcons).forEach(key => {
    app.component(key, ElIcons[key])
})
app.use(ElementPlus)
app.use(router)
app.mount('#app')