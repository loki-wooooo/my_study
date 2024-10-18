<template>
  <div>
    <p> {{ message }} </p>
    <button @click="changeMessage"></button>
  </div>
</template>

<script>
import { ref } from 'vue';
import {useMessage} from './hooks/useMessage'

export default {
  setup() {
    /**
     * 장점
     *  타입 추론
     *  js import
     * 
     * 단점
     *  js import -> 각각의 많은 hooks의 파일을 분리해서 확인하기가 힘듬 (불필요한 전환 비용)
     *  composition api에 대한 학습이 필요함
     *  주의점 : setup에 많은 코드들이 들어가면, 수십, 수백줄이 되어 스파게티 코드가 되어버림.
     */
    // https://joshua1988.github.io/vue-camp/es6+/destructuring.html
    const { message, changeMessage } = useMessage();
    return { message, changeMessage }
  }



  // // data() {
  // //   return {
  // //     message: 'hello'
  // //   }
  // // },
  // setup() {
  //   // message -> data로 정의
  //   // ref 로 선언한 값은 ".value"로 값을 바꿈
  //   const message = ref('hello');

  //   //message
  //   // const changeMessage = () => {

  //   // }

  //   function changeMessage() {
  //     message.value = 'hi'
  //   }

  //   // 정의한 데이터, 메서드, computed, watch를 다 넣어야함
  //   // setup 밖에서 사용을 할 수 있음
  //   return { message, changeMessage }

  // }
  // // methods: {
  // //   changeMessage() {
  // //     this.message = 'hi'
  // //   }
  // // }
}
</script>

<style lang="scss" scoped></style>