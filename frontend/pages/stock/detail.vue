<template>
  <div>
    <h2>공모주 상세</h2>
    <table>
      <tr>
        <th>공모주</th>
        <td>{{ stock.stockName }}</td>
      </tr>
      <tr>
        <th>공모일</th>
        <td>{{ stock.subscriptDate }}</td>
      </tr>
      <tr>
        <th>상장일</th>
        <td>{{ stock.listingDate }}</td>
      </tr>
    </table>
    <div>
      <button @click="remove()">삭제</button>
    </div>
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
      const stockId = this.$route.query.stockId;
      const response = await axios.get('http://localhost:8080/api/stocks/' + stockId);
      this.stock = response.data;
    },

    async remove() {
      const stockId = this.$route.query.stockId;
      await axios.delete('http://localhost:8080/api/stocks/' + stockId)
          .then((response) => {
            if (response.status === 200) {
              alert("삭제 성공");
              location.href = 'list'
            }
          }).catch((error) => {
            console.log(error.response)
            alert("삭제 실패");
          });
    }
  },
  created() {
    this.getStock();
  },
}
</script>