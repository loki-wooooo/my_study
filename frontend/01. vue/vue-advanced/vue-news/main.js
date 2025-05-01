import { createApp } from 'vue'
import App from './App.vue'
import routes from '@/routes/index'
import store from '@/store/index'

const app = createApp(App)
app.use(routes)     // 라우터 사용
app.use(store)      // store 사용
app.mount('#app')
