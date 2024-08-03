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

## 3. Redocly
### 3.1. 설치
- https://github.com/Redocly/openapi-starter
### 3.2. swagger.json to yaml
- https://redocly.com/tools/json-to-json-schema/

## 4. AWS
### 4.1. AWS로 JAR 업로드
- scp -i [pem file] [upload file] [user id]@[ec2 public IP]:~/[transfer address]
### 4.2. JAR 시작
- nohup java -jar stock-0.0.1-SNAPSHOT.jar & > /dev/null