INSERT INTO ROLE(id, role) VALUES (1, 'ROLE_USER');
INSERT INTO ROLE(id, role) VALUES (2, 'ROLE_ADMIN');

INSERT into USER (id, email, password, name, enable) values (1, 'user@miu.edu', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.', 'User', 1);
INSERT into USER (id, email, password, name, enable) values (2, 'admin@miu.edu', '$2a$12$Pdn2Mxp1c7loJguhLtfzp.RRSHREL8Sp.bsasHnQzCLt8TSJzqiE6', 'Admin', 1);

insert into user_roles(user_id, role_id) values (1, 1);
insert into user_roles(user_id, role_id) values (2, 2);
