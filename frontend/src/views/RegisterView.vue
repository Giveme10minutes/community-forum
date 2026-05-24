<script setup>
import {useRouter} from "vue-router";
import {ref} from "vue";
import request from "../utils/request.js";

const router = useRouter();
const userName = ref("");
const password = ref("");
const errorMsg = ref("");

async function register() {
  errorMsg.value = "";
  const res = await request.post("/user/register", {
    userName: userName.value,
    password: password.value,
  });
  if (res.data.code !== 200) {
    errorMsg.value = res.data.message
    return
  }
  localStorage.setItem("token", res.data.data)
  router.push("/");
}

</script>

<template>
  <h3 v-if="errorMsg" class="error">{{ errorMsg }}</h3>
  <div class="register">
    <h2>注册</h2>
    <input v-model="userName" placeholder="用户名">
    <input v-model="password" type="password" placeholder="密码">
    <button @click="register">注册</button>
  </div>
</template>

<style scoped>
.register {
  max-width: 360px;
  margin: 60px auto;
  padding: 60px 60px 100px 60px;
  border: 1px solid #ddd;
  border-radius: 30px;
  background: #9fbcbc;
}

.register h2 {
  text-align: center;
  margin-bottom: 20px;
}

.register input {
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

.register input::placeholder {
  color: #999;
}

.register button {
  width: 100%;
  padding: 10px;
  background: #333;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.register button:hover {
  //background: #a5ac80;
  background: #838871;
}

.error {
  color: red;
  font-size: 14px;
  text-align: center;
  margin: 20px;
}
</style>