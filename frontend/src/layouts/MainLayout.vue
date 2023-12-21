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
      @update:rail="isMenuHovering=!isMenuHovering"
      width="270"
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
<!--        <v-list-item prepend-icon="mdi-office-building-cog-outline" title="Генерация расписания" value="generation" rounded="xl"></v-list-item>-->
      </v-list>

      <template #append>
        <template v-if="isMenuHovering">
          <v-btn
              class="pa-3 w-100 mb-3 d-flex align-center justify-center"
              variant="flat"
              color="#255ED6"
              rounded="xl"
              @click="logout"
          >
            <template #prepend>
              <v-icon
                  icon="mdi-logout"
              ></v-icon>
            </template>
            Выйти
          </v-btn>
        </template>

        <template v-else>
          <v-list>
            <v-list-item
                class="py-3"
                color="primary"
                rounded="xl"
                @click="logout"
            >
              <template #prepend>
                <v-icon
                    icon="mdi-logout"
                    color="#2962FF"
                ></v-icon>
              </template>

            </v-list-item>
          </v-list>
        </template>

      </template>
    </v-navigation-drawer>

    <v-main class="d-flex flex-column align-center justify-center">
      <div class="main-background pa-5 pb-6 w-100">
        <router-view/>
      </div>

      <v-footer class="w-100 border justify-end">
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
import { onMounted, ref } from 'vue';
import { LocalStorageKeys } from '@/components/auth/model/localStorageKeys';
import { VNavigationDrawer } from 'vuetify/components';
import auth from '@/components/auth/api/Auth';

const userFio = ref('');
const userName = ref('');

const logout = async () => {
  await auth.logout();
}

const isMenuHovering = ref(false);

onMounted(() => {
  userFio.value = localStorage.getItem(LocalStorageKeys.FIO_KEY) || 'userFullname';
  userName.value = localStorage.getItem(LocalStorageKeys.USERNAME_KEY) || 'user_email@mail.ru';
})
</script>

<style scoped>
.bottom-bordered{
  border-bottom: rgba(0, 0, 0, 0.1) 1px solid;
}
.main-background {
  background-color: #F2F5F9;
}
</style>