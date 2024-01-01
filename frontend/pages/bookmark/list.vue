<script>
import { bookmarkFn } from '~/composables/bookmarkFn'

export default {
  setup(props, context) {
    const {
      bookmarks,
      getBookmarks,
      unBookmarks,
      getUnBookmarks,
      addBookmark,
      deleteBookmark,
      editBookmark
    } = bookmarkFn();

    onMounted(() => {
      getBookmarks();
      getUnBookmarks();
    })

    return { bookmarks, unBookmarks, addBookmark, deleteBookmark, editBookmark }
  }
}
</script>

<template>
  <v-card elevation="10">
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
            <v-btn v-if="bookmark.issuedFlag == 'N'" class="mr-2 bg-primary" @click="editBookmark(bookmark.bookmarkId)">배정</v-btn>
          </td>
        </tr>
        </tbody>
      </v-table>
    </v-card-item>
  </v-card>
  <v-card elevation="10" class="mt-5">
    <v-card-item class="pa-6">
      <v-card-title class="text-h5 pt-sm-2 pb-7">공모주 리스트</v-card-title>
      <v-table class="month-table">
        <thead>
        <tr>
          <th class="text-subtitle-1 font-weight-bold">공모주명</th>
          <th class="text-subtitle-1 font-weight-bold">공모일</th>
          <th class="text-subtitle-1 font-weight-bold">상장일</th>
          <th class="text-subtitle-1"></th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="unBookmark in unBookmarks" :key="unBookmark.stockId" class="month-item">
          <td>{{ unBookmark.stockName }}</td>
          <td>{{ unBookmark.subscriptDate }}</td>
          <td>{{ unBookmark.listingDate }}</td>
          <td>
            <v-btn size="40" icon class="bg-primary d-flex" @click="addBookmark(unBookmark.stockId)">
              <v-avatar size="30" class="text-white">
                <BookmarkIcon size="15" />
              </v-avatar>
              <v-tooltip activator="parent" location="bottom">Bookmark</v-tooltip>
            </v-btn>
          </td>
        </tr>
        </tbody>
      </v-table>
    </v-card-item>
  </v-card>
</template>
