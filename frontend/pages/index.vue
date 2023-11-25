<template>
  <div>
    <NuxtLogo/>
    <p>{{ msg }}</p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      msg: {},
    }
  },
  methods: {
    async getMsg() {
      const response = await axios.get('http://localhost:8080/api/test/hello');
      console.log(response.data)
      this.msg = response.data;
    }
  },
  created() {
    this.getMsg();
  },
}
</script>
