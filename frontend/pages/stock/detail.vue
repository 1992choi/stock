<template>
  <div class="container-xxl flex-grow-1 container-p-y">
    <h4 class="fw-bold py-3 mb-4">공모주 상세</h4>

    <!-- Basic Bootstrap Table -->
    <div class="card-body">
      <form>
        <div class="row mb-3">
          <label class="col-sm-2 col-form-label" for="basic-default-name">공모주</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" v-model="stock.stockName"/>
          </div>
        </div>
        <div class="row mb-3">
          <label class="col-sm-2 col-form-label" for="basic-default-name">공모일</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" v-model="stock.subscriptDate"/>
          </div>
        </div>
        <div class="row mb-3">
          <label class="col-sm-2 col-form-label" for="basic-default-name">상장일</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" v-model="stock.listingDate"/>
          </div>
        </div>
        <div class="row justify-content-end text-end">
          <div class="col-sm-10">
            <button type="button" class="btn btn-primary" @click="edit()">수정</button>
            <button type="button" class="btn btn-danger" @click="remove()">삭제</button>
          </div>
        </div>
      </form>
    </div>
    <!--/ Basic Bootstrap Table -->
  </div>
</template>

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