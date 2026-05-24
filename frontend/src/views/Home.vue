<script setup>

import {onMounted, ref} from "vue";
import request from "../utils/request.js";

const posts = ref([])

onMounted(async () => {
  const res = await request.get('/post', {params: {pageNum: 1, pageSize: 10}})
  posts.value = res.data.data.records
})
</script>

<template>
  <div class="container">
    <div class="post-list">
      <h1>论坛</h1>
      <div class="post-item" v-for="post in posts" :key="post.id">
        <h2> {{ post.title }}</h2>
        <p>{{ post.content }}</p>
        <div class="post-meta">
          <span>评论：{{ post.commentCount }}</span>
          <span>浏览：{{ post.ViewCount }}</span>
        </div>
      </div>
    </div>
  </div>
</template>


<style scoped>
.container {
  //width: 100%;
  //margin: 0 auto;
  //padding: 20px;
}

.post-item {
  border-bottom: 1px solid #eee;
  padding: 16px 0;
}


.post-item h2 {
  margin: 0 0 8px;
  color: #387dc1;
}

.post-meta {
  color: #999;
  font-size: 14px;
}

.post-meta span {
  margin-right: 16px;
}
</style>
