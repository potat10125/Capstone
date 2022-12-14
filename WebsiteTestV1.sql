DROP DATABASE IF EXISTS Website;
CREATE DATABASE Website;
USE Website;

CREATE TABLE authors(
	username VARCHAR(20) PRIMARY KEY,
    display_name VARCHAR(30) NOT NULL,
    pass VARCHAR(15) NOT NULL,
    permissions BOOLEAN NOT NULL DEFAULT FALSE
    );

CREATE TABLE blog(
	id INT PRIMARY KEY AUTO_INCREMENT,
    content LONGTEXT NOT NULL,
    title VARCHAR(50) NOT NULL,
	author VARCHAR(20) NOT NULL,
    publish_date DateTime NOT NULL DEFAULT NOW(),
    approved BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_author
		FOREIGN KEY (author) REFERENCES authors(username) ON DELETE CASCADE
);

CREATE TABLE hashtag(
	tag VARCHAR(30),
    blogId INT,
    CONSTRAINT 
		PRIMARY KEY (tag, blogId),
	CONSTRAINT
		FOREIGN KEY (blogID) REFERENCES blog(id) ON DELETE CASCADE
);

INSERT INTO authors VALUES("TheBoss", "Mr. Boss", "password123", TRUE);