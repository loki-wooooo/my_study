<template>
  <div>
    <p v-for="item in this.$store.state.news">
      <a v-bind:href="item.url">{{ item.title }}</a>
      <small>{{item.time_ago}} by
        <router-link v-bind:to="`/user/${item.user}`">{{item.user}}</router-link>
      </small>
    </p>
  </div>
</template>

<script>
import { fetchUser } from '../api/index.js';

export default {
  created() {
    fetchUser('davideast')
        .then(response => console.log(response))
        .catch(error => console.log(error));
  }
}
</script>

<style>

</style>