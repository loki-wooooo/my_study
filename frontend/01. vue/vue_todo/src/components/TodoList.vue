<template>
    <div>
        <transition-group name="list" tag="ul">
<!--            <li-->
<!--                v-for="(todoItem, index) in this.$props.propsdata"-->
<!--                v-bind:key="todoItem.item"-->
<!--                class="shadow"-->
<!--            >-->
            <!-- vuex store로 변경처리 -->
            <li
                v-for="(todoItem, index) in this.$store.state.todoItems"
                v-bind:key="todoItem.item"
                class="shadow"
            >
                <i
                    class="checkBtn fas fa-check"
                    v-bind:class="{checkBtnCompleted: todoItem.completed}"
                    v-on:click="this.toggleComplete(todoItem)"
                ></i>
                <!-- false면 textCompleted 표출 X, true textCompleted 표출 O -->
                <span v-bind:class="{textCompleted: todoItem.completed}">{{ todoItem.item }}</span>
                <span
                    class="removeBtn"
                    v-on:click="this.removeTodo(todoItem, index)"
                >
                    <i class="fas fa-trash-alt"></i>
                </span>

            </li>
        </transition-group>
    </div>
</template>

<script>

export default {
    props:['propsdata'],
    methods: {
        removeTodo(todoItem, index) {
            this.$emit('removeTodoItem', todoItem, index);
        },

        toggleComplete(todoItem, index) {
            this.$emit('toggleTodo음Item', todoItem, index);
        },
    },
}
</script>

<style scoped>
ul {
    list-style-type: none;
    padding-left: 0px;
    margin-top: 0px;
    text-align: left;
}

li {
    display: flex;
    min-height: 50px;
    height: 50px;
    line-height: 50px;
    margin: 0.5rem 0;
    padding: 0 0.9rem;
    background: white;
    border-radius: 5px;
}

.removeBtn {
    margin-left: auto;
    color: #de4343;
}

.checkBtn {
    line-height: 45px;
    color: #62acde;
    margin-right: 5px;
}

.checkBtnCompleted {
    color: #b3adad;
}

.textCompleted {
    text-decoration: line-through;
    color: #b3adad;
}


/* list item 트랜지션 효과 */
.list-enter-active, .list-leave-active {
    transition: all 1s;
}

.list-enter, .list-leave-to {
    opacity: 0;
    transform: translateY(30px);
}

</style>