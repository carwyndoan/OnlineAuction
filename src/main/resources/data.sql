INSERT INTO ROLE(role_id, role) VALUES (1, 'ROLE_USER');
INSERT INTO ROLE(role_id, role) VALUES (2, 'ROLE_ADMIN');

INSERT into USER(user_id, email, password, name, enable, profile_verified, registration_verified, user_type) values (1, 'user@miu.edu', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.', 'User', 1, 1, 1, 0);
INSERT into USER(user_id, email, password, name, enable, profile_verified, registration_verified, user_type) values (2, 'admin@miu.edu', '$2a$12$Pdn2Mxp1c7loJguhLtfzp.RRSHREL8Sp.bsasHnQzCLt8TSJzqiE6', 'Admin', 1, 1, 1, 0);
INSERT into USER(user_id, email, password, name, enable, profile_verified, registration_verified, user_type) values (3, 'seller@miu.edu', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.', 'Seller', 1, 1, 1, 0);
INSERT into USER(user_id, email, password, name, enable, profile_verified, registration_verified, user_type) values (4, 'buyer@miu.edu', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.', 'Buyer', 1, 1, 1, 0);

insert into user_roles(user_id, role_id) values (1, 1);
insert into user_roles(user_id, role_id) values (2, 2);

--Category
insert into Category(category_id, name, active) values(1, 'Laptop', 1);
insert into Category(category_id, name, active) values(2, 'PC', 1);
insert into Category(category_id, name, active) values(3, 'Mouse', 1);

--Product
insert into Product(product_id, name, active, status, seller_id) values(1, 'Macbook pro', 1, 1, 1);
insert into Product(product_id, name, active, status, seller_id) values(2, 'HP', 1, 1, 3);
insert into Product(product_id, name, active, status, seller_id) values(3, 'Microsoft', 1, 1, 4);

--Product_Category
insert into Category_Product(product_id, category_id) values(1, 1);
insert into Category_Product(product_id, category_id) values(2, 2);
insert into Category_Product(product_id, category_id) values(3, 3);

--Bidding
insert into Bidding(bidding_id, start_price, deposit, status, duedate, payment_duedate, product_id) values(1, 1000, 50, 1, '12-07-2020', '12-15-2020', 1);
insert into Bidding(bidding_id, start_price, deposit, status, duedate, payment_duedate, product_id) values(2, 750, 50, 1, '12-07-2020', '12-16-2020', 2);