<template>
  <div class="auth fill-height">
    <v-container class="d-flex fill-height">
      <v-row>
        <v-spacer />

        <v-col cols="5">
          <LoginCard @login-success="onLoginSuccess" />
        </v-col>

        <v-spacer />
      </v-row>
    </v-container>
  </div>
</template>

<script
    setup
    lang="ts"
>
import {useRouter} from "vue-router";
import LoginCard from '@/components/auth/LoginCard.vue'

const router = useRouter();

const onLoginSuccess = () => {
  router.replace({ name: 'schedule' });
};
</script>

<style scoped>

</style>