<script>
import { bookmarkFn } from '~/composables/bookmarkFn'

export default {
  setup(props, context) {
    const {
      bookmarks,
      getBookmarks,
      deleteBookmark
    } = bookmarkFn();

    onMounted(() => {
      getBookmarks();
    })

    return { bookmarks, deleteBookmark }
  }
}
</script>

<template>
  <v-card elevation="10" class="">
    <v-card-item class="pa-6">
      <v-card-title class="text-h5 pt-sm-2 pb-7">관심 리스트</v-card-title>
      <v-table class="month-table">
        <thead>
        <tr>
          <th class="text-subtitle-1 font-weight-bold">공모주명</th>
          <th class="text-subtitle-1 font-weight-bold">공모일</th>
          <th class="text-subtitle-1 font-weight-bold">상장일</th>
          <th class="text-subtitle-1 font-weight-bold">배정결과</th>
          <th class="text-subtitle-1"></th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="bookmark in bookmarks" :key="bookmark.bookmarkId" class="month-item">
          <td>{{ bookmark.stock.stockName }}</td>
          <td>{{ bookmark.stock.subscriptDate }}</td>
          <td>{{ bookmark.stock.listingDate }}</td>
          <td>{{ bookmark.issuedFlag }}</td>
          <td>
            <v-btn class="mr-2 bg-warning" @click="deleteBookmark(bookmark.bookmarkId)">삭제</v-btn>
          </td>
        </tr>
        </tbody>
      </v-table>
    </v-card-item>
  </v-card>
</template>
