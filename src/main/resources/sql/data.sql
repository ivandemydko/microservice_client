use microservice;

INSERT INTO  client_user  (USERNAME, PASSWORD) VALUES ('admin', '$2a$11$Pcw1ZM7cS7neZo5yqa4OfeWxAh04rF5zy6SU6RJ5v3Y4BpA.7gfum');  -- password: admin
INSERT INTO AUTH_USER_GROUP (USER_ID, AUTH_GROUP) VALUES(0, 'ADMIN');
INSERT INTO AUTH_USER_GROUP (USER_ID, AUTH_GROUP) VALUES(0, 'USER');

INSERT INTO  client_user  (USERNAME, PASSWORD) VALUES ('u1', '$2a$11$O7qRaavdFwx9YgNw0X5.R.5e9MfQfpH2bDgSsSqPVxj/xFLleLRTO');  -- password: u1
INSERT INTO AUTH_USER_GROUP (USER_ID, AUTH_GROUP) VALUES(1, 'USER');