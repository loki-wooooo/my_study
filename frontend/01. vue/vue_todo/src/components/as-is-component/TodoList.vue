<!--<template>-->
<!--    <div>-->
<!--        <ul>-->
<!--            <li-->
<!--                v-for="(todoItem, index) in this.todoItems"-->
<!--                v-bind:key="todoItem.item"-->
<!--                class="shadow"-->
<!--            >-->
<!--                <i-->
<!--                    class="checkBtn fas fa-check"-->
<!--                    v-bind:class="{checkBtnCompleted: todoItem.completed}"-->
<!--                    v-on:click="this.toggleComplete(todoItem)"-->
<!--                ></i>-->
<!--                &lt;!&ndash; false면 textCompleted 표출 X, true textCompleted 표출 O &ndash;&gt;-->
<!--                <span v-bind:class="{textCompleted: todoItem.completed}">{{ todoItem.item }}</span>-->
<!--                <span-->
<!--                    class="removeBtn"-->
<!--                    v-on:click="this.removeTodo(todoItem, index)"-->
<!--                >-->
<!--                    <i class="fas fa-trash-alt"></i>-->
<!--                </span>-->

<!--            </li>-->
<!--        </ul>-->
<!--    </div>-->
<!--</template>-->

<!--<script>-->

<!--export default {-->
<!--    data: function () {-->
<!--        return {-->
<!--            todoItems: []-->
<!--        }-->
<!--    },-->

<!--    methods: {-->
<!--        removeTodo: function (todoItem, index) {-->
<!--            // console.log('remove items');-->
<!--            localStorage.removeItem(todoItem);-->
<!--            this.todoItems.splice(index, 1); //배열을 변경해서 새로 넣어줌-->
<!--        },-->

<!--        toggleComplete: function (todoItem) {-->
<!--            todoItem.completed = !todoItem.completed;-->
<!--            // 로컬 스토리지의 데이터를 갱신-->
<!--            // 로컬 스토리지는 수정이 없음-->
<!--            localStorage.removeItem(todoItem.item);-->
<!--            localStorage.setItem(todoItem.item, JSON.stringify(todoItem));-->
<!--        },-->
<!--    },-->

<!--    //life cycle hook-->
<!--    created: function () {-->
<!--        if (localStorage.length > 0) {-->
<!--            for (let i = 0; i < localStorage.length; i++) {-->
<!--                if (localStorage.key(i) !== 'loglevel:webpack-dev-server') {-->

<!--                    this.todoItems.push(JSON.parse(localStorage.getItem(localStorage.key(i))));-->
<!--                    // this.todoItems.push(localStorage.key(i));-->
<!--                }-->
<!--            }-->
<!--        }-->
<!--    }-->

<!--}-->
<!--</script>-->

<!--<style scoped>-->
<!--ul {-->
<!--    list-style-type: none;-->
<!--    padding-left: 0px;-->
<!--    margin-top: 0px;-->
<!--    text-align: left;-->
<!--}-->

<!--li {-->
<!--    display: flex;-->
<!--    min-height: 50px;-->
<!--    height: 50px;-->
<!--    line-height: 50px;-->
<!--    margin: 0.5rem 0;-->
<!--    padding: 0 0.9rem;-->
<!--    background: white;-->
<!--    border-radius: 5px;-->
<!--}-->

<!--.removeBtn {-->
<!--    margin-left: auto;-->
<!--    color: #de4343;-->
<!--}-->

<!--.checkBtn {-->
<!--    line-height: 45px;-->
<!--    color: #62acde;-->
<!--    margin-right: 5px;-->
<!--}-->

<!--.checkBtnCompleted {-->
<!--    color: #b3adad;-->
<!--}-->

<!--.textCompleted {-->
<!--    text-decoration: line-through;-->
<!--    color: #b3adad;-->
<!--}-->

<!--</style>-->