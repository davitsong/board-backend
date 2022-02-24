-- 영화 테이블
CREATE TABLE movie
(
    movieseq NUMBER         constraint movie_seq_pk PRIMARY KEY,   -- 영화 일련번호
    usrid    VARCHAR2(20)   constraint movie_id_nn  NOT NULL,      -- 작성자 아이디 (관리인 작성)
    title    VARCHAR2(200)  constraint movie_title_nn  NOT NULL,   -- 영화 제목
    "content"  VARCHAR2(4000) constraint movie_content_nn  NOT NULL, -- 영화 내용
    "like"  NUMBER DEFAULT 0,                                     -- 영화 좋아요
    hitcount NUMBER DEFAULT 0,                                     -- 게시글 조회수
    dislike  NUMBER DEFAULT 0,                                     -- 영화 싫어요
    regdate  DATE DEFAULT sysdate                                 -- 등록일
);

CREATE SEQUENCE movie_seq;

-- 영화 테이블
CREATE TABLE movie
(
    movieseq NUMBER         constraint movie_seq_pk PRIMARY KEY,   -- 영화 일련번호
    usrid    VARCHAR2(20)   constraint movie_id_nn  NOT NULL,      -- 작성자 아이디 (관리인 작성)
    title    VARCHAR2(200)  constraint movie_title_nn  NOT NULL,   -- 영화 제목
    "content"  VARCHAR2(4000) constraint movie_content_nn  NOT NULL, -- 영화 내용
    "like"  NUMBER DEFAULT 0,                                     -- 영화 좋아요
    hitcount NUMBER DEFAULT 0,                                     -- 게시글 조회수
    dislike  NUMBER DEFAULT 0,                                     -- 영화 싫어요
    regdate  DATE DEFAULT sysdate,                                 -- 등록일
    originalfile VARCHAR(500), --파일명(원래이름)
    savedfile VARCHAR2(500)  -- 첨부파일명(실제 저장된 파일명)
);

CREATE SEQUENCE movie_seq;



-- 회원 테이블
CREATE TABLE member2
(
    usrid   VARCHAR2(20)   PRIMARY KEY,
    usrpwd  VARCHAR2(20)  NOT NULL,
    usrname VARCHAR2(50)   NOT NULL,
    email   VARCHAR2(100)  NOT NULL,
    phonenum VARCHAR2(20) NOT NULL,
    enabled NUMBER(1) DEFAULT 1,   ---1: 사용가능, 0: 사용불가능 (SECURITY에서 사용하는 컬럼)
    rolename VARCHAR2(50) DEFAULT 'ROLE_USER' NOT NULL -- ROLE_USER, ROLE_MANAGER, ROLE_ADMIN 등 각 역할에 따른 이름
);

CREATE SEQUENCE member2_seq;

--- 댓글 테이블
CREATE TABLE reply2
(
    replyseq NUMBER primary key,
    movieseq NUMBER NOT NULL,
    usrid VARCHAR2(20) NOT NULL,
    replytext VARCHAR2(1000) NOT NULL,
    regdate DATE default sysdate,
    CONSTRAINT reply2_fk FOREIGN KEY(movieseq) REFERENCES  movie(movieseq) ON DELETE CASCADE

);

CREATE SEQUENCE reply2_seq;

