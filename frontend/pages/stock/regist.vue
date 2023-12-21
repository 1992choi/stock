<template>
  <div class="container-xxl flex-grow-1 container-p-y">
    <h4 class="fw-bold py-3 mb-4">공모주 추가</h4>

    <!-- Basic Bootstrap Table -->
    <div class="card-body">
      <form>
        <div class="row mb-3">
          <label class="col-sm-2 col-form-label" for="basic-default-name">공모주</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" v-model="stockName"/>
          </div>
        </div>
        <div class="row mb-3">
          <label class="col-sm-2 col-form-label" for="basic-default-name">공모일</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" v-model="subscriptDate"/>
          </div>
        </div>
        <div class="row mb-3">
          <label class="col-sm-2 col-form-label" for="basic-default-name">상장일</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" v-model="listingDate"/>
          </div>
        </div>
        <div class="row justify-content-end text-end">
          <div class="col-sm-10">
            <button type="button" class="btn btn-primary" @click="regist()">공모주 추가</button>
          </div>
        </div>
      </form>
    </div>
    <!--/ Basic Bootstrap Table -->
  </div>
</template>

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