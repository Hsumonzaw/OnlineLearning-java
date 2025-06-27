SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS examAns;
DROP TABLE IF EXISTS Courses;
DROP TABLE IF EXISTS ratings;
DROP TABLE IF EXISTS lessons;
DROP TABLE IF EXISTS languages;
DROP TABLE IF EXISTS useraccount;




/* Create Tables */

CREATE TABLE comments
(
	commentsId int NOT NULL AUTO_INCREMENT,
	lessonsId int NOT NULL,
	userAccountId int NOT NULL,
	message text NOT NULL,
	date date NOT NULL,
	modifiedDate datetime NOT NULL,
	PRIMARY KEY (commentsId)
);


CREATE TABLE Courses
(
	coursesId int NOT NULL AUTO_INCREMENT,
	userAccountId int NOT NULL,
	studentId int NOT NULL,
	languagesId int NOT NULL,
	type enum('COURSES','EXAM'),
	amount int DEFAULT 0 NOT NULL,
	receivedDate date NOT NULL,
	date datetime NOT NULL,
	modifiedDate datetime NOT NULL,
	PRIMARY KEY (coursesId)
);


CREATE TABLE examAns
(
	examId int NOT NULL AUTO_INCREMENT,
	userAccountId int NOT NULL,
	coursesId int,
	pdf varchar(200),
	examMark int NOT NULL,
	date datetime NOT NULL,
	status enum('PENDING','DONE'),
	PRIMARY KEY (examId)
);


CREATE TABLE languages
(
	languagesId int NOT NULL AUTO_INCREMENT,
	name varchar(50) NOT NULL,
	amount int DEFAULT 0 NOT NULL,
	examLink varchar(100) NOT NULL,
	examFee int NOT NULL,
	PRIMARY KEY (languagesId),
	UNIQUE (name)
);


CREATE TABLE lessons
(
	lessonsId int NOT NULL AUTO_INCREMENT,
	userAccountId int NOT NULL,
	languagesId int NOT NULL,
	youtube varchar(200),
	pdf varchar(50),
	date datetime NOT NULL,
	modifiedDate datetime NOT NULL,
	freeVideo enum('FREE','PAID') NOT NULL,
	PRIMARY KEY (lessonsId)
);


CREATE TABLE ratings
(
	ratingsId int NOT NULL AUTO_INCREMENT,
	userAccountId int NOT NULL,
	lessonsId int NOT NULL,
	rate int DEFAULT 0 NOT NULL,
	date datetime NOT NULL,
	modifiedDate datetime NOT NULL,
	PRIMARY KEY (ratingsId)
);


CREATE TABLE useraccount
(
	userAccountId int NOT NULL AUTO_INCREMENT,
	createId int,
	name varchar(50) NOT NULL,
	age int NOT NULL,
	photo varchar(50),
	status int DEFAULT 1 NOT NULL,
	userType enum('ADMIN','STAFF','STUDENT','TEACHER'),
	userName varchar(50) NOT NULL,
	password varchar(200) NOT NULL,
	encryptPassword varchar(50) NOT NULL,
	date datetime NOT NULL,
	modifiedDate datetime NOT NULL,
	startDate date NOT NULL,
	address varchar(50) NOT NULL,
	nrc varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	phonenum varchar(50) NOT NULL,
	degree varchar(50) NOT NULL,
	file varchar(50),
	PRIMARY KEY (userAccountId),
	UNIQUE (name)
);



/* Create Foreign Keys */

ALTER TABLE examAns
	ADD FOREIGN KEY (coursesId)
	REFERENCES Courses (coursesId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Courses
	ADD FOREIGN KEY (languagesId)
	REFERENCES languages (languagesId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE lessons
	ADD FOREIGN KEY (languagesId)
	REFERENCES languages (languagesId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE comments
	ADD FOREIGN KEY (lessonsId)
	REFERENCES lessons (lessonsId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ratings
	ADD FOREIGN KEY (lessonsId)
	REFERENCES lessons (lessonsId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE comments
	ADD FOREIGN KEY (userAccountId)
	REFERENCES useraccount (userAccountId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Courses
	ADD FOREIGN KEY (userAccountId)
	REFERENCES useraccount (userAccountId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Courses
	ADD FOREIGN KEY (studentId)
	REFERENCES useraccount (userAccountId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE examAns
	ADD FOREIGN KEY (userAccountId)
	REFERENCES useraccount (userAccountId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE lessons
	ADD FOREIGN KEY (userAccountId)
	REFERENCES useraccount (userAccountId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ratings
	ADD FOREIGN KEY (userAccountId)
	REFERENCES useraccount (userAccountId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE useraccount
	ADD FOREIGN KEY (createId)
	REFERENCES useraccount (userAccountId)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



<<<<<<< HEAD
=======
useraccount
>>>>>>> 881f4bf8362aae8b4c16c599f78965c665d13bd4
