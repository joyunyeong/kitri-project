/* PLZDAENGS_USER */
DROP TABLE PLZ_USER 
	CASCADE CONSTRAINTS;

/* GROUP */
DROP TABLE PLZ_GROUP 
	CASCADE CONSTRAINTS;

/* GROUP_MEMBER */
DROP TABLE PLZ_GROUP_MEMBER 
	CASCADE CONSTRAINTS;

/* GROUP_CHAT */
DROP TABLE PLZ_GROUP_CHAT 
	CASCADE CONSTRAINTS;

/* PLZ_PET */
DROP TABLE PLZ_PET 
	CASCADE CONSTRAINTS;

/* DIARY */
DROP TABLE PLZ_DIARY 
	CASCADE CONSTRAINTS;

/* PLZ_BREED */
DROP TABLE PLZ_BREED 
	CASCADE CONSTRAINTS;

/* PLZ_ANIMAL */
DROP TABLE PLZ_ANIMAL 
	CASCADE CONSTRAINTS;

/* VACCINATION */
DROP TABLE PLZ_VACCINATION 
	CASCADE CONSTRAINTS;

/* TAKEVACCIN */
DROP TABLE PLZ_TAKEVACCIN 
	CASCADE CONSTRAINTS;

/* GROUP_MEETING */
DROP TABLE PLZ_GROUP_MEETING 
	CASCADE CONSTRAINTS;

/* BOARD */
DROP TABLE PLZ_BOARD 
	CASCADE CONSTRAINTS;

/* USER_DETAIL */
DROP TABLE PLZ_USER_DETAIL 
	CASCADE CONSTRAINTS;

/* REPLY */
DROP TABLE PLZ_REPLY 
	CASCADE CONSTRAINTS;

/* BOARD_CATEGORY */
DROP TABLE PLZ_BOARD_CATEGORY 
	CASCADE CONSTRAINTS;

/* LIKES */
DROP TABLE PLZ_LIKES 
	CASCADE CONSTRAINTS;

/* DIARY_CATEGORY */
DROP TABLE PLZ_DIARY_CATEGORY 
	CASCADE CONSTRAINTS;

/* GROUP_TYPE */
DROP TABLE PLZ_GROUP_TYPE 
	CASCADE CONSTRAINTS;

/* MEETING_MEMBER */
DROP TABLE PLZ_MEETING_MEMBER 
	CASCADE CONSTRAINTS;

/* PLZDAENGS_USER */
CREATE TABLE PLZ_USER (
	user_id VARCHAR2(32) NOT NULL, /* 유저ID */
	password VARCHAR2(32), /* 패스워드 */
	emailid VARCHAR2(32), /* 이메일ID */
	emaildomain VARCHAR2(64), /* 이메일DOMAIN */
	nickname VARCHAR2(64), /* 닉네임 */
	user_img VARCHAR2(128), /* 유저프로필 */
	authority VARCHAR2(1), /* 권한 */
	join_date DATE, /* 가입일 */
	status VARCHAR2(1) DEFAULT 'F', /* 탈퇴여부 */
	secession_date DATE /* 탈퇴일 */
);

COMMENT ON TABLE PLZ_USER IS 'PLZDAENGS_USER';

COMMENT ON COLUMN PLZ_USER.user_id IS '유저ID';

COMMENT ON COLUMN PLZ_USER.password IS '패스워드';

COMMENT ON COLUMN PLZ_USER.emailid IS '이메일ID';

COMMENT ON COLUMN PLZ_USER.emaildomain IS '이메일DOMAIN';

COMMENT ON COLUMN PLZ_USER.nickname IS '닉네임';

COMMENT ON COLUMN PLZ_USER.user_img IS '유저프로필';

COMMENT ON COLUMN PLZ_USER.authority IS '권한';

COMMENT ON COLUMN PLZ_USER.join_date IS '가입일';

COMMENT ON COLUMN PLZ_USER.status IS 'F : 가입
T : 탈퇴';

COMMENT ON COLUMN PLZ_USER.secession_date IS '탈퇴일';

CREATE UNIQUE INDEX PK_PLZ_USER
	ON PLZ_USER (
		user_id ASC
	);

ALTER TABLE PLZ_USER
	ADD
		CONSTRAINT PK_PLZ_USER
		PRIMARY KEY (
			user_id
		);

/* GROUP */
CREATE TABLE PLZ_GROUP (
	group_id NUMBER NOT NULL, /* 그룹ID */
	group_category_id VARCHAR2(1), /* 그룹카테고리ID */
	group_name VARCHAR2(64), /* 그룹이름 */
	description VARCHAR2(1024), /* 그룹설명 */
	group_img VARCHAR2(128), /* 그룹대표이미지 */
	address_sido VARCHAR2(32), /* 주소시도 */
	address_sigungu VARCHAR2(32) /* 주소시군구 */
);

