import Vuex from 'vuex'
import * as getters from './getters'
import * as mutations from './mutations'

// use vue의 플러그인
// 사용시 vue의 global 영역에서 추가할 시 사용함
// vuex 설치 및 등록


const storage = {
    fetch() {
        const arr = [];
        if (localStorage.length > 0) {
            for (let i = 0; i < localStorage.length; i++) {
                if (localStorage.key(i) !== 'loglevel:webpack-dev-server') {
                    arr.push(JSON.parse(localStorage.getItem(localStorage.key(i))));
                }
            }
        }
        return arr;
    },
    // fetch() 와 같음
    // fetch: function () {
    //
    // }
}

export const store = new Vuex.Store({
    state: {
        // headerText: "TODO it!"
        todoItems: storage.fetch(),
    },
    getters,
    mutations
});