<template>
  <div class="inputBox shadow">
    <input type="text" v-model="this.newTodoItem" v-on:keyup.enter="this.addTodo">
    <span class="addContainer" v-on:click="this.addTodo">
        <i class="fas fa-plus addBtn"></i>
    </span>

    <AlertModal v-if="showModal" @close="showModal = false">
      <template v-slot:header>
        <h3>경고</h3>
        <i
            class="closeModalBtn fas fa-times"
            @click="this.showModal = false"
        ></i>
      </template>

      <template v-slot:body>
        <div>아무것도 입력하지 않으셨습니다.</div>
      </template>
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
        this.$store.commit('addOneItem', this.newTodoItem)
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