COMMENT ON TABLE PLZ_GROUP IS 'GROUP';

COMMENT ON COLUMN PLZ_GROUP.group_id IS '그룹ID';

COMMENT ON COLUMN PLZ_GROUP.group_category_id IS '오프라인
온라인
산책
지식공유
직접입력
';

COMMENT ON COLUMN PLZ_GROUP.group_name IS '그룹이름';

COMMENT ON COLUMN PLZ_GROUP.description IS '그룹설명';

COMMENT ON COLUMN PLZ_GROUP.group_img IS '그룹대표이미지';

COMMENT ON COLUMN PLZ_GROUP.address_sido IS '주소시도';

COMMENT ON COLUMN PLZ_GROUP.address_sigungu IS '주소시군구';

CREATE UNIQUE INDEX PK_PLZ_GROUP
	ON PLZ_GROUP (
		group_id ASC
	);

ALTER TABLE PLZ_GROUP
	ADD
		CONSTRAINT PK_PLZ_GROUP
		PRIMARY KEY (
			group_id
		);

/* GROUP_MEMBER */
CREATE TABLE PLZ_GROUP_MEMBER (
	group_id NUMBER NOT NULL, /* 그룹ID */
	user_id VARCHAR2(32) NOT NULL, /* 유저ID */
	member_status VARCHAR2(1), /* 멤버분류상태 */
	group_joindate DATE /* 새 컬럼 */
);

COMMENT ON TABLE PLZ_GROUP_MEMBER IS 'GROUP_MEMBER';

COMMENT ON COLUMN PLZ_GROUP_MEMBER.group_id IS '그룹ID';

COMMENT ON COLUMN PLZ_GROUP_MEMBER.user_id IS '유저ID';

COMMENT ON COLUMN PLZ_GROUP_MEMBER.member_status IS '대표        L
신청        A
가입완료  M
탈퇴        X
';

COMMENT ON COLUMN PLZ_GROUP_MEMBER.group_joindate IS '새 컬럼';

CREATE UNIQUE INDEX PK_PLZ_GROUP_MEMBER
	ON PLZ_GROUP_MEMBER (
		group_id ASC,
		user_id ASC
	);

ALTER TABLE PLZ_GROUP_MEMBER
	ADD
		CONSTRAINT PK_PLZ_GROUP_MEMBER
		PRIMARY KEY (
			group_id,
			user_id
		);

/* GROUP_CHAT */
CREATE TABLE PLZ_GROUP_CHAT (
	group_id NUMBER NOT NULL, /* 그룹ID */
	user_id VARCHAR2(32) NOT NULL, /* 유저ID */
	chat_date TIMESTAMP NOT NULL, /* 채팅날짜 */
	chat_contents VARCHAR2(256) /* 채팅내용 */
);

COMMENT ON TABLE PLZ_GROUP_CHAT IS 'GROUP_CHAT';

COMMENT ON COLUMN PLZ_GROUP_CHAT.group_id IS '그룹ID';

COMMENT ON COLUMN PLZ_GROUP_CHAT.user_id IS '유저ID';

COMMENT ON COLUMN PLZ_GROUP_CHAT.chat_date IS '채팅날짜';

COMMENT ON COLUMN PLZ_GROUP_CHAT.chat_contents IS '채팅내용';

CREATE UNIQUE INDEX PK_PLZ_GROUP_CHAT
	ON PLZ_GROUP_CHAT (
		group_id ASC,
		user_id ASC,
		chat_date ASC
	);

ALTER TABLE PLZ_GROUP_CHAT
	ADD
		CONSTRAINT PK_PLZ_GROUP_CHAT
		PRIMARY KEY (
			group_id,
			user_id,
			chat_date
		);

/* PLZ_PET */
CREATE TABLE PLZ_PET (
	user_id VARCHAR2(32) NOT NULL, /* 유저ID */
	pet_name VARCHAR2(64) NOT NULL, /* 펫이름 */
	animal_code VARCHAR2(32) NOT NULL, /* 동물코드 */
	breed_code VARCHAR2(32), /* 품종코드 */
	pet_gender VARCHAR2(1), /* 펫성별 */
	birth_date DATE, /* 펫생일 */
	pet_type VARCHAR2(1), /* 펫대표여부 */
	pet_img VARCHAR2(128) /* 펫이미지 */
);

COMMENT ON TABLE PLZ_PET IS 'PLZ_PET';

COMMENT ON COLUMN PLZ_PET.user_id IS '유저ID';

COMMENT ON COLUMN PLZ_PET.pet_name IS '펫이름';

COMMENT ON COLUMN PLZ_PET.animal_code IS '동물코드';

