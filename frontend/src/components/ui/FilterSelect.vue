<template>
  <v-select
      class="mr-2"
      :loading="loading"
      color="#658de2"
      variant="outlined"
      :label="label"
      v-model="value['value']"
      :style="`max-width: ${maxWidth}px`"
      :items="items"
      :props="$attrs"
      hide-details
      base-color="#658de2"
      :menu-props="{
       closeOnClick: true,
       closeOnContentClick: true,
      }"
  >
    <template #prepend-item>
      <v-list-item
          v-if="hasEmptyOption"
          @click="value['value']='Не выбрано'; value['title']=null;"
      >
        Не выбрано
      </v-list-item>
    </template>
    <template #item="{item, index, props}">
      <v-list-item @click="value['value']=item.value; value['title']=item.title;" v-bind="props"/>
    </template>
    <template #selection>{{ value['value'] || 'Не выбрано' }}</template>
    <template #no-data>
      Не выбрано</template>
  </v-select>
</template>

<script
    lang="ts"
  setup
>
interface Props {
  loading: boolean,
  items: object[],
  label: string,
  value: object,
  maxWidth: number,
  hasEmptyOption: boolean,
}

const props = defineProps<Props>();
</script>

<style scoped>
:deep(.v-field) {
  color: #255ED6 !important;
}
</style>