import { createRouter, createWebHistory } from 'vue-router'
import AuthView from '@/views/AuthView.vue';
import MainLayout from '@/layouts/MainLayout.vue';
import ScheduleView from '@/views/ScheduleView.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: MainLayout,
      name: 'layout',
      children: [
        {
          path: '',
          redirect: {name: 'auth'}
        },
        {
          path: '/schedule',
          name: 'schedule',
          component: ScheduleView,
        },
      ]
    },
    {
      path: '/auth',
      name: 'auth',
      component: AuthView
    },
  ]
})

export default router
