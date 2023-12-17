import { createRouter, createWebHistory } from 'vue-router'
import AuthView from '@/views/AuthView.vue';
import MainLayout from '@/layouts/MainLayout.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: MainLayout,
      name: 'layout',
      children: [
        {
          path: 'auth',
          name: 'auth',
          component: AuthView
        },
      ]
    },
  ]
})

export default router