COMMENT ON COLUMN PLZ_PET.breed_code IS '품종코드';

COMMENT ON COLUMN PLZ_PET.pet_gender IS 'M
F';

COMMENT ON COLUMN PLZ_PET.birth_date IS '펫생일';

COMMENT ON COLUMN PLZ_PET.pet_type IS 'TRUE : 대표펫
T : 대표
F : 비대표
';

COMMENT ON COLUMN PLZ_PET.pet_img IS '펫이미지';

CREATE UNIQUE INDEX PK_PLZ_PET
	ON PLZ_PET (
		user_id ASC,
		pet_name ASC
	);

ALTER TABLE PLZ_PET
	ADD
		CONSTRAINT PK_PLZ_PET
		PRIMARY KEY (
			user_id,
			pet_name
		);

/* DIARY */
CREATE TABLE PLZ_DIARY (
	diary_number NUMBER NOT NULL, /* 다이어리번호 */
	user_id VARCHAR2(32) NOT NULL, /* 유저ID */
	category_id2 VARCHAR2(32), /* 카테고리ID */
	category_id VARCHAR2(32), /* 다이어리타입 */
	diary_date DATE, /* 다이어리날짜 */
	diary_subject VARCHAR2(256), /* 다이어리제목 */
	hashtag VARCHAR2(1024), /* 해쉬태그 */
	diary_contents CLOB, /* 다이어리내용 */
	diary_img VARCHAR2(128), /* 다이어리이미지 */
	location_x VARCHAR2(64), /* x좌표 */
	location_y VARCHAR2(64), /* y좌표 */
	create_date DATE /* 다이어리생성일 */
);

COMMENT ON TABLE PLZ_DIARY IS 'DIARY';

COMMENT ON COLUMN PLZ_DIARY.diary_number IS '다이어리번호';

COMMENT ON COLUMN PLZ_DIARY.user_id IS '유저ID';

COMMENT ON COLUMN PLZ_DIARY.category_id2 IS '카테고리ID';

COMMENT ON COLUMN PLZ_DIARY.category_id IS '다이어리타입';

COMMENT ON COLUMN PLZ_DIARY.diary_date IS '달력에 표시되는 기준날짜';

COMMENT ON COLUMN PLZ_DIARY.diary_subject IS '다이어리제목';

COMMENT ON COLUMN PLZ_DIARY.hashtag IS '해쉬태그';

COMMENT ON COLUMN PLZ_DIARY.diary_contents IS '다이어리내용';

COMMENT ON COLUMN PLZ_DIARY.diary_img IS '다이어리이미지';

COMMENT ON COLUMN PLZ_DIARY.location_x IS 'x좌표';

COMMENT ON COLUMN PLZ_DIARY.location_y IS 'y좌표';

COMMENT ON COLUMN PLZ_DIARY.create_date IS '다이어리생성일';

CREATE UNIQUE INDEX PK_PLZ_DIARY
	ON PLZ_DIARY (
		diary_number ASC
	);

ALTER TABLE PLZ_DIARY
	ADD
		CONSTRAINT PK_PLZ_DIARY
		PRIMARY KEY (
			diary_number
		);

/* PLZ_BREED */
CREATE TABLE PLZ_BREED (
	animal_code VARCHAR2(32) NOT NULL, /* 동물코드 */
	breed_code VARCHAR2(32) NOT NULL, /* 품종코드 */
	breed_name VARCHAR2(64) /* 품종이름 */
);

COMMENT ON TABLE PLZ_BREED IS 'PLZ_BREED';

COMMENT ON COLUMN PLZ_BREED.animal_code IS '동물코드';

COMMENT ON COLUMN PLZ_BREED.breed_code IS '품종코드';

COMMENT ON COLUMN PLZ_BREED.breed_name IS '품종이름';

CREATE UNIQUE INDEX PK_PLZ_BREED
	ON PLZ_BREED (
		animal_code ASC,
		breed_code ASC
	);

ALTER TABLE PLZ_BREED
	ADD
		CONSTRAINT PK_PLZ_BREED
		PRIMARY KEY (
			animal_code,
			breed_code
		);

/* PLZ_ANIMAL */
CREATE TABLE PLZ_ANIMAL (
	animal_code VARCHAR2(32) NOT NULL, /* 동물코드 */
	animal_name VARCHAR2(64) /* 동물종이름 */
);

COMMENT ON TABLE PLZ_ANIMAL IS 'PLZ_ANIMAL';

COMMENT ON COLUMN PLZ_ANIMAL.animal_code IS '동물코드';

COMMENT ON COLUMN PLZ_ANIMAL.animal_name IS '동물종이름';

CREATE UNIQUE INDEX PK_PLZ_ANIMAL
	ON PLZ_ANIMAL (
		animal_code ASC
	);

