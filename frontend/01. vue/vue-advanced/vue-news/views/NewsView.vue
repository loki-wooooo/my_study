<template>
    <div>
        <div v-for="user in users">{{ user.title }}</div>
        News
    </div>
</template>

<script>

import axios from 'axios';
import { fetchNewsList } from '@/api/index.js';

export default {
    data() {
        return {
            users: []
        }
    },
    created() {
        console.log("호출 전: ", this)

        fetchNewsList()
        .then(response => {
            console.log("호출 후: ", this)
            this.users = response.data
        })
        .catch(function(error){
            console.error(error);
        })

        // // 바인딩 문제로 인한 데이터 변수 처리
        // // vue 컴포넌트에 바로 붙지를 못함
        // var vm = this;
        // axios.get('https://api.hnpwa.com/v0/news/1.json')
        //     .then(function (response) {
        //         console.log(response);
        //         vm.users = response.data;
        //     })
        //     .catch(function (error) {
        //         console.error(error)
        //     })
    }
}
</script>

<style></style>