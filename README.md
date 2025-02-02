# 게시판 커뮤니티 API 개발

## 기능
- 인증
  - 회원가입
  - 로그인
- 게시판
  - 등록
  - 수정
  - 상세 조회
  - 목록 조회
- 답글
  - 게시판 답글 등록
- 파일
  - 파일 업로드

## 사용기술
- Java 17, Spring boot 3
- Spring security
- Spring data JPA, MySQL 8
- Spring validation 
- Swagger (Open API 3)
- Java JWT (JWT 라이브러리)
- Spring-dotenv
- Docker compose


## 시작하기

### 1. 프로젝트 클론
```
git clone https://github.com/hyenny/community.git
```

### 2. Docker 실행
```
cd community
docker-compose up -d
```

### 3. 애플리케이션 실행
```
./gradlew build
```

```
cd build/libs
java -jar community-0.0.1-SNAPSHOT.jar
```



#### [참고] JWT 관련 세팅

JWT 관련 값을 세팅하지 않으면 서버가 동작하지 않으므로, 반드시 설정이 필요합니다. 

```yaml
jwt:
  secret: ${JWT_SECRET}
  token-validity-in-seconds: ${JWT_TOKEN_VALIDITY_IN_SECONDS}
```
- `JWT_SECRET` : JWT Secret Key 만드는데 사용될 secret 값
- `JWT_TOKEN_VALIDITY_IN_SECONDS` : JWT 토큰 만료 시간(초)


- 예시
```yaml
jwt:
  secret: c2lsdmVybmluZS10ZWNoLXNwcmluZy1ib290LWp3dC10dXRvcmlhbC1zZWNyZXQtc2lsdmVybmluZS10ZWNoLXNwcmluZy1ib290LWp3dC10dXRvcmlhbC1zZWNyZXQK
  token-validity-in-seconds: 600
```


#### [참고] 로컬 파일 시스템 경로
업로드한 파일은 아래 경로에 업로드 됩니다.

```yaml
file:
  upload-dir: /tmp/upload/
```


### 4. API 호출
- [상세 가이드 참고](src%2Fdocs%2Fapi-guide.md)


## API 명세
> http://localhost:8080/swagger-ui/index.html



## 부록
- [프로젝트 설명](src%2Fdocs%2Fdescription.md)