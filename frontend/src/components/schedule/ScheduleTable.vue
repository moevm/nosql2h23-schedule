<template>
  <v-data-table-virtual
    :headers="headers"
    :items="items"
    height="1040"
    fixed-header
  >
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
                        <v-list-item-title>{{ item[column.key].subjectTitle  }}</v-list-item-title>
                        <v-list-item-subtitle>{{ item[column.key].classroom }}</v-list-item-subtitle>
                        <v-list-item-subtitle>{{ item[column.key].teacher || '-'}}</v-list-item-subtitle>
            </v-list-item>
          </template>

          <template v-if="column.key === 'time'">
            <v-chip color="#658DE2" class="d-flex justify-center py-2">
              {{ item[column.key] || '-' }}
            </v-chip>
          </template>

          <template v-if="column.key === 'weekDay'">
            <v-list-item
                class="text-center"
                :title="getWeekDayTitle(item[column.key]) || '-'"
                color="#7498E4"
            />
          </template>
        </td>
      </tr>
    </template>
  </v-data-table-virtual>
</template>

<script setup>
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

const headers = [
  {
    title: "День недели",
    key: "weekDay",
    sortable: false,
    width: 100,
    align: 'center',
  },
  {
    title: "Время пары",
    key: "time",
    sortable: false,
    width: 130,
    align: 'center',
  },
  {
    title: "0382",
    key: "0382",
    sortable: false,
    width: 180,
    align: 'center',
  },
  {
    title: "0381",
    key: "0381",
    sortable: false,
    width: 180,
    align: 'center',
  },
  {
    title: "0383",
    key: "0383",
    sortable: false,
    width: 180,
    align: 'center',
  },
];
const items = [
  {
    "weekDay": "MON",
    "0383": {
      "id": "6580f1c78c382b111c30cc34",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc32",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "15:30-17:00",
    "0382": {
      "id": "6580f1c78c382b111c30cc33",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "MON",
    "0383": {
      "id": "6580f1c78c382b111c30cc2b",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc29",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "9:50-11:20",
    "0382": {
      "id": "6580f1c78c382b111c30cc2a",
      "classroom": "1235",
      "teacher": null,
      "subjectTitle": "Криптография"
    }
  },
  {
    "weekDay": "MON",
    "0383": {
      "id": "6580f1c78c382b111c30cc2e",
      "classroom": "1232",
      "teacher": null,
      "subjectTitle": "ОПНП"
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc2c",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "11:40-13:10",
    "0382": {
      "id": "6580f1c78c382b111c30cc2d",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "MON",
    "0383": {
      "id": "6580f1c78c382b111c30cc31",
      "classroom": "5328",
      "teacher": null,
      "subjectTitle": "NoSQL"
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc2f",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "13:40-15:10",
    "0382": {
      "id": "6580f1c78c382b111c30cc30",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "MON",
    "0383": {
      "id": "6580f1c78c382b111c30cc34",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc32",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "15:30-17:00",
    "0382": {
      "id": "6580f1c78c382b111c30cc33",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "MON",
    "0383": {
      "id": "6580f1c78c382b111c30cc37",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc35",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "17:20-18:50",
    "0382": {
      "id": "6580f1c78c382b111c30cc36",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "TUE",
    "0383": {
      "id": "6580f1c78c382b111c30cc46",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc44",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "15:30-17:00",
    "0382": {
      "id": "6580f1c78c382b111c30cc45",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "TUE",
    "0383": {
      "id": "6580f1c78c382b111c30cc3d",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc3b",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "9:50-11:20",
    "0382": {
      "id": "6580f1c78c382b111c30cc3c",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "TUE",
    "0383": {
      "id": "6580f1c78c382b111c30cc40",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc3e",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "11:40-13:10",
    "0382": {
      "id": "6580f1c78c382b111c30cc3f",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "TUE",
    "0383": {
      "id": "6580f1c78c382b111c30cc43",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc41",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "13:40-15:10",
    "0382": {
      "id": "6580f1c78c382b111c30cc42",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "TUE",
    "0383": {
      "id": "6580f1c78c382b111c30cc46",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc44",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "15:30-17:00",
    "0382": {
      "id": "6580f1c78c382b111c30cc45",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "TUE",
    "0383": {
      "id": "6580f1c78c382b111c30cc49",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc47",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "17:20-18:50",
    "0382": {
      "id": "6580f1c78c382b111c30cc48",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "WED",
    "0383": {
      "id": "6580f1c78c382b111c30cc58",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc56",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "15:30-17:00",
    "0382": {
      "id": "6580f1c78c382b111c30cc57",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "WED",
    "0383": {
      "id": "6580f1c78c382b111c30cc4f",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc4d",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "9:50-11:20",
    "0382": {
      "id": "6580f1c78c382b111c30cc4e",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "WED",
    "0383": {
      "id": "6580f1c78c382b111c30cc52",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc50",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "11:40-13:10",
    "0382": {
      "id": "6580f1c78c382b111c30cc51",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "WED",
    "0383": {
      "id": "6580f1c78c382b111c30cc55",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc53",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "13:40-15:10",
    "0382": {
      "id": "6580f1c78c382b111c30cc54",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "WED",
    "0383": {
      "id": "6580f1c78c382b111c30cc58",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc56",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "15:30-17:00",
    "0382": {
      "id": "6580f1c78c382b111c30cc57",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "WED",
    "0383": {
      "id": "6580f1c78c382b111c30cc5b",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc59",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "17:20-18:50",
    "0382": {
      "id": "6580f1c78c382b111c30cc5a",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "THU",
    "0383": {
      "id": "6580f1c78c382b111c30cc6a",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc68",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "15:30-17:00",
    "0382": {
      "id": "6580f1c78c382b111c30cc69",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "THU",
    "0383": {
      "id": "6580f1c78c382b111c30cc61",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc5f",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "9:50-11:20",
    "0382": {
      "id": "6580f1c78c382b111c30cc60",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "THU",
    "0383": {
      "id": "6580f1c78c382b111c30cc64",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc62",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "11:40-13:10",
    "0382": {
      "id": "6580f1c78c382b111c30cc63",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "THU",
    "0383": {
      "id": "6580f1c78c382b111c30cc67",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc65",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "13:40-15:10",
    "0382": {
      "id": "6580f1c78c382b111c30cc66",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "THU",
    "0383": {
      "id": "6580f1c78c382b111c30cc6a",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc68",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "15:30-17:00",
    "0382": {
      "id": "6580f1c78c382b111c30cc69",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "THU",
    "0383": {
      "id": "6580f1c78c382b111c30cc6d",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc6b",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "17:20-18:50",
    "0382": {
      "id": "6580f1c78c382b111c30cc6c",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "FRI",
    "0383": {
      "id": "6580f1c78c382b111c30cc7c",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc7a",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "15:30-17:00",
    "0382": {
      "id": "6580f1c78c382b111c30cc7b",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "FRI",
    "0383": {
      "id": "6580f1c78c382b111c30cc73",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc71",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "9:50-11:20",
    "0382": {
      "id": "6580f1c78c382b111c30cc72",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "FRI",
    "0383": {
      "id": "6580f1c78c382b111c30cc76",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc74",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "11:40-13:10",
    "0382": {
      "id": "6580f1c78c382b111c30cc75",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "FRI",
    "0383": {
      "id": "6580f1c78c382b111c30cc79",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc77",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "13:40-15:10",
    "0382": {
      "id": "6580f1c78c382b111c30cc78",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "FRI",
    "0383": {
      "id": "6580f1c78c382b111c30cc7c",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc7a",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "15:30-17:00",
    "0382": {
      "id": "6580f1c78c382b111c30cc7b",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "FRI",
    "0383": {
      "id": "6580f1c78c382b111c30cc7f",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc7d",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "17:20-18:50",
    "0382": {
      "id": "6580f1c78c382b111c30cc7e",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "SAT",
    "0383": {
      "id": "6580f1c78c382b111c30cc8e",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc8c",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "15:30-17:00",
    "0382": {
      "id": "6580f1c78c382b111c30cc8d",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "SAT",
    "0383": {
      "id": "6580f1c78c382b111c30cc85",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc83",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "9:50-11:20",
    "0382": {
      "id": "6580f1c78c382b111c30cc84",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "SAT",
    "0383": {
      "id": "6580f1c78c382b111c30cc88",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc86",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "11:40-13:10",
    "0382": {
      "id": "6580f1c78c382b111c30cc87",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "SAT",
    "0383": {
      "id": "6580f1c78c382b111c30cc8b",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc89",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "13:40-15:10",
    "0382": {
      "id": "6580f1c78c382b111c30cc8a",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "SAT",
    "0383": {
      "id": "6580f1c78c382b111c30cc8e",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc8c",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "15:30-17:00",
    "0382": {
      "id": "6580f1c78c382b111c30cc8d",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  },
  {
    "weekDay": "SAT",
    "0383": {
      "id": "6580f1c78c382b111c30cc91",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "0381": {
      "id": "6580f1c78c382b111c30cc8f",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    },
    "time": "17:20-18:50",
    "0382": {
      "id": "6580f1c78c382b111c30cc90",
      "classroom": null,
      "teacher": null,
      "subjectTitle": null
    }
  }
];
</script>

<style scoped>

</style>