ALTER TABLE PLZ_ANIMAL
	ADD
		CONSTRAINT PK_PLZ_ANIMAL
		PRIMARY KEY (
			animal_code
		);

/* VACCINATION */
CREATE TABLE PLZ_VACCINATION (
	vaccin_code VARCHAR2(32) NOT NULL, /* 백신코드 */
	vaccin_name VARCHAR2(64), /* 백신이름 */
	animal_code VARCHAR2(32), /* 동물코드 */
	vaccin_cycle NUMBER /* 접종주기 */
);

COMMENT ON TABLE PLZ_VACCINATION IS 'VACCINATION';

COMMENT ON COLUMN PLZ_VACCINATION.vaccin_code IS '백신코드';

COMMENT ON COLUMN PLZ_VACCINATION.vaccin_name IS '백신이름';

COMMENT ON COLUMN PLZ_VACCINATION.animal_code IS '동물코드';

COMMENT ON COLUMN PLZ_VACCINATION.vaccin_cycle IS '일단위로 저장함';

CREATE UNIQUE INDEX PK_PLZ_VACCINATION
	ON PLZ_VACCINATION (
		vaccin_code ASC
	);

ALTER TABLE PLZ_VACCINATION
	ADD
		CONSTRAINT PK_PLZ_VACCINATION
		PRIMARY KEY (
			vaccin_code
		);

/* TAKEVACCIN */
CREATE TABLE PLZ_TAKEVACCIN (
	user_id VARCHAR2(32) NOT NULL, /* 유저ID */
	pet_name VARCHAR2(64) NOT NULL, /* 펫이름 */
	vaccin_code VARCHAR2(32) NOT NULL, /* 백신코드 */
	take_vaccin_date DATE /* 백신맞은날짜 */
);

COMMENT ON TABLE PLZ_TAKEVACCIN IS 'TAKEVACCIN';

COMMENT ON COLUMN PLZ_TAKEVACCIN.user_id IS '유저ID';

COMMENT ON COLUMN PLZ_TAKEVACCIN.pet_name IS '펫이름';

COMMENT ON COLUMN PLZ_TAKEVACCIN.vaccin_code IS '백신코드';

COMMENT ON COLUMN PLZ_TAKEVACCIN.take_vaccin_date IS '백신맞은날짜';

CREATE UNIQUE INDEX PK_PLZ_TAKEVACCIN
	ON PLZ_TAKEVACCIN (
		user_id ASC,
		pet_name ASC,
		vaccin_code ASC
	);

ALTER TABLE PLZ_TAKEVACCIN
	ADD
		CONSTRAINT PK_PLZ_TAKEVACCIN
		PRIMARY KEY (
			user_id,
			pet_name,
			vaccin_code
		);

/* GROUP_MEETING */
CREATE TABLE PLZ_GROUP_MEETING (
	meeting_id NUMBER NOT NULL, /* 미팅ID */
	group_id NUMBER, /* 그룹ID */
	meeting_title VARCHAR2(128), /* 미팅제목 */
	meeting_description VARCHAR2(256), /* 미팅설명 */
	meeting_date DATE, /* 미팅할날짜 */
	location_x VARCHAR2(128), /* 미팅장소X좌표 */
	location_y VARCHAR2(128) /* 미팅장소Y좌표 */
);

COMMENT ON TABLE PLZ_GROUP_MEETING IS 'GROUP_MEETING';

COMMENT ON COLUMN PLZ_GROUP_MEETING.meeting_id IS '미팅ID';

COMMENT ON COLUMN PLZ_GROUP_MEETING.group_id IS '그룹ID';

COMMENT ON COLUMN PLZ_GROUP_MEETING.meeting_title IS '미팅제목';

COMMENT ON COLUMN PLZ_GROUP_MEETING.meeting_description IS '미팅설명';

COMMENT ON COLUMN PLZ_GROUP_MEETING.meeting_date IS '미팅할날짜';

COMMENT ON COLUMN PLZ_GROUP_MEETING.location_x IS '미팅장소X좌표';

COMMENT ON COLUMN PLZ_GROUP_MEETING.location_y IS '미팅장소Y좌표';

CREATE UNIQUE INDEX PK_PLZ_GROUP_MEETING
	ON PLZ_GROUP_MEETING (
		meeting_id ASC
	);

ALTER TABLE PLZ_GROUP_MEETING
	ADD
		CONSTRAINT PK_PLZ_GROUP_MEETING
		PRIMARY KEY (
			meeting_id
		);

