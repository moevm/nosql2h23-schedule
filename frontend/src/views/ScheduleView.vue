<template>
  <div class="main-background w-100">
    <div class="white-background  pl-6 bg-white rounded-xl">
      <div class="controls-row d-flex align-center pt-12">
        <div
            class="table-header font-weight-bold ml-2 text-h4 mr-6"
            style="letter-spacing: 1.28px;"
        > Расписание</div>

        <FilterSelect
          :loading="filterParamsLoading"
          :items="courses"
          label="Курс"
          :value="courseParam"
          :max-width="100"
          :has-empty-option="false"
        ></FilterSelect>
        <FilterSelect
            :loading="filterParamsLoading"
            :items="faculties"
            label="Факультет"
            :value="facultyParam"
            :max-width="200"
            :has-empty-option="false"
        ></FilterSelect>
        <FilterSelect
            :loading="filterParamsLoading"
            :items="teachers"
            label="Преподаватель"
            :value="teacherParam"
            :max-width="300"
            :has-empty-option="true"
        ></FilterSelect>

      </div>

      <div class="schedule-table pr-5 pb-4" style="min-height: 1050px;">
        <ScheduleTable
            @filterParamsLoaded="onFilterParamsLoaded"
            :course="courseParam.title"
            :faculty="facultyParam.title"
            :teacher="teacherParam.title"
        />
      </div>

    </div>

  </div>
</template>

<script setup>
import ScheduleTable from '@/components/schedule/ScheduleTable.vue';
import { onMounted, ref } from 'vue';
import FilterSelect from '@/components/ui/FilterSelect.vue';

const filterParamsLoading = ref(false);
const courseParam = ref({
  title: '4',
  value: 4,
});
const teachers = ref([]);
const facultyParam = ref({
  title: 'ФКТИ',
  value: 'ФКТИ',
});
const teacherParam = ref({
  title: null,
  value: 'Не выбрано'
});

const courses = [{
    title: '1',
    value: 1,
  }, {
  title: '2',
  value: 2,
}, {
  title: '3',
  value: 3,
}, {
  title: '4',
  value: 4,
}];

const faculties = [{
  title: 'ФКТИ',
  value: 'ФКТИ',
}, {
  title: 'ФРТ',
  value: 'ФРТ',
}, {
  title: 'ФЭЛ',
  value: 'ФЭЛ',
}, {
  title: 'ФИБС',
  value: 'ФИБС',
}];

onMounted(() => {
  filterParamsLoading.value = true;
});

const onFilterParamsLoaded = (data) => {
  filterParamsLoading.value = false;
  const formattedArr = [];
  data.forEach((value, index) => {
    formattedArr.push({
      title: value,
      value: value,
    });
  });
  teachers.value = formattedArr;
}
</script>

<style scoped>
.main-background {
  background-color: #F2F5F9;
}


</style>