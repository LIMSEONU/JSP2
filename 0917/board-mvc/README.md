# Board MVC Project

게시판 기능을 MVC 패턴으로 구현한 웹 애플리케이션입니다.

## 프로젝트 구조

```
board-mvc/
├── src/main/
│   ├── java/
│   │   ├── controller/
│   │   │   ├── BoardController.java    # 초기 진입점 컨트롤러
│   │   │   └── BoardListController.java # 게시판 목록 컨트롤러
│   │   ├── dao/
│   │   │   └── BoardDAO.java           # 데이터베이스 접근 객체
│   │   └── dto/
│   │       └── BoardDTO.java           # 데이터 전송 객체
│   └── webapp/
│       ├── META-INF/
│       │   └── context.xml             # 데이터베이스 연결 설정
│       └── WEB-INF/
│           ├── views/
│           │   └── list.jsp            # 게시판 목록 뷰
│           └── web.xml                 # 웹 애플리케이션 설정
```

## 주요 기능

### 1. 페이징 처리
- 한 페이지당 게시물 수: 10개
- 화면에 표시되는 페이지 번호: 5개
- Prev/Next 네비게이션
- 현재 페이지 하이라이트 표시

### 2. 게시판 목록 표시
- 번호(Num)
- 제목(Subject)
- 작성자(Writer)
- 등록일(RegDate)
- 게시물 hover 효과

### 3. 데이터베이스 연동
- MariaDB 사용
- JNDI 데이터소스 설정
- 게시물 테이블 구조:
  ```sql
  CREATE TABLE BOARD(
      BCODE INT AUTO_INCREMENT PRIMARY KEY,
      SUBJECT VARCHAR(100),
      CONTENT TEXT,
      WRITER VARCHAR(50),
      REGDATE DATE
  );
  ```

## 기술 스택
- Java
- JSP/Servlet
- JSTL
- MariaDB
- HTML/CSS
- JavaScript

## 주요 클래스 설명

### BoardController (index.do)
- 애플리케이션 진입점
- list.do로 포워딩하여 게시판 목록 표시

### BoardListController (list.do)
- 페이징 처리 로직
- 게시물 목록 조회
- 뷰에 필요한 데이터 전달
  - count: 전체 레코드 수
  - numOfRecords: 한 페이지당 레코드 수 (10)
  - numOfPages: 화면에 표시할 페이지 수 (5)
  - startNum: 시작 페이지 번호
  - lastNum: 마지막 페이지 번호

### BoardDAO
- 데이터베이스 연동
- 게시물 목록 조회
- 전체 게시물 수 조회

## 화면 구성
```
[게시판 목록]
-------------------
전체 게시물: XX개

[게시물 목록 테이블]
Num | Subject | Writer | RegDate

[홈으로] [게시글 등록]

[페이지 네비게이션]
[Prev] 1 2 3 4 5 [Next]


```

## 실행 방법
1. 서버 실행
2. http://localhost:8080/board-mvc/ 접속
3. index.do를 통해 자동으로 게시판 목록 표시