/* BOARD */
CREATE TABLE PLZ_BOARD (
	post_id NUMBER NOT NULL, /* 글번호 */
	board_category_id VARCHAR2(1) NOT NULL, /* 보드카테고리ID */
	user_id VARCHAR2(32) NOT NULL, /* 유저ID */
	post_subject VARCHAR2(256), /* 글제목 */
	post_contents CLOB, /* 글본문 */
	creat_date DATE, /* 생성일 */
	img_id VARCHAR2(32), /* 이미지ID */
	views NUMBER, /* 조회수 */
	group_id VARCHAR2(32) /* 그룹ID */
);

COMMENT ON TABLE PLZ_BOARD IS 'BOARD';

COMMENT ON COLUMN PLZ_BOARD.post_id IS '글번호';

COMMENT ON COLUMN PLZ_BOARD.board_category_id IS '사진3, 나눔2, 통합1 or g통합4, g사진5
';

COMMENT ON COLUMN PLZ_BOARD.user_id IS '유저ID';

COMMENT ON COLUMN PLZ_BOARD.post_subject IS '글제목';

COMMENT ON COLUMN PLZ_BOARD.post_contents IS '글본문';

COMMENT ON COLUMN PLZ_BOARD.creat_date IS '생성일';

COMMENT ON COLUMN PLZ_BOARD.img_id IS '이미지ID';

COMMENT ON COLUMN PLZ_BOARD.views IS '조회수';

COMMENT ON COLUMN PLZ_BOARD.group_id IS '그룹ID';

CREATE UNIQUE INDEX PK_PLZ_BOARD
	ON PLZ_BOARD (
		post_id ASC,
		board_category_id ASC
	);

ALTER TABLE PLZ_BOARD
	ADD
		CONSTRAINT PK_PLZ_BOARD
		PRIMARY KEY (
			post_id,
			board_category_id
		);

/* USER_DETAIL */
CREATE TABLE PLZ_USER_DETAIL (
	user_id VARCHAR2(32) NOT NULL, /* 유저ID */
	tel VARCHAR2(15), /* 전화번호1 */
	gender VARCHAR2(1), /* 성별 */
	zipcode VARCHAR2(5), /* 우편번호 */
	address VARCHAR2(128), /* 주소 */
	address_detail VARCHAR2(128) /* 상세주소 */
);

COMMENT ON TABLE PLZ_USER_DETAIL IS 'USER_DETAIL';

COMMENT ON COLUMN PLZ_USER_DETAIL.user_id IS '유저ID';

COMMENT ON COLUMN PLZ_USER_DETAIL.tel IS '전화번호1';

COMMENT ON COLUMN PLZ_USER_DETAIL.gender IS '남자 : M
여자 : F';

COMMENT ON COLUMN PLZ_USER_DETAIL.zipcode IS '우편번호';

COMMENT ON COLUMN PLZ_USER_DETAIL.address IS '주소';

COMMENT ON COLUMN PLZ_USER_DETAIL.address_detail IS '상세주소';

CREATE UNIQUE INDEX PK_PLZ_USER_DETAIL
	ON PLZ_USER_DETAIL (
		user_id ASC
	);

ALTER TABLE PLZ_USER_DETAIL
	ADD
		CONSTRAINT PK_PLZ_USER_DETAIL
		PRIMARY KEY (
			user_id
		);

/* REPLY */
CREATE TABLE PLZ_REPLY (
	reply_id NUMBER NOT NULL, /* 리플ID */
	post_id NUMBER NOT NULL, /* 글번호 */
	board_category_id VARCHAR2(1) NOT NULL, /* 게시글카테고리 */
	user_id VARCHAR2(32), /* 유저ID */
	reply_contents VARCHAR2(1024), /* 리플내용 */
	creat_date DATE /* 리플생성일 */
);

COMMENT ON TABLE PLZ_REPLY IS 'REPLY';

COMMENT ON COLUMN PLZ_REPLY.reply_id IS '리플ID';

COMMENT ON COLUMN PLZ_REPLY.post_id IS '글번호';

COMMENT ON COLUMN PLZ_REPLY.board_category_id IS '게시글카테고리';

COMMENT ON COLUMN PLZ_REPLY.user_id IS '유저ID';

COMMENT ON COLUMN PLZ_REPLY.reply_contents IS '리플내용';

COMMENT ON COLUMN PLZ_REPLY.creat_date IS '리플생성일';

CREATE UNIQUE INDEX PK_PLZ_REPLY
	ON PLZ_REPLY (
		reply_id ASC
	);

ALTER TABLE PLZ_REPLY
	ADD
		CONSTRAINT PK_PLZ_REPLY
		PRIMARY KEY (
			reply_id
		);

/* BOARD_CATEGORY */
CREATE TABLE PLZ_BOARD_CATEGORY (
	board_category_id VARCHAR2(1) NOT NULL, /* 보드카테고리ID */
	board_category_name VARCHAR2(64), /* 보드카테고리이름 */
	board_category_descripton VARCHAR2(1024) /* 보드카테고리설명 */
);

