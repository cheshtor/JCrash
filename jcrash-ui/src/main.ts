import { createApp } from 'vue'
import {Dialog, Notify, Quasar} from 'quasar'
import quasarLang from 'quasar/lang/zh-CN'

import '@quasar/extras/material-icons/material-icons.css'
import 'quasar/src/css/index.sass'

import App from './App.vue'
import router from "./router"
import {key, store} from './store/store'

const myApp = createApp(App)

myApp.use(Quasar, {
    plugins: {Dialog, Notify},
    lang: quasarLang,
})

myApp.use(router)
myApp.use(store, key)

myApp.mount('#app')
