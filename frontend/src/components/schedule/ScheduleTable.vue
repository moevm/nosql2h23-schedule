<template>
  <div class="table-with-data" v-if="!itemsLoading">
    <v-data-table-virtual
        :headers="headers"
        :items="items"
        height="1060"
        :loading="itemsLoading"
        fixed-header
    >
<!--      <template #headers="{ columns }">-->
<!--        <tr>-->
<!--          <td-->
<!--            v-for="column in columns"-->
<!--            :key="column.key"-->
<!--          >-->
<!--            <span class="d-flex text-center">{{ column.title }}</span>-->
<!--          </td>-->
<!--        </tr>-->
<!--      </template>-->
      <template #item="{ item, columns, index }">
        <tr>
          <td
              v-for="column in columns"
              :key="column.key"
              :width="column.width"
          >
            <template v-if="column.key !== 'weekDay' && column.key !== 'time'">
              <v-list-item
                  class="text-center"
              >
                <v-list-item-title class="font-italic font-weight-medium">{{ item[column.key].subjectTitle  }}</v-list-item-title>
                <v-list-item-subtitle>{{ item[column.key].classroom }}</v-list-item-subtitle>
                <v-list-item-subtitle class="font-italic">{{ item[column.key].teacher || '-'}}</v-list-item-subtitle>
              </v-list-item>
            </template>

            <template v-if="column.key === 'time'">
              <div class="table-cell rounded-lg text-center py-3" style="background-color: #EDEDFE;">
                {{ item[column.key] || '-' }}
              </div>
            </template>

            <template v-if="column.key === 'weekDay'">
              <v-list-item
                  class="text-center"
              >
                <v-list-item-title class="text-body-2 text-center">
                  {{getWeekDayTitle(item[column.key]) || '-'}}
                </v-list-item-title>
              </v-list-item>
            </template>
          </td>
        </tr>
      </template>
      <template #no-data>
        <div class="title font-italic">
          Расписание для данных параметров пока не составлено :)
        </div>
      </template>
    </v-data-table-virtual>
  </div>
  <div
      v-if="itemsLoading"
      class="d-flex w-100 align-center"
  >
    <v-progress-circular
        class="mx-auto"
        color="#658DE2"
        indeterminate
        size="large"
    />
  </div>

</template>

<script setup>
import axiosApiInstance from "@/components/auth/api/AxiosApiInstance";
import { onBeforeMount, onMounted, ref } from "vue";

const itemsLoading = ref(false);
const items = ref([]);
const groups = ref([]);

const weekDayMap = new Map([
  ['MON', 'Понедельник'],
  ['TUE', 'Вторник'],
  ['WED', 'Среда'],
  ['THU', 'Четверг'],
  ['FRI', 'Пятница'],
  ['SAT', 'Суббота'],
  ['SUN', 'Воскресенье'],
]);
const getWeekDayTitle = (weekDayKey) => {
  return weekDayMap.get(weekDayKey) || 'День недели';
};
onBeforeMount(async () => {
  itemsLoading.value = true;
  await fetchItems();
  groups.value.forEach(g => {
    headers.value.push({
      title: g,
      key: g,
      sortable: false,
      width: 180,
      align: 'center',
    },)
  })
})

const fetchItems = async () => {
  const body = { faculty: 'ФКТИ', course: 4 };
  await axiosApiInstance.post('/admin/getSchedule', body)
  //await axiosApiInstance.get('https://run.mocky.io/v3/21ca3e5d-740b-495e-925c-12cb4cfef578')
      .then((res) => {
        if (res.data.chains) {
          items.value = res.data.chains;
        }
        if (res.data.groups) {
          groups.value = res.data.groups;
        }
        itemsLoading.value = false;
      })
      .catch((resStatus) => {
        itemsLoading.value = false;
        console.log(resStatus);
      });
};

const headers = ref([
  {
    title: "День недели",
    key: "weekDay",
    sortable: true,
    width: 140,
    align: 'center',
  },
  {
    title: "Время пары",
    key: "time",
    sortable: true,
    width: 100,
    align: 'center',
  },
]);
</script>

<style scoped>
.table-with-data {
  max-width: 1800px;
  overflow: auto;
}
</style>