INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_USER');

INSERT INTO member (role_id, id, email, name, nickname, password) VALUES (2, 0x7F00000194B214E88194B25F10ED0005, 'user1@test.com', 'user1', '사용자1', '$2a$10$vl0JFolzia0zUY1Z89RgCevJ4xsIIrl2yum1NudbH1m5GOkLkegs6');
INSERT INTO member (role_id, id, email, name, nickname, password) VALUES (2, 0x7F00000194B214E88194B25F2CBC0006, 'user2@test.com', 'user2', '사용자2', '$2a$10$W9bP0tyi5kN2XCn1eiw2L.CQZtqWu7J1V8ip.GDpIWimQXeV7do.y');
INSERT INTO member (role_id, id, email, name, nickname, password) VALUES (2, 0x7F00000194B214E88194B25F4DA20007, 'user3@test.com', 'user3', '사용자3', '$2a$10$lqdDsJiBZ3Une2Sf9z.JeOY5wYW475vHfdeNJr1x79NQ2JCx6Z5XK');
