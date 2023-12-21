<template>
  <div class="main-background h-90 w-100">
    <div class="white-background ma-11  pl-6 bg-white rounded-xl">
      <div class="controls-row d-flex">
        <div class="table-header font-weight-bold mt-8 ml-2 text-h4" style="letter-spacing: 1.28px;">Расписание</div>
      </div>
      <ScheduleTable/>
    </div>

  </div>
</template>

<script
  setup
  lang="ts"
>

import ScheduleTable from '@/components/schedule/ScheduleTable.vue';
</script>

<style scoped>
.main-background {
  background-color: #F2F5F9;
}
</style>