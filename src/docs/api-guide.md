# API 호출 상세 가이드

## 1. 회원가입
커뮤니티 서비스를 이용하기 위해서는 회원가입이 필요합니다.

```
curl -X 'POST' \
  'http://localhost:8080/api/auth/signup' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "hello",
  "email": "hello@test.com",
  "password": "hello1111",
  "nickname": "hello"
}'
```


## 2. 로그인
이메일과 패스워드를 입력하여 로그인 합니다.

- 요청
```
curl -X 'POST' \
  'http://localhost:8080/api/auth/login' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "email": "hello@test.com",
  "password": "hello1111"
}'
```

- 응답
```
{
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoZWxsb0B0ZXN0LmNvbSIsImF1dGgiOiJST0xFX1VTRVIiLCJpYXQiOjE3Mzg1MDI1MDIsImV4cCI6MTczODUwMjU2Mn0.9eRIRkftLYFOtXD-RLMxUpxvLcWY-fN2sXIZXsNRoMqcD08AObGTo6WvHg6qaZLT8ZVBfqVDAaN0viSy0_nOXw"
}
```

응답에 나오는 해당 토큰은 API 이용시 헤더에 넣어서 보내야 합니다.


## 3. 게시글 등록
- 게시글 등록은 USER 만 이용할 수 있습니다. (ADMIN은 운영관리자이기 때문에 등록 가능합니다)
    - 회원가입 하면 기본으로 USER 입니다.
- 제목, 내용, 첨부파일 목록을 입력할 수 있습니다.
    - [참고] 첨부파일은 파일 업로드 API 를 먼저 호출 후 id를 받아 입력해야 합니다.

### 1) 파일 업로드 API 예시
- 요청
```
curl -X 'POST' \
  'http://localhost:8080/api/upload-file' \
  -H 'accept: application/json' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoZWxsb0B0ZXN0LmNvbSIsImF1dGgiOiJST0xFX1VTRVIiLCJpYXQiOjE3Mzg1MDI1MDIsImV4cCI6MTczODUwMjU2Mn0.9eRIRkftLYFOtXD-RLMxUpxvLcWY-fN2sXIZXsNRoMqcD08AObGTo6WvHg6qaZLT8ZVBfqVDAaN0viSy0_nOXw' \
  -H 'Content-Type: multipart/form-data' \
  -F 'file=@스크린샷.png;type=image/png'

```

- 응답
```
{
  "id": "7f000001-94c6-115d-8194-c6d870720002" 
}
```

### 2) 게시글 등록 API 예시
- 요청
```
curl -X 'POST' \
  'http://localhost:8080/api/posts' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoZWxsb0B0ZXN0LmNvbSIsImF1dGgiOiJST0xFX1VTRVIiLCJpYXQiOjE3Mzg1MDI1MDIsImV4cCI6MTczODUwMjU2Mn0.9eRIRkftLYFOtXD-RLMxUpxvLcWY-fN2sXIZXsNRoMqcD08AObGTo6WvHg6qaZLT8ZVBfqVDAaN0viSy0_nOXw' \
  -H 'Content-Type: application/json' \
  -d '{
  "title": "게시글 등록 테스트",
  "content": "내용입니다",
  "attachmentIds": [
    "7f000001-94c6-115d-8194-c6d870720002"
  ]
}'
```

## 4. 게시글 답글 등록
- 게시글 등록은 ADMIN 만 이용할 수 있습니다.

### ADMIN 계정으로 로그인
```
curl -X 'POST' \
  'http://localhost:8080/api/auth/login' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "email": "admin1@test.com",
  "password": "1234"
}'

```

### 게시글 답글 API 예시

```
curl -X 'POST' \
  'http://localhost:8080/api/posts/7f000001-94c6-115d-8194-c6dc885f0003/replies' \
  -H 'accept: */*' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbjFAdGVzdC5jb20iLCJhdXRoIjoiUk9MRV9BRE1JTiIsImlhdCI6MTczODUwNDAzOSwiZXhwIjoxNzM4NTA0MDk5fQ.1uNdDRud4zhaiUw4JtsXruG41OKYGOBZVeYT_xgSAVy-0UErxUVNei85s29jKjnYk8nFTyLCE7aUpOM04p8pQw' \
  -H 'Content-Type: application/json' \
  -d '{
  "content": "답글입니다"
}'

```

## 5. 게시글 상세 조회

- 요청
```
curl -X 'GET' \
  'http://localhost:8080/api/posts/7f000001-94c6-115d-8194-c6dc885f0003' \
  -H 'accept: application/json' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoZWxsb0B0ZXN0LmNvbSIsImF1dGgiOiJST0xFX1VTRVIiLCJpYXQiOjE3Mzg1MDI1MDIsImV4cCI6MTczODUwMjU2Mn0.9eRIRkftLYFOtXD-RLMxUpxvLcWY-fN2sXIZXsNRoMqcD08AObGTo6WvHg6qaZLT8ZVBfqVDAaN0viSy0_nOXw'
```

- 응답
```
{
  "title": "게시글 등록 테스트",
  "content": "내용입니다",
  "authorName": "h*****",
  "createdAt": "2025-02-02T13:32:09.181Z",
  "updatedAt": "2025-02-02T13:42:09.086Z",
  "attachments": [],
  "replies": [
    {
      "id": "7f000001-94c6-115d-8194-c6eb3d380004",
      "content": "답글입니다",
      "author": {
        "id": "7f000001-94c1-1be2-8194-c18e43250000",
        "name": "admin1"
      },
      "createdAt": "2025-02-02T13:48:12.983Z",
      "updatedAt": "2025-02-02T13:48:12.983Z"
    }
  ]
}
```