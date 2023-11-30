<template>
  <div>
    <h2><NuxtLogo/> {{ title }}</h2>
    <ul>
      <li><nuxt-link to="/stock/list">공모주 목록</nuxt-link></li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      title: {},
    }
  },
  methods: {
    async getTitle() {
      const response = await axios.get('http://localhost:8080/api/test/hello');
      this.title = response.data;
    }
  },
  created() {
    this.getTitle();
  },
}
</script>
