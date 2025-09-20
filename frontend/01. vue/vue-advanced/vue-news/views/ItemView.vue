<template>
  <div>
    <section>
      <!-- 질문 상세 정보 -->
      <div class="user-container">
        <div>
          <i class="fa fa-user"></i>
        </div>
        <div class="user-description">
          <router-link v-bind:to="`/user/${fetchedItem.user}`"></router-link>
          <div class="time">
            {{ fetchedItem.itme_ago }}
          </div>
        </div>
        <h2>{{ fetchedItem.title }}</h2>
      </div>
    </section>

    <section>
      <!-- 질문 댓글 -->
      <div v-html="fetchedItem.comments">
      </div>
    </section>

  </div>
</template>

<script>
//vuex helper function
import {mapGetters} from 'vuex';

export default {
  computed: {
    ...mapGetters([
      'fetchedItem'
    ])
  },
  created() {
    const itemId = this.$route.params.id;
    this.$store.dispatch('FETCH_ITEM', itemId);

  }
}
</script>

<style scoped>
.user-container {
  display: flex;
  align-items: center;
  padding: .5rem;
}

.fa-user {
  font-size: 2.5rem;
}

.user-description {
  padding-left: 8px;
}

.time {
  font-size: .7rem;
}

</style>