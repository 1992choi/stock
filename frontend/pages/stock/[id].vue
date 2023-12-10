<template>
  <div>
    <h2>공모주 상세2</h2>
    <table>
      <tr>
        <th>공모주</th>
        <td>{{stock.stockName}}</td>
      </tr>
      <tr>
        <th>공모일</th>
        <td>{{stock.subscriptDate}}</td>
      </tr>
      <tr>
        <th>상장일</th>
        <td>{{stock.listingDate}}</td>
      </tr>
    </table>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      stock: {},
    }
  },
  methods: {
    async getStock() {
      const stockId = this.$route.params.id
      const response = await axios.get('http://localhost:8080/api/stocks/' + stockId)
      this.stock = response.data.data
    }
  },
  created() {
    this.getStock()
  },
}
</script>
