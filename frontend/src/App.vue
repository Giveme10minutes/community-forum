<script setup>
</script>
<template>

  <nav>
    <div class="nav-bar">
      <router-link to="/">首页</router-link>
      <router-link to="/login">登录</router-link>
      <router-link to="/register">注册</router-link>
      <router-link to="/create">发帖</router-link>
    </div>
  </nav>
  <div class="main-container">
    <router-view/>
  </div>

</template>


<style>

.main-container {
  margin-top: 24px;
  background: #ffffff;
  width: 100%;
  border-radius: 16px;
  box-shadow: 0 4px 20px rgba(0,0,0,0.08);
}

body {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
  font-family: system-ui, sans-serif;
  background: #f2f3f5;
}

nav {
  background: #ffffff;
  padding: 12px 24px;
  display: flex;
  width: 100%;
  box-sizing: border-box;
  gap: 20px;
}

nav a {
  margin-right: 16px;
  color: #595f70;
  text-decoration: none;
  font-size: 16px;
}

nav a:hover {
  color: #30343d;
}
</style>

