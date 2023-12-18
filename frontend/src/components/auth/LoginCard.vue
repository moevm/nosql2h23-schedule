<template>
  <v-card :loading="isLoginLoading">
    <v-card-title class="text-center">Авторизация</v-card-title>

    <v-card-text>
      <v-form
          ref="loginFormRef"
          data-testid="loginData-form"
          @submit.prevent="onClickSubmitLogin"
      >
        <v-text-field
            v-model="loginInputData.username"
            :rules="loginValidationRules.login"
            autocomplete="username"
            clearable
            data-testid="login"
            label="Логин"
            variant="underlined"
        />
        <v-text-field
            v-model="loginInputData.password"
            :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="loginValidationRules.password"
            :type="showPassword ? 'text' : 'password'"
            autocomplete="current-password"
            data-testid="password"
            label="Пароль"
            variant="underlined"
            @click:append="onClickToggleShowPassword"
        />

        <v-btn
            :disabled="
            !(
              loginInputData.isContainsValues() && !loginFormRef?.errors?.length
            )
          "
            :loading="isLoginLoading"
            block
            class="mt-4"
            color="themeColor"
            data-testid="submit-button"
            type="submit"
        >
          Войти
        </v-btn>
      </v-form>
    </v-card-text>
  </v-card>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue';
import type { VForm } from 'vuetify/components';
import loginValidationRules from '@/components/auth/model/loginValidation';
import LoginData from '@/components/auth/model/loginData';

const emit = defineEmits(['login-success']);

const showPassword = ref(false);
const loginFormRef = ref<VForm | null>(null);
const loginInputData = reactive(new LoginData());
const isLoginLoading = ref(false);
// let statusMsg = reactive({ message: '' });

const onClickToggleShowPassword = () => {
  showPassword.value = !showPassword.value;
};

const onClickSubmitLogin = async () => {
  emit('login-success');
};
</script>