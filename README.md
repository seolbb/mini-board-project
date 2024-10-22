# 게시판 만들기
- 멋쟁이사자처럼 미니 프로젝트
## 📌 프로젝트 요구 사항
### 1. 게시글 목록 보기 (`/list`)
- **URL:** `/list`, `/list?page=2`
- **기능:**
  - 게시글 목록을 페이지별로 보여줍니다.
  - `page` 파라미터가 없으면 기본적으로 1페이지를 보여줍니다.
  - 각 페이지는 최신 글부터 보여지며, 페이징 처리가 적용되어 있습니다.
  - 하단에는 페이지 네비게이터가 있어 다른 페이지로 쉽게 이동할 수 있습니다.
  - 각 게시글은 ID, 제목, 이름, 등록일(YYYY/MM/DD 형식)로 목록이 구성됩니다.

### 2. 게시글 상세 조회 (`/view?id=아이디`)
- **URL:** `/view?id=아이디`
- **기능:**
  - 특정 게시글의 상세 내용을 보여줍니다.
  - 삭제와 수정 링크를 제공하여 해당 기능을 수행할 수 있는 페이지로 이동할 수 있습니다.
  - 게시글의 등록일은 YYYY/MM/DD hh24:mi 형식으로 표시됩니다.
  - 게시글의 암호는 보여지지 않습니다.

### 3. 게시글 등록 폼 (`/writeform`)
- **URL:** `/writeform`
- **기능:**
  - 특정 게시글을 쓰기위한 폼을 제공합니다.
  - 사용자는 이름, 제목, 내용, 암호를 입력하고, 확인 버튼을 클릭하여 등록을 요청합니다.
  - 모든 내용이 잘 입력되어 있을 경우 `/write`로 요청을 보내 등록 처리 후 `/list`로 리다이렉트됩니다.

### 4. 게시글 삭제 폼 (`/deleteform?id=아이디`)
- **URL:** `/deleteform?id=아이디`
- **기능:**
  - 특정 게시글을 삭제하기 위한 폼을 제공합니다.
  - 사용자는 암호를 입력하고, 확인 버튼을 클릭하여 삭제를 요청합니다.
  - 올바른 암호 입력 시, `/delete`로 요청을 보내 삭제 처리 후 `/list`로 리다이렉트됩니다.

### 5. 게시글 수정 폼 (`/updateform?id=아이디`)
- **URL:** `/updateform?id=아이디`
- **기능:**
  - 특정 게시글을 수정하기 위한 폼을 제공합니다.
  - 이름, 제목, 본문, 암호 필드를 포함하며, 사용자는 글 등록시 입력했던 암호를 입력해야 이를 수정할 수 있습니다.
  - 확인 버튼을 클릭하면 `/update`로 수정 요청을 보내고, 수정이 완료되면 해당 게시글의 상세 페이지(`/view?id=아이디`)로 리다이렉트됩니다.



## 📌 개발 단계
### 1. **환경 설정**
- Spring Boot 프로젝트 생성
- 필요한 의존성 추가 (Spring Web, Spring Data JDBC 등)
     
### 2. **데이터베이스 구성**
- SQL 스크립트를 사용하여 Users, Posts, Comments 테이블 생성 및 초기 데이터 삽입

### 3. **도메인 모델 생성**
- 각 테이블에 대응하는 Java 클래스(도메인 모델) 생성

### 4. **DAO 개발**
- Spring Data JDBC를 이용한 Repository 인터페이스 구현

### 5. **서비스 계층 구현**
- 비즈니스 로직을 수행하는 서비스 클래스 구현

### 6. **컨트롤러 구현**
- 컨트롤러 클래스 구현
- thymeleaf템플릿 작성

