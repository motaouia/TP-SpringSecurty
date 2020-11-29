INSERT INTO users (username, password, enabled) VALUES ('myAdmin', '123', true);
INSERT INTO users (username, password, enabled) VALUES('myUser', '123', true);

INSERT INTO authorities (username, authority) VALUES ('myUser', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('myAdmin', 'ROLE_ADMIN');