COMMENT ON TABLE PLZ_BOARD_CATEGORY IS 'BOARD_CATEGORY';

COMMENT ON COLUMN PLZ_BOARD_CATEGORY.board_category_id IS '보드카테고리ID';

COMMENT ON COLUMN PLZ_BOARD_CATEGORY.board_category_name IS '보드카테고리이름';

COMMENT ON COLUMN PLZ_BOARD_CATEGORY.board_category_descripton IS '보드카테고리설명';

CREATE UNIQUE INDEX PK_PLZ_BOARD_CATEGORY
	ON PLZ_BOARD_CATEGORY (
		board_category_id ASC
	);

ALTER TABLE PLZ_BOARD_CATEGORY
	ADD
		CONSTRAINT PK_PLZ_BOARD_CATEGORY
		PRIMARY KEY (
			board_category_id
		);

/* LIKES */
CREATE TABLE PLZ_LIKES (
	user_id VARCHAR2(32) NOT NULL, /* 유저ID */
	post_id NUMBER NOT NULL, /* 글번호 */
	board_category_id VARCHAR2(1) NOT NULL /* 보드카테고리ID */
);

COMMENT ON TABLE PLZ_LIKES IS 'LIKES';

COMMENT ON COLUMN PLZ_LIKES.user_id IS '유저ID';

COMMENT ON COLUMN PLZ_LIKES.post_id IS '글번호';

COMMENT ON COLUMN PLZ_LIKES.board_category_id IS '보드카테고리ID';

CREATE UNIQUE INDEX PK_PLZ_LIKES
	ON PLZ_LIKES (
		user_id ASC,
		post_id ASC,
		board_category_id ASC
	);

ALTER TABLE PLZ_LIKES
	ADD
		CONSTRAINT PK_PLZ_LIKES
		PRIMARY KEY (
			user_id,
			post_id,
			board_category_id
		);

/* DIARY_CATEGORY */
CREATE TABLE PLZ_DIARY_CATEGORY (
	category_id VARCHAR2(32) NOT NULL, /* 카테고리ID */
	category_name VARCHAR2(64), /* 카테고리이름 */
	category_description VARCHAR2(1024) /* 카테고리설명 */
);

COMMENT ON TABLE PLZ_DIARY_CATEGORY IS 'DIARY_CATEGORY';

COMMENT ON COLUMN PLZ_DIARY_CATEGORY.category_id IS '카테고리ID';

COMMENT ON COLUMN PLZ_DIARY_CATEGORY.category_name IS '카테고리이름';

COMMENT ON COLUMN PLZ_DIARY_CATEGORY.category_description IS '카테고리설명';

CREATE UNIQUE INDEX PK_PLZ_DIARY_CATEGORY
	ON PLZ_DIARY_CATEGORY (
		category_id ASC
	);

ALTER TABLE PLZ_DIARY_CATEGORY
	ADD
		CONSTRAINT PK_PLZ_DIARY_CATEGORY
		PRIMARY KEY (
			category_id
		);

/* GROUP_TYPE */
CREATE TABLE PLZ_GROUP_TYPE (
	group_category_id VARCHAR2(1) NOT NULL, /* 그룹카테고리ID */
	group_category_name VARCHAR2(64) /* 그룹카테고리이름 */
);

COMMENT ON TABLE PLZ_GROUP_TYPE IS 'GROUP_TYPE';

COMMENT ON COLUMN PLZ_GROUP_TYPE.group_category_id IS '그룹카테고리ID';

COMMENT ON COLUMN PLZ_GROUP_TYPE.group_category_name IS '그룹카테고리이름';

CREATE UNIQUE INDEX PK_PLZ_GROUP_TYPE
	ON PLZ_GROUP_TYPE (
		group_category_id ASC
	);

ALTER TABLE PLZ_GROUP_TYPE
	ADD
		CONSTRAINT PK_PLZ_GROUP_TYPE
		PRIMARY KEY (
			group_category_id
		);

/* MEETING_MEMBER */
CREATE TABLE PLZ_MEETING_MEMBER (
	meeting_id NUMBER NOT NULL, /* 미팅ID */
	group_id NUMBER NOT NULL, /* 그룹ID */
	user_id VARCHAR2(32) NOT NULL /* 유저ID */
);

COMMENT ON TABLE PLZ_MEETING_MEMBER IS 'MEETING_MEMBER';

COMMENT ON COLUMN PLZ_MEETING_MEMBER.meeting_id IS '미팅ID';

COMMENT ON COLUMN PLZ_MEETING_MEMBER.group_id IS '그룹ID';

