// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
    devtools: {enabled: true},
    modules: [
        '@pinia/nuxt',
        '@pinia-plugin-persistedstate/nuxt',
    ],
    imports: {
        dirs: ['./stores']
    },
    ssr: false,
    typescript: {
        shim: false
    },
    build: {
        transpile: ["vuetify"],
    },
    vite: {
        define: {
            "process.env.DEBUG": false,
        },
    },
    nitro: {
        serveStatic: true,
    },
    devServerHandlers: [],
    hooks: {
    },
})
