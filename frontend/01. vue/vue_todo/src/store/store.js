import Vuex from 'vuex'

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
    mutations: {
        addOneItem(state, todoItem) {
            let obj = {completed: false, item: todoItem};
            localStorage.setItem(todoItem, JSON.stringify(obj));
            state.todoItems.push(obj)
        },

        removeOneItem(state, payload) {
            localStorage.removeItem(payload.todoItem.item);
            state.todoItems.splice(payload.index, 1); //배열을 변경해서 새로 넣어줌
        },

        toggleOneItem(state, payload) {
            state.todoItems[payload.index].completed = !state.todoItems[payload.index].completed // to-be
            localStorage.removeItem(payload.todoItem.item);
            localStorage.setItem(payload.todoItem.item, JSON.stringify(payload.todoItem));
        },

        clearAllItems(state) {
            localStorage.clear();
            state.todoItems = [];
        }
    }
});