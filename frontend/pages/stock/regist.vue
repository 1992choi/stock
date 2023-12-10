<template>
  <div>
    <h2>공모주 추가</h2>
    <table>
      <tr>
        <th>공모주명</th>
        <td>
          <input type="text" v-model="stockName"/>
        </td>
      </tr>
      <tr>
        <th>공모일</th>
        <td>
          <input type="text" v-model="subscriptDate"/>
        </td>
      </tr>
      <tr>
        <th>상장일</th>
        <td>
          <input type="text" v-model="listingDate"/>
        </td>
      </tr>
    </table>
    <div>
      <button @click="regist()">공모주 추가</button>
    </div>
  </div>
</template>

<script setup lang="ts">
import {ref} from "@vue/reactivity";
import axios from "axios";

const stockName = ref('')
const subscriptDate = ref('')
const listingDate = ref('')

async function regist() {
  await axios.post('http://localhost:8080/api/stocks', {
    stockName: stockName.value,
    subscriptDate: subscriptDate.value,
    listingDate: listingDate.value
  }).then((response) => {
    if (response.data.status == 'success') {
      alert("추가 성공");
      location.href = 'list'
    } else {
      alert(response.data.message);
    }
  }).catch((error) => {
    console.log(error.response)
    alert("추가 실패");
  });
}
</script>