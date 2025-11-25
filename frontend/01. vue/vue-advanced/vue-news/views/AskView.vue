<template>
    <div>
      <list-item/>
<!--        <ul class="news-list">-->
<!--            <li v-for="item in fetchedAsk" :key="item.id" class="post">-->
<!--                &lt;!&ndash; 포인트 입력 &ndash;&gt;-->
<!--                <div class="points">-->
<!--                    {{ item.points }}-->
<!--                </div>-->

<!--                &lt;!&ndash; 기타 정보 입력 &ndash;&gt;-->
<!--                <div>-->
<!--                    <p class="news-title">-->
<!--                        <router-link v-bind:to="`/item/${item.id}`">-->
<!--                            {{item.title}}-->
<!--                        </router-link>-->
<!--                    </p>-->
<!--                    <small class="link-text">{{ item.time_ago }} by-->
<!--                        <router-link v-bind:to="`/user/${item.user}`">{{ item.user }}</router-link>-->
<!--                    </small>-->
<!--                </div>-->
<!--            </li>-->
<!--        </ul>-->
    </div>
</template>

<script>
import ListItem from '@/components/ListItem.vue'
// import {mapGetters} from 'vuex';

export default {
  components: { ListItem },
    // computed: {
    //     ...mapGetters(
    //         ['fetchedAsk']
    //     )
    // }
}
</script>

<style scoped>
</style>