<script>
import axios from "axios";
import { useAuthStore } from '~/stores/auth'

const store = useAuthStore();
const { user } = store;
const router = useRouter();

export default {
  data() {
    return {
      stock: {}
    }
  },
  methods: {
    async getStock() {
      const stockId = this.$route.query.stockId;
      const response = await axios.get('http://localhost:8080/api/stocks/' + stockId,
          {
            headers: {
              Authorization: `Bearer ${user.token}`,
            }
          }
      );
      this.stock = response.data.data;
    },
    async edit() {
      const stockId = this.$route.query.stockId;
      await axios.put('http://localhost:8080/api/stocks/' + stockId, {
            stockName: this.stock.stockName,
            subscriptDate: this.stock.subscriptDate,
            listingDate: this.stock.listingDate
          },
          {
            headers: {
              Authorization: `Bearer ${user.token}`
            }
          }).then((response) => {
        if (response.status === 200) {
          if (response.data.status == 'success') {
            alert("수정 성공");
            router.push('/stock/list');
          } else {
            alert(response.data.message);
          }
        }
      }).catch((error) => {
        console.log(error.response)
        alert("수정 실패");
      });
    },
    async remove() {
      const stockId = this.$route.query.stockId;
      await axios.delete('http://localhost:8080/api/stocks/' + stockId,
          {
            headers: {
              Authorization: `Bearer ${user.token}`
            }
          })
          .then((response) => {
            if (response.status === 200) {
              if (response.data.status == 'success') {
                alert("삭제 성공");
                router.push('/stock/list');
              } else {
                alert(response.data.message);
              }
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

<template>
  <v-card elevation="10" class="">
    <v-card-item class="pa-6">
      <v-card-title class="text-h5 pt-sm-2 pb-7">공모주 상세</v-card-title>
      <form>
        <v-row class="d-flex mb-3">
          <v-col cols="12">
            <v-label class="font-weight-bold mb-1">공모주</v-label>
            <v-text-field variant="outlined" hide-details color="primary" v-model="stock.stockName"></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-label class="font-weight-bold mb-1">공모일</v-label>
            <v-text-field variant="outlined" type="text" hide-details color="primary" v-model="stock.subscriptDate"></v-text-field>
          </v-col>
          <v-col cols="12">
            <v-label class="font-weight-bold mb-1">상장일</v-label>
            <v-text-field variant="outlined" type="text" hide-details color="primary" v-model="stock.listingDate"></v-text-field>
          </v-col>
          <v-col cols="12 text-right">
            <v-btn class="mr-2 bg-primary" @click="edit()">수정</v-btn>
            <v-btn class="mr-2 bg-warning" @click="remove()">삭제</v-btn>
          </v-col>
        </v-row>
      </form>
    </v-card-item>
  </v-card>
</template>