# 🎵 Vibe - 음악 커뮤니티 게시판

> Spring MVC 기반 음악 스트리밍 플랫폼

## 📌 프로젝트 소개

Vibe는 음악을 공유하고 소통할 수 있는 커뮤니티 플랫폼입니다.
사용자들은 게시판에서 음악에 대한 의견을 나누고, 좋아하는 음악을 재생하며, 
플레이리스트를 만들 수 있습니다.

- **개발 기간**: 2024.11.27 ~ 2024.12.11 (2주)
- **개발 인원**: 1명 (개인 프로젝트)
- **과목**: SPRING-2025

---

## 🛠️ 기술 스택

### Backend
- **Framework**: Spring MVC 4.3.25
- **ORM**: MyBatis 3.5.6
- **Language**: Java 1.8
- **Build Tool**: Maven

### Frontend
- **Template Engine**: JSP, JSTL
- **Markup**: HTML5, CSS3
- **Script**: JavaScript

### Database
- **DBMS**: MariaDB 10.6

### Server
- **WAS**: Apache Tomcat 9.0

### API
- **Music API**: Spotify Web API

### Tools
- **IDE**: Spring Tool Suite 3
- **DB Tool**: HeidiSQL
- **Version Control**: Git

---

## ✨ 주요 기능

### 1. 게시판 CRUD
- ✅ 게시글 등록 (로그인 사용자)
- ✅ 게시글 조회 (목록/상세)
- ✅ 게시글 수정 (작성자 본인)
- ✅ 게시글 삭제 (작성자 본인, CASCADE)
- ✅ 카테고리별 필터링

### 2. 검색 기능
- ✅ 제목/내용 통합 검색
- ✅ 키워드 기반 LIKE 검색
- ✅ MyBatis 동적 쿼리 활용

### 3. 페이징
- ✅ 페이지당 5개씩 표시
- ✅ Criteria 패턴 활용
- ✅ 이전/다음 페이지 네비게이션

### 4. 댓글 시스템
- ✅ 댓글 작성/삭제
- ✅ 작성자 정보 JOIN 조회
- ✅ 실시간 반영

### 5. 회원 관리
- ✅ 회원가입/로그인
- ✅ 세션 기반 인증
- ✅ 권한별 기능 제어

### 6. 음원 스트리밍
- ✅ 음원 재생 (HTML5 Audio)
- ✅ 하단 고정 플레이어
- ✅ 재생 횟수 카운팅

### 7. 좋아요 기능
- ✅ AJAX 비동기 처리
- ✅ 좋아요 추가/취소
- ✅ 마이페이지에서 목록 확인

### 8. Spotify API 연동
- ✅ 음원 검색 (관리자)
- ✅ 앨범 커버 자동 가져오기
- ✅ 메타데이터 자동 입력

---

## 🗂️ 프로젝트 구조
```
project/
├─ src/main/java/com/music/
│  ├─ controller/           # 요청 처리
│  │  ├─ HomeController.java
│  │  ├─ PostController.java
│  │  ├─ UserController.java
│  │  ├─ SongController.java
│  │  ├─ LikeController.java
│  │  └─ SpotifyController.java
│  │
│  ├─ service/              # 비즈니스 로직
│  │  ├─ PostService.java
│  │  ├─ PostServiceImpl.java
│  │  ├─ UserService.java
│  │  ├─ UserServiceImpl.java
│  │  ├─ SongService.java
│  │  ├─ SongServiceImpl.java
│  │  ├─ LikeService.java
│  │  ├─ LikeServiceImpl.java
│  │  ├─ SpotifyService.java
│  │  └─ SpotifyServiceImpl.java
│  │
│  ├─ mapper/               # MyBatis 인터페이스
│  │  ├─ PostMapper.java
│  │  ├─ UserMapper.java
│  │  ├─ SongMapper.java
│  │  ├─ CommentMapper.java
│  │  ├─ LikeMapper.java
│  │  └─ PlaylistMapper.java
│  │
│  └─ domain/               # VO 클래스
│     ├─ PostVO.java
│     ├─ UserVO.java
│     ├─ SongVO.java
│     ├─ CommentVO.java
│     ├─ PlaylistVO.java
│     ├─ Criteria.java
│     └─ PageDTO.java
│
├─ src/main/resources/
│  └─ com/music/mapper/     # MyBatis XML
│     ├─ PostMapper.xml
│     ├─ UserMapper.xml
│     ├─ SongMapper.xml
│     ├─ CommentMapper.xml
│     ├─ LikeMapper.xml
│     └─ PlaylistMapper.xml
│
└─ src/main/webapp/
   ├─ WEB-INF/
   │  ├─ views/project/     # JSP 뷰
   │  │  ├─ board/          # 게시판
   │  │  ├─ user/           # 회원
   │  │  └─ song/           # 음원
   │  ├─ spring/            # Spring 설정
   │  │  ├─ root-context.xml
   │  │  └─ appServlet/servlet-context.xml
   │  └─ web.xml
   │
   └─ resources/
      ├─ css/               # 스타일시트
      ├─ js/                # 자바스크립트
      └─ music/             # 음원 파일 (gitignore)
```

