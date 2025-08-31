import {
    fetchNews,
    fetchAsk,
    fetchJobs,
    fetchUserInfo,
} from '../api/index.js';

export default {
    FETCH_NEWS({ commit }) {
        return fetchNews().then(response => commit('SET_NEWS', response.data));
    },
    FETCH_ASK({ commit }) {
        return fetchAsk().then(response => commit('SET_ASK', response.data));
    },
    FETCH_JOBS({ commit }) {
        return fetchJobs().then(response => commit('SET_JOBS', response.data));
    },
    FETCH_USER_INFO({ commit }, name){
        return fetchUserInfo(name).then(response => commit('SET_USER', response.data));
    }
}