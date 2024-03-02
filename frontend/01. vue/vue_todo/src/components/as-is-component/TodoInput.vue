<!--<template>-->
<!--    <div class="inputBox shadow">-->
<!--        <input type="text" v-model="this.newTodoItem" v-on:keyup.enter="this.addTodo">-->
<!--&lt;!&ndash;        <button v-on:click="addTodo">add</button>&ndash;&gt;-->
<!--        <span class="addContainer" v-on:click="this.addTodo">-->
<!--            <i class="fas fa-plus addBtn"></i>-->
<!--        </span>-->
<!--    </div>-->
<!--</template>-->

<!--<script>-->

<!--export default {-->
<!--    data: function () {-->
<!--        return {-->
<!--            newTodoItem: ""-->
<!--        }-->
<!--    }-->
<!--    , methods: {-->
<!--        addTodo: function () {-->
<!--            if (this.newTodoItem !== ''){-->

<!--                let obj = {completed: false, item: this.newTodoItem};-->
<!--                //저장하는 로직-->
<!--                //localStorage.setItem(this.newTodoItem, obj);-->
<!--                // JSON.stringify -> javascript 객체를 string형식으로 변경처리-->
<!--                localStorage.setItem(this.newTodoItem, JSON.stringify(obj));-->

<!--                this.clearInput();-->
<!--            }-->

<!--        },-->
<!--        clearInput: function () {-->
<!--            this.newTodoItem = "";-->
<!--        }-->

<!--    }-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->
<!--input:focus{-->
<!--    outline: none;-->
<!--}-->

<!--.inputBox {-->
<!--    background: white;-->
<!--    height: 50px;-->
<!--    line-height: 50px;-->
<!--    border-radius: 5px;-->
<!--}-->

<!--.inputBox input {-->
<!--    border-style: none;-->
<!--    font-size: 0.9rem;-->
<!--}-->

<!--.addContainer {-->
<!--    float: right;-->
<!--    background: linear-gradient(to right, #6478FB, #8763FB);-->
<!--    display: none;-->
<!--    width: 3rem;-->
<!--    border-radius: 0 5px 5px 0;-->
<!--}-->

<!--.addBtn {-->
<!--    color: white;-->
<!--    vertical-align: middle;-->
<!--}-->

<!--</style>-->