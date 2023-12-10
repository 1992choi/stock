<template>
  <div>
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
  }
</style>
