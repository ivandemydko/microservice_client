drop database if exists microservice;

create database microservice;

use microservice;

CREATE TABLE client_user (
 id bigint auto_increment primary key,
username varchar(100),
password varchar(256),
access_token varchar(100) NULL,
access_token_validity datetime NULL,
refresh_token varchar(100) NULL
);

CREATE TABLE AUTH_USER_GROUP (
  AUTH_USER_GROUP_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  USER_ID bigint NOT NULL,
  AUTH_GROUP VARCHAR(128) NOT NULL,
  CONSTRAINT USER_AUTH_USER_GROUP_FK FOREIGN KEY(USER_ID) REFERENCES client_user(id),
  UNIQUE (USER_ID, AUTH_GROUP)
);
