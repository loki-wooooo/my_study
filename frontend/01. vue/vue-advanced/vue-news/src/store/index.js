import {createStore} from 'vuex'
import {fetchNewsList} from '@/api/index.js';

export const store = createStore({
    state: {
        news: []
    },
    mutations: {
        //첫 파라미터는 state
        SET_NEWS(state, news) {
            state.news = news;
        }
    },
    actions: {
        //context를 통해 mutations로 commit 처리
        FETCH_NEWS: (context) => {
            fetchNewsList()
                .then(response => {
                    context.commit('SET_NEWS', response.data);
                }).catch(error => {
                console.error(error);
            })
        }
    },
})