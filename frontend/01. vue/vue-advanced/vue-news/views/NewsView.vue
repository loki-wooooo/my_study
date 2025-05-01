<template>
  <div>
    <div :key="user.id" v-for="user in this.$store.state.news">{{ user.title }}</div>
    News
  </div>
</template>

<script>


export default {
  created() {
    //store 사용
    this.$store.dispatch('FETCH_NEWS');
  }
}
</script>