import { createApp } from 'vue'
import App from './App.vue'
import routes from '@/routes/index'


const app = createApp(App)
app.use(routes)  // 라우터 사용
app.mount('#app')
