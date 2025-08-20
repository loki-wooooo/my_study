import {createRouter, createWebHistory} from "vue-router";
import NewsView from '../../views/NewsView.vue'
import AskView from '../../views/AskView.vue'
import UserView from '../../views/UserView.vue'

const routes = [
  // 여기에 라우트 정의
  // 예: { path: '/', component: Home },
  //     { path: '/about', component: About },
  /**
   * PATH -> URL 주소
   * COMPOMENT -> URL 주소로 갔을 때 표시되는 컴포넌트
  */
  {
    path: '/',
    redirect: '/news',

  },
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
  {
    path: '/user',
    component: UserView,
  },
  {
    path: '/user/:id',
    component: UserView,
  }
];

const router = createRouter({
  history: createWebHistory(), // url : http://naver.com/#/news -> "#"제거용
  routes,
});

export default router;
