import { createApp } from 'vue'
import App from './App.vue'
import { store }  from './store/index'
import { router } from './routes/index'


const app = createApp(App)
app.use(router)
app.use(store)
app.mount('#app')