<template>
  <div>
    <h2>공모주 목록</h2>
    <table>
      <tr>
        <th>공모주 #1</th>
        <th>공모주 #2</th>
        <th>공모일</th>
        <th>상장일</th>
      </tr>
      <tr v-for="stock in stocks" :key="stock.stockId">
        <td><nuxt-link :to="{path: '/stock/detail', query:{stockId:stock.stockId}}">{{ stock.stockName }}</nuxt-link></td>
        <td @click="moveToDetailPage(stock.stockId)">{{ stock.stockName }}</td>
        <td>{{ stock.subscriptDate }}</td>
        <td>{{ stock.listingDate }}</td>
      </tr>
    </table>
    <div>
      <button @click="moveToRegistPage()">공모주 추가</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      stocks: {},
    }
  },
  methods: {
    async getStocks() {
      const response = await axios.get('http://localhost:8080/api/stocks');
      this.stocks = response.data;
    },
    moveToDetailPage(id) {
      this.$router.push(`${id}`)
    },
    moveToRegistPage() {
      this.$router.push(`regist`)
    }
  },
  created() {
    this.getStocks();
  },
}
</script>
