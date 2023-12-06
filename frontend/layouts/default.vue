<template>
  <div>
    <nav>
      <nuxt-link to="/">Home</nuxt-link> |
      <nuxt-link to="/stock/list">공모주 목록</nuxt-link>
    </nav>
    <main>
      <slot />
    </main>
    <footer class="footer">
      Copyright. CHOI all rights reserved.
    </footer>
  </div>
</template>
<script setup lang="ts">
</script>
<style scoped>
  .footer {
    position: absolute;
    right: 5px;
    bottom: 5px;
  }
</style>
