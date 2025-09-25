<template>
    <div>
        <ul class="news-list">
            <li v-for="news in this.$store.state.news" :key="news.id" class="post">
                <!-- 포인트 입력 -->
                <div class="points">
                    {{ item.points }}
                </div>

                <!-- 기타 정보 입력 -->
                <div>
                    <p class="news-title">
                        <a :href="news.url">{{ news.title }}</a>
                    </p>
                    <small class="link-text">{{ news.time_ago }} by
                        <router-link v-bind:to="`/user/${news.user}`">{{ news.user }}</router-link>
                    </small>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    created() {
        this.$store.dispatch('FETCH_NEWS')
            .then(() => console.log('success'))
            .catch(() => console.log('fail'));
    }
}
</script>

<style scoped>
.news-list {
    margin: 0;
    padding: 0;
}

.post {
    list-style-type: none;
    display: flex;
    align-items: center;
    border-bottom: 1px solid #eee;
}

.points {
    width: 80px;
    height: 60px;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #41b883;
}

.news-title {
    margin: 0;
}

.link-text {
    color: #999;
}
</style>