COMMENT ON COLUMN PLZ_MEETING_MEMBER.user_id IS '유저ID';

CREATE UNIQUE INDEX PK_PLZ_MEETING_MEMBER
	ON PLZ_MEETING_MEMBER (
		meeting_id ASC,
		group_id ASC,
		user_id ASC
	);

ALTER TABLE PLZ_MEETING_MEMBER
	ADD
		CONSTRAINT PK_PLZ_MEETING_MEMBER
		PRIMARY KEY (
			meeting_id,
			group_id,
			user_id
		);

ALTER TABLE PLZ_GROUP
	ADD
		CONSTRAINT FK_GROUP_TYPE_TO_GROUP
		FOREIGN KEY (
			group_category_id
		)
		REFERENCES PLZ_GROUP_TYPE (
			group_category_id
		);

ALTER TABLE PLZ_GROUP_MEMBER
	ADD
		CONSTRAINT FK_GROUP_TO_GROUP_MEMBER
		FOREIGN KEY (
			group_id
		)
		REFERENCES PLZ_GROUP (
			group_id
		);

ALTER TABLE PLZ_GROUP_MEMBER
	ADD
		CONSTRAINT FK_USER_TO_GROUP_MEMBER
		FOREIGN KEY (
			user_id
		)
		REFERENCES PLZ_USER (
			user_id
		);

ALTER TABLE PLZ_GROUP_CHAT
	ADD
		CONSTRAINT FK_GR_MEMBER_TO_GR_CHAT
		FOREIGN KEY (
			group_id,
			user_id
		)
		REFERENCES PLZ_GROUP_MEMBER (
			group_id,
			user_id
		);

ALTER TABLE PLZ_PET
	ADD
		CONSTRAINT FK_PLZ_USER_TO_PLZ_PET
		FOREIGN KEY (
			user_id
		)
		REFERENCES PLZ_USER (
			user_id
		);

ALTER TABLE PLZ_PET
	ADD
		CONSTRAINT FK_PLZ_BREED_TO_PLZ_PET
		FOREIGN KEY (
			animal_code,
			breed_code
		)
		REFERENCES PLZ_BREED (
			animal_code,
			breed_code
		);

ALTER TABLE PLZ_DIARY
	ADD
		CONSTRAINT FK_DIARY_CATEGORY_TO_DIARY
		FOREIGN KEY (
			category_id2
		)
		REFERENCES PLZ_DIARY_CATEGORY (
			category_id
		);

ALTER TABLE PLZ_DIARY
	ADD
		CONSTRAINT FK_PLZ_USER_TO_PLZ_DIARY
		FOREIGN KEY (
			user_id
		)
		REFERENCES PLZ_USER (
			user_id
		);

ALTER TABLE PLZ_BREED
	ADD
		CONSTRAINT FK_PLZ_ANIMAL_TO_PLZ_BREED
		FOREIGN KEY (
			animal_code
		)
		REFERENCES PLZ_ANIMAL (
			animal_code
		);

ALTER TABLE PLZ_VACCINATION
	ADD
		CONSTRAINT FK_ANIMAL_TO_VACCINATION
		FOREIGN KEY (
			animal_code
		)
		REFERENCES PLZ_ANIMAL (
			animal_code
		);

ALTER TABLE PLZ_TAKEVACCIN
	ADD
		CONSTRAINT FK_PLZ_PET_TO_PLZ_TAKEVACCIN
		FOREIGN KEY (
			user_id,
			pet_name
		)
		REFERENCES PLZ_PET (
			user_id,
			pet_name
		);

ALTER TABLE PLZ_TAKEVACCIN
	ADD
		CONSTRAINT FK_VACCINATION_TO_TAKEVACCIN
		FOREIGN KEY (
			vaccin_code
		)
		REFERENCES PLZ_VACCINATION (
			vaccin_code
		);

ALTER TABLE PLZ_GROUP_MEETING
	ADD
		CONSTRAINT FK_GR_TO_GR_MEETING
		FOREIGN KEY (
			group_id
		)
		REFERENCES PLZ_GROUP (
			group_id
		);

ALTER TABLE PLZ_BOARD
	ADD
		CONSTRAINT FK_PLZ_USER_TO_PLZ_BOARD
		FOREIGN KEY (
			user_id
		)
		REFERENCES PLZ_USER (
			user_id
		);

ALTER TABLE PLZ_BOARD
	ADD
		CONSTRAINT FK_BOARD_CATEGORY_TO_BOARD
		FOREIGN KEY (
			board_category_id
		)
		REFERENCES PLZ_BOARD_CATEGORY (
			board_category_id
		);

ALTER TABLE PLZ_USER_DETAIL
	ADD
		CONSTRAINT FK_PLZ_USER_TO_PLZ_USER_DETAIL
		FOREIGN KEY (
			user_id
		)
		REFERENCES PLZ_USER (
			user_id
		);

