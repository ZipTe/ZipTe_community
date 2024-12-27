# 0. Getting Started (시작하기)
```bash
서비스 링크 추가 예정
```

<br/>
<br/>

# 1. Project Overview (프로젝트 개요)
- 프로젝트 이름: ZIPTE
- 프로젝트 설명: 개인별  상황에 맞는 적절한 거주지를 추천하고, 아파트에 대한 이야기를 나누는 커뮤니티 서비스를 제공합니다.
- 프로젝트 기간 : 2024.10.27 ~ 2024.12.31 (1인 개발 진행중)

<br/>
<br/>

# 2. Team Member (팀원 소개)
| 이도연 |
|:------:|
| <img src="https://github.com/user-attachments/assets/653c94e3-5837-4e40-8ee9-b0ff135b59e7" alt="이도연" width="150"> | 
| BE |
| [GitHub](https://github.com/doup2001) | 

<br/>
<br/>

# 3. Key Features (주요 기능)
- **회원가입**:
   - Oauth2 & JWT 사용 회원가입
   - 네이버 및 구글
   - Oauth로 새롭게 가입하게되면 Oauth_User
   - 회원가입 이후, 주소를 추가하면 Oauth_User에서 User로 업그레이드
   - 주문(구독제)을 통하여 MemberRole upgrade 가능 → VIP, VVIP
   - 마이페이지에서 회원정보 수정 가능
   - 회원탈퇴 기능
   - 역할별 접근가능한 페이지 존재

- **로그인**:
  - 소셜 로그인 정보를 통해 로그인합니다.
  - JWT 토큰을 통해 유저의 정보가 맞는지 체크합니다.

- **아파트**:
  - FastAPI를 통해, 아파트의 정보 및 유사한 아파트 데이터 받기
  - FastAPI를 통해, 개인별 선호도에 따른 아파트 데이터 받기
  - FastAPI를 통해, 지난 N년간 아파트별 실거래가를 받기
  - FastAPI를 통해, 지난 N년간 특정 법정동별 가격 정보(평당) 데이터 받기

- **아파트 리뷰**:
  - 리뷰 내용 입력 (사진포함)
  - 아파트에 대한 평점 입력 기능 
  - 이용자 별 리뷰 확인 가능
  - 조회수, 댓글 가능
  - Pageable 써서 목록 조회 페이징처리

- **아이템 보기** :
  - 상품 정보 내용 입력 (사진포함)
  - 아이템 상세보기에서 수량을 선택하여 장바구니로 추가 가능
  - Pageable 써서 목록 조회 페이징처리

- **장바구니**:
  - 한번에 여러개의 아이템 구매 가능하도록 장바구니 기능 구현
  - 아이템 상세보기에서 수량을 선택하여 장바구니로 추가 가능

- **상품 및 결제**:
  - 여러개 아이템 구매 혹은 하나의 아이템 구매 가능
  - 토스의 결제 위젯을 사용하여, 아이템을 결제
  - 토스를 위한 가주문(Order) 테이블과, 실제 결제를 완료하면 생기는 Payment 테이블로 나눠서 관리
  - 결제가 완료된 주문에 대해서 상세 주문 정보 확인 가능


<br/>
<br/>

# 4. Technology Stack (기술 스택)
## 5. Backend
|  |  |  |
|-----------------|-----------------|-----------------|
| SpringBoot    |  <img src="https://github.com/user-attachments/assets/43d80a85-2060-4475-95a8-fc402f837aa0" alt="SpringBoot" width="100">    | 3.3.4  |
| SpringSecurity    |  <img src="https://github.com/user-attachments/assets/7eafc435-a1b9-47ab-bbc2-6ac02d21a4f0" alt="SpringSecurity" width="100">    |  |

<br/>

## 6. Cooperation
|  |  |
|-----------------|-----------------|
| Git    |  <img src="https://github.com/user-attachments/assets/483abc38-ed4d-487c-b43a-3963b33430e6" alt="git" width="100">    |
| Notion    |  <img src="https://github.com/user-attachments/assets/34141eb9-deca-416a-a83f-ff9543cc2f9a" alt="Notion" width="100">    |

<br/>

# 7. Project Structure (프로젝트 구조)
```plaintext
src
    ├── main
    │   ├── java
    │   │   └── org
    │   │       └── gdg
    │   │           └── zipte_gdg
    │   │               ├── ZipteGdgApplication.java
    │   │               ├── api
    │   │               │   ├── controller
    │   │               │   │   ├── apt
    │   │               │   │   ├── cart
    │   │               │   │   ├── comment
    │   │               │   │   ├── delivery
    │   │               │   │   ├── member
    │   │               │   │   ├── order
    │   │               │   │   ├── page
    │   │               │   │   ├── payment
    │   │               │   │   ├── product
    │   │               │   │   ├── response
    │   │               │   │   ├── review
    │   │               │   │   └── toss
    │   │               │   └── service
    │   │               │       ├── apt
    │   │               │       ├── cart
    │   │               │       ├── comment
    │   │               │       ├── delivery
    │   │               │       ├── member
    │   │               │       ├── order
    │   │               │       ├── page
    │   │               │       ├── payment
    │   │               │       ├── product
    │   │               │       ├── review
    │   │               │       └── toss
    │   │               ├── domain
    │   │               │   ├── apt
    │   │               │   ├── area
    │   │               │   ├── cart
    │   │               │   ├── comment
    │   │               │   ├── delivery
    │   │               │   ├── member
    │   │               │   ├── order
    │   │               │   ├── orderItem
    │   │               │   ├── own
    │   │               │   ├── payment
    │   │               │   ├── product
    │   │               │   ├── rating
    │   │               │   ├── review
    │   │               │   ├── role
    │   │               │   └── subway
    │   │               └── security
    │   │                   ├── config
    │   │                   ├── jwt
    │   │                   │   ├── filter
    │   │                   │   ├── handler
    │   │                   │   └── provider
    │   │                   ├── oauth
    │   │                   └── redis
    │   └── resources
    │       ├── application.yml
    │       ├── static
```

<br/>
<br/>

# 8. Development Workflow (개발 워크플로우)
## 브랜치 전략 (Branch Strategy)
우리의 브랜치 전략은 Git Flow를 기반으로 하며, 다음과 같은 브랜치를 사용합니다.

- Main Branch
  - 배포 가능한 상태의 코드를 유지합니다.
  - 모든 배포는 이 브랜치에서 이루어집니다.
 
- Devlop Branch
  - 만든 기능들이 작동하는지 코드를 합병합니다.
  
- {feat} Branch
  - 모든 기능 개발은 feat 브랜치에서 이루어집니다.

<br/>
<br/>

# 9. 커밋 컨벤션

## type 종류
```
feat : 새로운 기능 추가
fix : 버그 수정
docs : 문서 수정
style : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
refactor : 코드 리펙토링
test : 테스트 코드, 리펙토링 테스트 코드 추가
chore : 설정 추가
```

<br/>

## 커밋 이모지
```
== 코드 관련
📝	코드 작성
🔥	코드 제거
♻️️	코드 리팩토링

== 문서&파일
📰	새 파일 생성
♻️️	파일 제거
📚	문서 작성

== 버그
🐛	버그 리포트
🚑	버그를 고칠 때

== 기타
🐎	성능 향상
✨	새로운 기능 구현
💡	새로운 아이디어
🚀	배포
```

<br/>

## 커밋 예시
```
== ex1
✨Feat: "회원 가입 기능 구현"

SMS, 이메일 중복확인 API 개발

== ex2
🔨chore: styled-components 라이브러리 설치

UI개발을 위한 라이브러리 styled-components 설치
```

<br/>
<br/>

# 10. 컨벤션 수행 결과

