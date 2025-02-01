INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO role (name) VALUES ('ROLE_GUEST');

INSERT INTO member (id, email, name, nickname, password) VALUES (0x7F00000194C11BE28194C18E43250000, 'admin1@test.com', 'admin1', 'admin1', '$2a$10$U969wbxcUTwMdgcrOBbcWeOvdYK9.6wIm.ZTtPJsu3SMb3baUGapu');
INSERT INTO member (id, email, name, nickname, password) VALUES (0x7F00000194C111E98194C19202AC0000, 'user1@test.com', 'user1', 'user1', '$2a$10$q4NxaAF7MCX9LooB9Heds.j6n21SQp4SmZyBtVc0pWKUTIXdiO/Je');
INSERT INTO member (id, email, name, nickname, password) VALUES (0x7F00000194C111E98194C19220C30001, 'user2@test.com', 'user2', 'user2', '$2a$10$ru5QhQlSuvvbzlJqkCN.K.jDixbQCsL5KgTLUJyaE1eAXnbplCV2i');
INSERT INTO member (id, email, name, nickname, password) VALUES (0x7F00000194C111E98194C192622F0002, 'guest1@test.com', 'guest1', 'guest1', '$2a$10$ByWqEJwFc3bvuiw/tSA4vuVnN9xfrESab1WhxCi3euXum8L/XUjf.');

INSERT INTO member_role (id, role_id, member_id) VALUES (1, 1, 0x7F00000194C11BE28194C18E43250000);
INSERT INTO member_role (id, role_id, member_id) VALUES (2, 2, 0x7F00000194C111E98194C19202AC0000);
INSERT INTO member_role (id, role_id, member_id) VALUES (3, 2, 0x7F00000194C111E98194C19220C30001);
INSERT INTO member_role (id, role_id, member_id) VALUES (4, 3, 0x7F00000194C111E98194C192622F0002);


