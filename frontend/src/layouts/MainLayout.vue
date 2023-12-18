<template>
  <v-layout class="rounded rounded-md">
    <v-app-bar
        :elevation="0"
    >
      <div class="logo ml-16">
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
        >
          <template v-slot:prepend>
            <v-icon icon="account-circle-outline"></v-icon>
          </template>
        </v-list-item>
      </v-list>

      <v-divider></v-divider>

      <v-list density="compact">
        <v-list-item prepend-icon="mdi-folder" title="My Files"></v-list-item>
        <v-list-item prepend-icon="mdi-account-multiple" title="Shared with me"></v-list-item>
        <v-list-item prepend-icon="mdi-star" title="Starred"></v-list-item>
      </v-list>

      <template v-slot:append>
        <div class="pa-2 ma-4 d-flex flex-column">
          <div class="spacer my-4">dddd</div>
          <v-btn
            block
            @click="logout"
          >
            Logout
          </v-btn>
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

</style>