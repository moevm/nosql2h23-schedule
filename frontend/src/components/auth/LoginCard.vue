<template>
  <v-card :loading="isLoginLoading" elevation="1" rounded="xl" class="px-5">
<!--    <v-card-title class="text-center mt-3" style="color: #658de2;">Авторизация</v-card-title>-->
    <div class="d-flex w-100 justify-center logo mt-16">
      <img width="487" src="src/assets/full-logo.svg" alt="logo"/>
    </div>


    <v-card-text class="mx-7">
      <v-form
          ref="loginFormRef"
          data-testid="loginData-form"
          @submit.prevent="onClickSubmitLogin"
      >
        <v-text-field
            v-model="loginInputData.email"
            :rules="loginValidationRules.email"
            autocomplete="username"
            clearable
            label="Логин"
            variant="outlined"
            base-color="#658de2"
            color="#658de2"
        />
        <v-text-field
            v-model="loginInputData.password"
            :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="loginValidationRules.password"
            :type="showPassword ? 'text' : 'password'"
            autocomplete="current-password"
            label="Пароль"
            variant="outlined"
            @click:append="onClickToggleShowPassword"
            color="#658de2"
        />

        <v-btn
            :disabled="
            !(
              loginInputData.isContainsValues() && !loginFormRef?.errors?.length
            )
          "
            :loading="isLoginLoading"
            block
            class="mt-4 mb-6 py-5"
            color="#658de2"
            data-testid="submit-button"
            type="submit"
            rounded="lg"
        >
          Войти
        </v-btn>
      </v-form>
    </v-card-text>
  </v-card>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue';
import  type { VForm } from 'vuetify/components';
import loginValidationRules from '@/components/auth/model/loginValidation';
import LoginData from '@/components/auth/model/loginData';
import auth from '@/components/auth/api/Auth';

const emit = defineEmits(['login-success']);

const showPassword = ref(false);
const loginFormRef = ref<VForm | null>(null);
const loginInputData = reactive(new LoginData());
const isLoginLoading = ref(false);

const onClickToggleShowPassword = () => {
  showPassword.value = !showPassword.value;
};

const onClickSubmitLogin = async () => {
  isLoginLoading.value = true;
  await auth.sendLoginData(loginInputData)
      .then(() => {
        emit('login-success');
        isLoginLoading.value = false;
      })
      .catch((resStatus) => {
        console.log(resStatus);
        isLoginLoading.value = false;
      });
};
</script>

<style scoped>
:deep(.v-input) {
  color: #255ED6 !important;
}
</style>