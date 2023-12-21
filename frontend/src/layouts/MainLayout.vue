<template>
  <v-layout class="rounded rounded-md">
    <v-app-bar
        :elevation="0"
        class="bottom-bordered"
    >
      <div class="logo ml-4">
        <img src="src/assets/full-logo.svg" alt="logo"/>
      </div>
    </v-app-bar>

    <v-navigation-drawer
      expand-on-hover
      rail
      ref="navMenu"
    >
      <v-list>
        <v-list-item
            :title="userFio"
            :subtitle="userName"
            class="px-3 py-4"
        >
          <template v-slot:prepend>
            <v-icon
                icon="mdi-account-circle-outline"
                color="#2962FF"
                size="x-large"
            ></v-icon>
          </template>
        </v-list-item>
      </v-list>

      <v-divider></v-divider>

      <v-list density="comfortable" nav>
        <v-list-item prepend-icon="mdi-table-clock" title="Просмотр расписания" value="schedule" rounded="xl"></v-list-item>
        <v-list-item prepend-icon="mdi-office-building-cog-outline" title="Генерация расписания" value="generation" rounded="xl"></v-list-item>
      </v-list>

      <template v-slot:append>
        <div class="d-flex flex-column">
          <v-list>
            <v-list-item
                class="py-3"
                color="primary"
                rounded="xl"
                @click="logout"
            >
              <v-list-item-title class="ml-3 font-weight-bold">Выйти</v-list-item-title>
              <template #prepend>
                <v-icon
                    class="ml-3"
                    icon="mdi-logout"
                    color="№2962FF"
                ></v-icon>
              </template>

            </v-list-item>
          </v-list>
        </div>
      </template>
    </v-navigation-drawer>

    <v-main class="d-flex flex-column align-center justify-center">
      <router-view/>

      <v-footer class="w-100 h-10 border">
        <v-row justify="center" no-gutters>
          <v-col class="text-center" cols="12" style="color: #99989F;">
            Проект по нереляционным СУБД. 2023{{ new Date().getFullYear() !== 2023 ? ' - ' + new Date().getFullYear() : '' }}. <strong>SmartScheduler</strong>
          </v-col>
        </v-row>
      </v-footer>
    </v-main>
  </v-layout>
</template>

<script
    setup
    lang="ts"
>
import {useRouter} from "vue-router";
import { onMounted, ref } from 'vue';
import { LocalStorageKeys } from '@/components/auth/model/localStorageKeys';

const userFio = ref('');
const userName = ref('');

const router = useRouter();
const logout = () => {
  router.push({name: 'auth'});
}
const navMenu = ref

onMounted(() => {
  userFio.value = localStorage.getItem(LocalStorageKeys.FIO_KEY) || '';
  userName.value = localStorage.getItem(LocalStorageKeys.USERNAME_KEY) || '';
})
</script>

<style scoped>
.bottom-bordered{
  border-bottom: rgba(0, 0, 0, 0.1) 1px solid;
}
</style>