---

## 🗄️ 데이터베이스 설계

### ERD (7개 테이블)

#### 1. users (회원)
- 회원 정보 저장
- 이메일, 비밀번호, 사용자명

#### 2. posts (게시글)
- 게시판 게시글 정보
- 제목, 내용, 카테고리, 조회수

#### 3. comments (댓글)
- 게시글에 달린 댓글
- posts와 1:N 관계

#### 4. songs (음원)
- 음원 정보와 메타데이터
- 제목, 아티스트, 앨범, 재생시간

#### 5. song_likes (좋아요)
- 사용자의 음원 좋아요 정보
- users와 songs를 연결 (N:M)

#### 6. playlists (플레이리스트)
- 사용자 플레이리스트 정보

#### 7. playlist_songs (플레이리스트-음원)
- playlists와 songs를 연결 (N:M)

### 관계 설정
- `users(1) → posts(N)`: 한 회원이 여러 게시글 작성
- `users(1) → comments(N)`: 한 회원이 여러 댓글 작성
- `posts(1) → comments(N)`: 한 게시글에 여러 댓글
- `users ↔ songs (N:M)`: song_likes 중간 테이블
- `playlists ↔ songs (N:M)`: playlist_songs 중간 테이블

---

## 🚀 설치 및 실행 방법

### 1. 사전 준비
```bash
- JDK 1.8 이상
- Apache Tomcat 9.0
- MariaDB 10.6 이상
- Maven 3.x
```

### 2. 데이터베이스 설정
```sql
-- 데이터베이스 생성
CREATE DATABASE vibe_db;

-- 테이블 생성
-- (프로젝트 내 SQL 파일 참고)
```

### 3. 프로젝트 클론
```bash
git clone https://github.com/your-username/vibe.git
cd vibe
```

### 4. 데이터베이스 연결 설정
```
src/main/webapp/WEB-INF/spring/root-context.xml 수정
- DB URL, username, password 설정
```

### 5. 빌드 및 실행
```bash
# Maven 빌드
mvn clean install

# Tomcat 실행 (STS 사용 시)
Run As → Run on Server
```

### 6. 접속
```
http://localhost:8080/
```

---

## 💡 핵심 기능 구현

### 1. 페이징 처리
```java
// Criteria.java - offset 자동 계산
public int getOffset() {
    return (pageNum - 1) * amount;
}
```

### 2. 검색 기능
```xml
<!-- PostMapper.xml - 동적 쿼리 -->
<select id="searchPosts">
    WHERE title LIKE CONCAT('%', #{keyword}, '%')
       OR content LIKE CONCAT('%', #{keyword}, '%')
</select>
```

### 3. 좋아요 토글
```java
// LikeServiceImpl.java - 좋아요 추가/취소
public boolean toggleLike(int userId, int songId) {
    if (likeMapper.isLiked(userId, songId) > 0) {
        likeMapper.delete(userId, songId);
        return false;  // 취소
    } else {
        likeMapper.insert(userId, songId);
        return true;   // 추가
    }
}
```

---

## 📚 배운 점

1. **Spring MVC 계층 구조 이해**
   - Controller-Service-Mapper 역할 분리
   - 의존성 주입과 유지보수성 향상

2. **MyBatis 동적 쿼리와 페이징**
   - `<if>`, `<choose>` 동적 쿼리 활용
   - Criteria 패턴으로 재사용성 확보

3. **세션 관리와 권한 제어**
   - HttpSession 기반 로그인 상태 유지
   - 작성자 검증으로 보안 강화

---

## 😓 어려웠던 점

1. **계속적인 404 에러**
   - 경로 매핑과 설정 파일 이해 부족
   - servlet-context.xml 학습으로 해결

2. **비동기 처리와 Ajax 구현**
   - 좋아요 기능 구현 시 복잡성
   - JSON 응답 처리 방법 학습

3. **페이징과 검색 기능 연동**
   - keyword와 pageNum 동시 전달
   - 파라미터 처리 로직 개선

---

## 🔧 향후 개선 사항

- [ ] 대댓글(답글) 기능 추가
- [ ] 비밀번호 암호화 (BCrypt)
- [ ] 파일 첨부 기능
- [ ] 실시간 알림 (WebSocket)
- [ ] 모바일 반응형 개선
- [ ] 소셜 로그인 (OAuth 2.0)

---

## 📝 라이선스

This project is licensed under the MIT License.

---

## 👤 개발자

- **이름**: 선우
- **과목**: SPRING-2025
- **기간**: 2024.11.27 ~ 2024.12.11

---

## 🙏 감사합니다

프로젝트를 봐주셔서 감사합니다!
