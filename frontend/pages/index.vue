<template>
  <div>
    <h2>{{ title }}</h2>
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
