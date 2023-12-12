// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  devtools: { enabled: true },
  css: [
      `../assets/vendor/fonts/boxicons.css`,
      `../assets/vendor/css/core.css`,
      `../assets/vendor/css/theme-default.css`,
      `../assets/css/demo.css`,
      `../assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css`,
      `../assets/vendor/libs/apex-charts/apex-charts.css`
  ],
})
