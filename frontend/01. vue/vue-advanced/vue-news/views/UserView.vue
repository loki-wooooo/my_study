<template>
  <div>
    <p>name: {{ userInfo.id}}</p>
    <p>karma: {{ userInfo.karma}}</p>
    <p>created: {{ userInfo.created  }}</p>
  </div>
</template>

<script>
import {fetchUser} from '../src/api/index.js';

export default {
  computed: {
    userInfo() {
      return this.$store.state.user
    }
  },
  created() {
    fetchUser('davideast')
        .then(response => console.log(response))
        .catch(error => console.log(error));
  }
}
</script>

<style>

</style>