ALTER TABLE PLZ_REPLY
	ADD
		CONSTRAINT FK_PLZ_BOARD_TO_PLZ_REPLY
		FOREIGN KEY (
			post_id,
			board_category_id
		)
		REFERENCES PLZ_BOARD (
			post_id,
			board_category_id
		);

ALTER TABLE PLZ_REPLY
	ADD
		CONSTRAINT FK_PLZ_USER_TO_PLZ_REPLY
		FOREIGN KEY (
			user_id
		)
		REFERENCES PLZ_USER (
			user_id
		);

ALTER TABLE PLZ_LIKES
	ADD
		CONSTRAINT FK_PLZ_USER_TO_PLZ_LIKES
		FOREIGN KEY (
			user_id
		)
		REFERENCES PLZ_USER (
			user_id
		);

ALTER TABLE PLZ_LIKES
	ADD
		CONSTRAINT FK_PLZ_BOARD_TO_PLZ_LIKES
		FOREIGN KEY (
			post_id,
			board_category_id
		)
		REFERENCES PLZ_BOARD (
			post_id,
			board_category_id
		);

ALTER TABLE PLZ_MEETING_MEMBER
	ADD
		CONSTRAINT FK_GR_MT_TO_MT_MEMBER
		FOREIGN KEY (
			meeting_id
		)
		REFERENCES PLZ_GROUP_MEETING (
			meeting_id
		);

ALTER TABLE PLZ_MEETING_MEMBER
	ADD
		CONSTRAINT FK_GR_MEMBER_TO_MEETING_MEMBER
		FOREIGN KEY (
			group_id,
			user_id
		)
		REFERENCES PLZ_GROUP_MEMBER (
			group_id,
			user_id
		);
		
-- animal 코드 등록
insert into plz_animal( animal_code, animal_name)
values('417000', '개');

insert into plz_animal(animal_code, animal_name
)values('422400', '고양이');

-- 백신 데이터 입력
insert into plz_vaccination(vaccin_code, vaccin_name, animal_code, vaccin_cycle
)values('1', '종합백신', '417000', 180);

insert into plz_vaccination(vaccin_code, vaccin_name, animal_code, vaccin_cycle
)values('2', '코로나 장염 예방접종', '417000', 180);

insert into plz_vaccination(vaccin_code, vaccin_name, animal_code, vaccin_cycle
)values('3', '켄넬코프 예방접종', '417000', 180);

-- 게시판 시퀀스 추가
INSERT INTO PLZ_BOARD_CATEGORY VALUES ('1','FREE','자유');
INSERT INTO PLZ_BOARD_CATEGORY VALUES ('2','NANUM','나눔');

drop SEQUENCE SEQ_PLZ_BOARD;

CREATE SEQUENCE SEQ_PLZ_BOARD
START WITH 1
INCREMENT BY 1
MAXVALUE 9999999
MINVALUE 1
NOCYCLE;

drop sequence SEQ_PLZ_REPLY;

create SEQUENCE SEQ_PLZ_REPLY
start with 1
increment by 1
nocycle;

drop sequence SEQ_PLZ_DIARY;

CREATE SEQUENCE SEQ_PLZ_DIARY
START WITH 1
INCREMENT BY 1
MAXVALUE 9999999
MINVALUE 1
NOCYCLE;

drop sequence group_id_seq;

CREATE SEQUENCE group_id_seq
START WITH 1
INCREMENT BY 1
MAXVALUE 9999999
MINVALUE 1
NOCYCLE;

-- 그룹타입 insert
insert into plz_group_type values('1', '산책');
insert into plz_group_type values('2', '지식공유');
insert into plz_group_type values('3', '모임');
insert into plz_group_type values('4', '기타');

-- 다이어리 카테고리
insert into plz_diary_category values('1', '병원', '동물병원');
insert into plz_diary_category values('2', '식사', '반려동물 사료구입');
insert into plz_diary_category values('3', '미용', '반려동물 미용');
insert into plz_diary_category values('4', '목욕', '반려동물 목욕');
insert into plz_diary_category values('5', '산책', '산책');
insert into plz_diary_category values('6', '학교', '훈련 or 교육');
insert into plz_diary_category values('7', '용품구입', '용품구입');

drop SEQUENCE diary_number_seq;
CREATE SEQUENCE diary_number_seq
START WITH 0
INCREMENT BY 1
MAXVALUE 9999999
MINVALUE 0
NOCYCLE;

create table PLZ_DIARY_IMG (
USER_ID VARCHAR2(30),
DIARY_DATE DATE,
IMAGE_NAME VARCHAR2(30)
);

commit;


