import {createRouter, createWebHistory} from "vue-router";

const routes = [
    {
        path: '/',
        name: 'home',
        component: () => import('../views/Home.vue')
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('../views/LoginView.vue'),
    },
    {
        path: '/register',
        name: 'register',
        component: () => import('../views/RegisterView.vue'),
    },
    {
        path: "/post",
        name: 'creatPost',
        component: () => import('../views/createPostView.vue'),
    },
    {
        path: '/post/:id',
        name: 'post',
        component: () => import('../views/PostView.vue'),
    }
]

const router=createRouter({
    history: createWebHistory(),
    routes,
})

export default router