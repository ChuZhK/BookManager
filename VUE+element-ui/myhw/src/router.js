import { createRouter, createWebHashHistory} from "vue-router";
import Login from './components/login.vue'
import Home from './components/home.vue'

const router = createRouter({
    history:createWebHashHistory(),
    routes:[{
        path:"/",
        component: Login
    },
        {
        path:"/home",
        component:Home
        }]
})


export default router;