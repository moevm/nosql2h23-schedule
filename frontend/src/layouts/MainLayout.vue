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
    >
      <v-list>
        <v-list-item
            title="Sandra Adams"
            subtitle="sandra_a88@gmailcom"
            class="px-3 py-4"
        >
          <template v-slot:prepend>
            <v-icon
                icon="mdi-account-circle-outline"
                size="x-large"
                color="#2962FF"
            ></v-icon>
          </template>
        </v-list-item>
      </v-list>

      <v-divider></v-divider>

      <v-list density="compact" nav>
        <v-list-item prepend-icon="mdi-folder" title="My Files" value="myfiles"></v-list-item>
        <v-list-item prepend-icon="mdi-account-multiple" title="Shared with me" value="shared"></v-list-item>
        <v-list-item prepend-icon="mdi-star" title="Starred" value="starred"></v-list-item>
      </v-list>

      <template v-slot:append>
        <div class="d-flex flex-column mb-4">
          <v-list-item
              rounded
              title="Выйти"
              @click="logout"
          >
            <template v-slot:prepend>
              <v-icon
                  icon="mdi-logout"
                  color="#2962FF"
              ></v-icon>
            </template>
          </v-list-item>
        </div>
      </template>
    </v-navigation-drawer>

    <v-main class="d-flex align-center justify-center">
      <router-view/>
    </v-main>
  </v-layout>
</template>

<script
    setup
    lang="ts"
>
import {useRouter} from "vue-router";

const router = useRouter();
const logout = () => {
  router.push({name: 'auth'});
}
</script>

<style scoped>
.bottom-bordered{
  border-bottom: rgba(0, 0, 0, 0.1) 1px solid;
}
</style>