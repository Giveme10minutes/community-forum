<script setup>
import {useRouter} from "vue-router";
import {ref} from "vue";
import request from "../utils/request.js";

const router = useRouter();
const userName = ref('')
const password = ref('')

async function login() {
  const res = await request.post("/user/login", {
    userName: userName.value,
    password: password.value,
  })
  localStorage.setItem("token", res.data.data)
  router.push('/')
}
</script>

<template>
  <div class="login">
    <h2>登录</h2>
    <input v-model="userName" placeholder="用户名">
    <input v-model="password" type="password" placeholder="密码">
    <button @click="login">登录</button>
  </div>
</template>

<style scoped>
.login {
  max-width: 360px;
  margin: 60px auto;
  padding: 60px;
  border: 1px solid #ddd;
  border-radius: 30px;
  background: #9fbcbc;
}

.login h2 {
  text-align: center;
  margin-bottom: 20px;
}

.login input {
  text-align: center;
  margin-bottom: 20px;
  width: 100%;
  padding: 10px;
  border: 1px solid #fbfbfb;
  background: #fff;
  color: #333;
  border-radius: 4px;
  box-sizing: border-box;
}

.login input::placeholder {
  color: #999;
}

.login button {
  width: 100%;
  padding: 10px;
  background: #333;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.login button:hover {
  //background: #a5ac80;
  background: #838871;
}
</style>