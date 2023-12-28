<script setup lang="ts">
import {ref} from "@vue/reactivity";
import axios from "axios";
import { useAuthStore } from '~/stores/auth'

const store = useAuthStore();
const { user } = store;
const stockName = ref('')
const subscriptDate = ref('')
const listingDate = ref('')
const router = useRouter();

async function regist() {
  await axios.post('http://localhost:8080/api/stocks', {
        stockName: stockName.value,
        subscriptDate: subscriptDate.value,
        listingDate: listingDate.value
      },
      {
        headers: {
          Authorization: `Bearer ${user.token}`
        }
      }).then((response) => {
    if (response.data.status == 'success') {
      alert("추가 성공");
      router.push('/stock/list');
    } else {
      alert(response.data.message);
    }
  }).catch((error) => {
    console.log(error.response)
    alert("추가 실패");
  });
}
</script>

<template>
  <v-card elevation="10" class="">
    <v-card-item class="pa-6">
      <v-card-title class="text-h5 pt-sm-2 pb-7">공모주 등록</v-card-title>
      <form>
        <v-row class="d-flex mb-3">
          <v-col cols="12">
            <v-label class="font-weight-bold mb-1">공모주</v-label>
            <v-text-field variant="outlined" hide-details color="primary" v-model="stockName"></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-label class="font-weight-bold mb-1">공모일</v-label>
            <v-text-field variant="outlined" type="text" hide-details color="primary" v-model="subscriptDate"></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-label class="font-weight-bold mb-1">상장일</v-label>
            <v-text-field variant="outlined" type="text" hide-details color="primary" v-model="listingDate"></v-text-field>
          </v-col>
          <v-col cols="12 text-right">
            <v-btn class="mr-2 bg-primary" @click="regist()">추가</v-btn>
          </v-col>
        </v-row>
      </form>
    </v-card-item>
  </v-card>
</template>