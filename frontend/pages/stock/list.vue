<template>
  <div class="container-xxl flex-grow-1 container-p-y">
    <h4 class="fw-bold py-3 mb-4">공모주 목록</h4>
    <div class="card">
      <div class="table-responsive text-nowrap">
        <table class="table">
          <thead>
          <tr>
            <th>공모주 #1</th>
            <th>공모주 #2</th>
            <th>공모일</th>
            <th>상장일</th>
          </tr>
          </thead>
          <tbody class="table-border-bottom-0">
          <tr v-for="stock in stocks" :key="stock.stockId">
            <td>
              <nuxt-link :to="{path: '/stock/detail', query:{stockId:stock.stockId}}">{{ stock.stockName }}</nuxt-link>
            </td>
            <td @click="moveToDetailPage(stock.stockId)">{{ stock.stockName }}</td>
            <td>{{ stock.subscriptDate }}</td>
            <td>{{ stock.listingDate }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="row justify-content-end text-end pt-5">
      <div class="col-sm-10">
        <button type="button" class="btn btn-primary" @click="moveToRegistPage()">추가</button>
      </div>
    </div>
  </div>
</template>

<script>
import { stockFn } from '~/composables/stockFn'

export default {
  setup(props, context) {
    const {
      stocks,
      getStocks,
      moveToDetailPage,
      moveToRegistPage
    } = stockFn();

    onMounted(() => {
      getStocks();
    })

    return { stocks, moveToDetailPage, moveToRegistPage }
  }
}
</script>