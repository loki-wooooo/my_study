<template>
    <div id="app">
        <TodoHeader/>
        <TodoInput
            v-on:addTodoItem="this.addOneItem"
        />
        <!--        <TodoInput-->
        <!--            v-on:하위 컴포넌트에서 발생시킨 이벤트 이름="현재 컴포넌트의 메서드 명"-->
        <!--        />-->
        <TodoList
            v-bind:propsdata="this.todoItems"
            v-on:removeTodoItem="this.removeOneItem"
            v-on:toggleTodoItem="this.toggleOneItem"
        />
        <!--        <TodoList -->
        <!--            v-bind:내려보낼 프롭스 송성 이름 = "현재 위치의 컴포넌트 데이터 속성"-->
        <!--        />-->
        <TodoFooter
            v-on:clearAllTodoItem="this.clearAllItems"
        />
    </div>
</template>

<script>
import TodoHeader from './components/TodoHeader.vue'
import TodoInput from './components/TodoInput.vue'
import TodoList from './components/TodoList.vue'
import TodoFooter from './components/TodoFooter.vue'

export default {

    components: {
        // 컴포넌트 태그 : 컴포넌트 내
        TodoHeader,
        TodoInput,
        TodoList,
        TodoFooter
    },

    data() {
        return {
            todoItems: []
        }
    },
    methods: {
        addOneItem(todoItem) {
            let obj = {completed: false, item: todoItem};
            localStorage.setItem(todoItem, JSON.stringify(obj));
            this.todoItems.push(obj)
        },
        removeOneItem(todoItem, index) {
            localStorage.removeItem(todoItem.item);
            this.todoItems.splice(index, 1); //배열을 변경해서 새로 넣어줌
        },
        toggleOneItem(todoItem, index) {
            /**
             * props를 이용해서 내려준 todoItems 데이터를 다시 emit(이밴트 버스)으로 받는 형식은 좋지 않은 패턴
             * anti patterns 라고 불림
             * 직접 container의 데이터를 조작하는게 좋음
             */
            // todoItem.completed = !todoItem.completed; // as-is
            this.todoItems[index].completed = !this.todoItems[index].completed // to-be

            // 로컬 스토리지의 데이터를 갱신
            // 로컬 스토리지는 수정이 없음
            localStorage.removeItem(todoItem.item);
            localStorage.setItem(todoItem.item, JSON.stringify(todoItem));
        },
        clearAllItems() {
            localStorage.clear();
            this.todoItems = [];
        }
    },

    //life cycle hook
    // created() {
    //     if (localStorage.length > 0) {
    //         for (let i = 0; i < localStorage.length; i++) {
    //             if (localStorage.key(i) !== 'loglevel:webpack-dev-server') {
    //                 this.todoItems.push(JSON.parse(localStorage.getItem(localStorage.key(i))));
    //             }
    //         }
    //     }
    // }
}
</script>

<style>
body {
    text-align: center;
    background-color: #F6F6F6;
}

input {
    border-style: groove;
    width: 200px;
}

button {
    border-style: groove;
}

.shadow {
    box-shadow: 5px 10px 10px rgba(0, 0, 0, 0.03);
}

</style>
