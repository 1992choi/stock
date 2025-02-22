# Stock

## 1. DB
- MySQL 설정
  - 이미지 다운로드
    - docker pull mysql:8.0.38
  - 이미지 조회
    - docker images
  - 실행
    - docker run --name stock-mysql -e MYSQL_ROOT_PASSWORD=root -d -p 3306:3306 mysql:8.0.38
  - 컨테이너 접속
    - docker exec -it stock-mysql bash
  - 데이터베이스 접속
    - mysql -u root -p
      - 위 명령어 입력한 이후 패스워드로 'root' 입력


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
- nohup java -Duser.timezone="Asia/Seoul" -jar stock-0.0.1-SNAPSHOT.jar & > /dev/null