<template>
  <!--1. Strongly Recommended-->
  <!-- <Toolbar></Toolbar> -->
  <!--2. Essential-->
  <tool-bar></tool-bar>
  <router-view></router-view>
</template>

<script>

import ToolBar from './components/ToolBar.vue';


export default {
  components: {
    ToolBar,
  },
  methods: {

    fetchData() {
      console.log('hello');
    }

  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

body {
  padding: 0;
  margin: 0;
}
</style>
