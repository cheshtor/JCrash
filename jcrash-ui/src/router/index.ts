import {createRouter, createWebHashHistory} from 'vue-router'

const modules = import.meta.glob('../pages/**/*.vue')

const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/',
            redirect: '/config'
        },
        {
            path: '/config',
            component: modules['../pages/config/Config.vue'],
            meta: {
                label: '全局配置',
            }
        },
        {
            path: '/project',
            component: modules['../pages/project/Project.vue'],
            meta: {
                label: '项目管理',
            }
        },
    ]
})

export default router