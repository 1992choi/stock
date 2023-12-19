# Stock

## 1. Nuxt
### 1.1. Nuxt 설치
- ``$ npx nuxi@latest init <project-name>``
### 1.2. Nuxt 실행
- ``$ cd <project-name>``
- ``$ npm run dev``
- http://localhost:3000/
### 1.3. Axios 설치
- ``$ npm i @nuxtjs/axios``
### 1.4. CORS 대응
- ``$ npm i @nuxtjs/proxy``
- nuxt.config.js에 추가
  ```
  modules: [
    '@nuxtjs/axios',
    '@nuxtjs/proxy'
  ],

  axios: {
    proxy: true
  },

  proxy: {
    '/': {
      target: 'http://localhost:8080/',
      changeOrigin: true // cross origin 허용
    }
  },
  ``` 
### 1.5. Bootstrap 이슈
- ``$ npm install -D sass``

### 1.6. Pinia 설치
- ``$ npm install pinia @pinia/nuxt``
- ``$ npm i -D @pinia-plugin-persistedstate/nuxt``

## 2. Swagger
### 2.1. URL
- http://localhost:8080/swagger-ui/index.html
