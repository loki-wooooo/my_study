<template>
    <div class="inputBox shadow">
        <input type="text" v-model="this.newTodoItem" v-on:keyup.enter="this.addTodo">
        <!--        <button v-on:click="addTodo">add</button>-->
        <span class="addContainer" v-on:click="this.addTodo">
            <i class="fas fa-plus addBtn"></i>
        </span>

        <AlertModal v-if="showModal" @close="showModal = false">
            <!--
              you can use custom content here to overwrite
              default content
            -->
            <template v-slot:header>
                <h3>경고</h3>
                <i
                    class="closeModalBtn fas fa-times"
                    @click="this.showModal = false"
                ></i>
            </template>


            <!--           바디 :  무언가를 입력하세요-->
            <!--           푸터 :  copy right-->

            <template v-slot:body>
                <div>아무것도 입력하지 않으셨습니다.</div>
            </template>

<!--            <template v-slot:footer>-->
<!--                <h3>Copy Right hswoo</h3>-->
<!--            </template>-->



        </AlertModal>
    </div>
</template>

<script>

import AlertModal from "./common/AlertModal.vue";

export default {
    components: {
        AlertModal
    },

    data() {
        return {
            newTodoItem: "",
            showModal: false
        }
    }
    , methods: {
        addTodo() {
            if (this.newTodoItem !== '') {
                // this.$emit('이벤트 이름', 인자1, 인자2, ...);
                this.$emit('addTodoItem', this.newTodoItem);
                this.clearInput();
            } else {
                this.showModal = !this.showModal;
            }

        },
        clearInput() {
            this.newTodoItem = "";
        }

    }
}
</script>

<style scoped>
input:focus {
    outline: none;
}

.inputBox {
    background: white;
    height: 50px;
    line-height: 50px;
    border-radius: 5px;
}

.inputBox input {
    border-style: none;
    font-size: 0.9rem;
}

.addContainer {
    float: right;
    background: linear-gradient(to right, #6478FB, #8763FB);
    display: none;
    width: 3rem;
    border-radius: 0 5px 5px 0;
}

.addBtn {
    color: white;
    vertical-align: middle;
}

.closeModalBtn {
    color: #42b983;
}

</style>