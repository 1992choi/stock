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

<template>
  <v-card elevation="10" class="">
    <v-card-item class="pa-6">
      <v-card-title class="text-h5 pt-sm-2 pb-7">공모주 리스트</v-card-title>
      <v-table class="month-table">
        <thead>
        <tr>
          <th class="text-subtitle-1 font-weight-bold">공모주 #1</th>
          <th class="text-subtitle-1 font-weight-bold">공모주 #2</th>
          <th class="text-subtitle-1 font-weight-bold">공모일</th>
          <th class="text-subtitle-1 font-weight-bold">상장일</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="stock in stocks" :key="stock.stockId" class="month-item">
          <td>
            <nuxt-link :to="{path: '/stock/detail', query:{stockId:stock.stockId}}">{{ stock.stockName }}</nuxt-link>
          </td>
          <td @click="moveToDetailPage(stock.stockId)">{{ stock.stockName }}</td>
          <td>{{ stock.subscriptDate }}</td>
          <td>{{ stock.listingDate }}</td>
        </tr>
        </tbody>
      </v-table>
      <div class="text-right">
        <v-btn class="mr-2 bg-primary" @click="moveToRegistPage()">추가</v-btn>
      </div>
    </v-card-item>
  </v-card>
</template>
