import { createRouter, createWebHistory } from "vue-router";
import { createApp } from "vue";
import NewsView from '../views/NewsView.vue'
import AskView from '../views/AskView.vue'
import JobsView from '../views/JobsView.vue'

const routes = [
  // 여기에 라우트 정의
  // 예: { path: '/', component: Home },
  //     { path: '/about', component: About },
  /**
   * PATH -> URL 주소
   * COMPOMENT -> URL 주소로 갔을 때 표시되는 컴포넌트
  */
  {
    path: '/news',
    component: NewsView,
  },
  {
    path: '/ask',
    component: AskView,
  },
  {
    path: '/jobs',
    component: '',
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
