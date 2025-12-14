<template>
    <!--    <ul class="news-list">-->
    <!--        <li v-for="item in this.$store.state.jobs" :key="item.id" class="post">-->
    <!--            <div class="points">-->
    <!--                {{ item.points || 0 }}-->
    <!--            </div>-->

    <!--            <div>-->
    <!--                <p class="news-title">-->
    <!--                    <a :href="item.url">{{ item.title }}</a><br>-->
    <!--                </p>-->
    <!--                <small class="link-text">{{ item.time_ago }} by-->
    <!--                    <a :href="item.url">-->
    <!--                        {{ item.domain }}-->
    <!--                    </a>-->
    <!--                </small>-->
    <!--            </div>-->
    <!--        </li>-->
    <!--    </ul>-->
    <list-item/>
</template>

<script>
import ListItem from "@/components/ListItem.vue";

export default {
    created() {
        this.$store.dispatch('FETCH_JOBS')
            .then(() => console.log('success'))
            .catch(() => console.log('fail'));
    },
    components: {ListItem}
}
</script>

<style scoped>
</style>