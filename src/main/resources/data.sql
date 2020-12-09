INSERT INTO ROLE(role_id, role) VALUES (1, 'ROLE_USER');
INSERT INTO ROLE(role_id, role) VALUES (2, 'ROLE_ADMIN');
INSERT INTO ROLE(role_id, role) VALUES (3, 'ROLE_SELLER');
INSERT INTO ROLE(role_id, role) VALUES (4, 'ROLE_CUSTOMER');

INSERT into USER(user_id, email, password, name, enable, profile_verified, registration_verified, user_type) values (1, 'user@miu.edu', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.', 'User', 1, 1, 1, 0);
INSERT into USER(user_id, email, password, name, enable, profile_verified, registration_verified, user_type) values (2, 'admin@miu.edu', '$2a$12$Pdn2Mxp1c7loJguhLtfzp.RRSHREL8Sp.bsasHnQzCLt8TSJzqiE6', 'Admin', 1, 1, 1, 1);
INSERT into USER(user_id, email, password, name, enable, profile_verified, registration_verified, user_type) values (3, 'seller@miu.edu', '$2a$12$Pdn2Mxp1c7loJguhLtfzp.RRSHREL8Sp.bsasHnQzCLt8TSJzqiE6', 'Seller', 1, 1, 1, 2);
INSERT into USER(user_id, email, password, name, enable, profile_verified, registration_verified, user_type) values (4, 'customer1@miu.edu', '$2a$12$Pdn2Mxp1c7loJguhLtfzp.RRSHREL8Sp.bsasHnQzCLt8TSJzqiE6', 'Seller', 1, 1, 1, 3);
INSERT into USER(user_id, email, password, name, enable, profile_verified, registration_verified, user_type) values (5, 'customer2@miu.edu', '$2a$12$Pdn2Mxp1c7loJguhLtfzp.RRSHREL8Sp.bsasHnQzCLt8TSJzqiE6', 'Seller', 1, 1, 1, 3);


insert into user_roles(user_id, role_id) values (1, 1);
insert into user_roles(user_id, role_id) values (2, 2);
insert into user_roles(user_id, role_id) values (3, 3);
insert into user_roles(user_id, role_id) values (4, 4);
insert into user_roles(user_id, role_id) values (5, 4);


insert into category(category_id, name, active) values (1, 'Panasonic', 1);
insert into category(category_id, name, active) values (2, 'Apple', 1);
insert into category(category_id, name, active) values (3, 'LTT company', 1);
insert into category(category_id, name, active) values (4, 'Computer shop', 1);
insert into category(category_id, name, active) values (5, 'Laptop shop', 1);
insert into category(category_id, name, active) values (6, 'Watch shop', 1);
insert into category(category_id, name, active) values (7, 'Shoe shop', 1);

insert into product(product_id, active, name, status, seller_id) values (1, 1, 'Seller_Product_1', 1, 3);
insert into product(product_id, active, name, status, seller_id) values (2, 1, 'Seller_Product_2', 1, 3);
insert into product(product_id, active, name, status, seller_id) values (3, 1, 'Seller_Product_3', 1, 3);
insert into product(product_id, active, name, status, seller_id) values (4, 1, 'Seller_Product_4', 1, 3);
insert into product(product_id, active, name, status, seller_id) values (5, 1, 'Seller_Product_5', 1, 3);

insert into bidding(bidding_id, deposit, description, start_price, finalprice, duedate, payment_duedate, status, product_id, winner_id) values
				   (1, 2000, 'Macbook Pro 2020', 500, 1500, '2020-12-07', '2020-12-30', 2, 1, 1);
insert into bidding(bidding_id, deposit, description, start_price, finalprice, duedate, payment_duedate, status, product_id, winner_id) values
				   (2, 20000, 'Dimond', 2000, 20000, '2020-12-07', '2020-12-30', 2, 2, 1);
insert into bidding(bidding_id, deposit, description, start_price, finalprice, duedate, payment_duedate, status, product_id, winner_id) values
				   (3, 1200, 'Apple Watch 2020', 120, 1500, '2020-12-07', '2020-12-30', 2, 3, 2);
insert into bidding(bidding_id, deposit, description, start_price, finalprice, duedate, payment_duedate, status, product_id) values
				   (4, 200000, 'Tesla', 50000, 0, '2020-12-07', '2020-12-30', 0, 4);
insert into bidding(bidding_id, deposit, description, start_price, finalprice, duedate, payment_duedate, status, product_id) values
				   (5, 10000, 'Final Worldcup Ball', 120000, 500000, '2020-12-07', '2020-12-30', 1, 2);

insert into payment(payment_id, deposit, deposit_date, bidding_id) values (1, 2000, '2020-12-07', 1);
insert into payment(payment_id, deposit, deposit_date, bidding_id) values (2, 20000, '2020-12-07', 2);
insert into payment(payment_id, deposit, deposit_date, bidding_id) values (3, 1200, '2020-12-07', 3);
insert into payment(payment_id, deposit, deposit_date, bidding_id) values (4, 200000, '2020-12-07', 4);
insert into payment(payment_id, deposit, deposit_date, bidding_id) values (5, 10000, '2020-12-07', 5);

insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (1, 10000, '2019-12-07 09:09', 1, 1);
insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (2, 15000, '2020-12-07 09:09', 1, 1);
insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (3, 20000, '2020-07-07 09:09', 1, 1);

insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (4, 10000, '2020-12-07 09:09', 2, 1);
insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (5, 15000, '2020-12-07 09:09', 2, 1);
insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (6, 20000, '2020-11-07 09:09', 2, 1);

insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (7, 10000, '2020-12-07 09:09', 3, 1);
insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (8, 15000, '2020-12-07 09:09', 3, 1);
insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (9, 20000, '2020-12-07 09:09', 3, 1);

insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (10, 10000, '2020-12-07 09:09', 4, 1);
insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (11, 15000, '2020-12-07 09:09', 4, 1);
insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (12, 20000, '2020-12-07 09:09', 4, 1);

insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (13, 10000, '2020-12-07 09:09', 5, 1);
insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (14, 15000, '2020-12-07 09:09', 5, 1);
insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (15, 20000, '2020-12-07 09:09', 5, 1);


insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (16, 10000, '2020-12-07 09:09', 1, 2);
insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (17, 15000, '2020-12-07 09:09', 1, 2);
insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (18, 20000, '2020-12-07 09:09', 1, 2);

insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (19, 10000, '2020-12-07 09:09', 2, 2);
insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (20, 15000, '2020-12-07 09:09', 2, 2);
insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (21, 20000, '2020-12-07 09:09', 2, 2);

insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (21, 10000, '2020-12-07 09:09', 3, 2);
insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (22, 15000, '2020-12-07 09:09', 3, 2);
insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (23, 20000, '2020-12-07 09:09', 3, 2);

insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (24, 10000, '2020-12-07 09:09', 4, 2);
insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (25, 15000, '2020-12-07 09:09', 4, 2);
insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (26, 20000, '2020-12-07 09:09', 4, 2);

insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (27, 10000, '2020-12-07 09:09', 5, 2);
insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (28, 15000, '2020-12-07 09:09', 5, 2);
insert into bidding_activities(bidding_activities_id, amount, bidding_date, bidding_id, user_bidding_id) values
                              (29, 20000, '2020-12-07 09:09', 5, 2);