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

insert into product(product_id, caption, active, description, image_path, name, status, seller_id) values (1, 'Land Cruiser 200 (1)', 1, 'The 200 Series is loaded with refined creature comforts, like heated and ventilated front seats, leather-wrapped surfaces and four-zone automatic climate control','200.jpg', 'Seller_Product_1', 1, 3);
insert into product(product_id, caption, active, description, image_path, name, status, seller_id) values (2, 'Honda Civic (1)', 1, 'The Honda Civic is a line of cars manufactured by Honda. Originally a subcompact, the Civic has gone through several generational changes, becoming both .','civic.jpg', 'Seller_Product_2', 1, 3);
insert into product(product_id, caption, active, description, image_path, name, status, seller_id) values (3, 'Lexus RX 450 (1)', 1, 'The Lexus RX  is a mid-size luxury crossover SUV sold since 1998 by Lexus, a luxury division of Toyota. Originally released in its home market of Japan in late 1997 as the Toyota Harrier, export sales began in March 1998 as the Lexus RX','lx.jpg', 'Seller_Product_3', 1, 3);
insert into product(product_id, caption, active, description, image_path, name, status, seller_id) values (4, 'Toyota Alphard (1)', 1, 'The Toyota Alphard  is a luxury minivan produced by the Japanese automaker Toyota since 2002. It is available as a seven- or eight-seater with petrol and hybrid engine','alphard.jpg', 'Seller_Product_4', 1, 3);
insert into product(product_id, caption, active, description, image_path, name, status, seller_id) values (5, 'Toyota Corolla (1)', 1, 'The Toyota Corolla is a line of subcompact and compact cars manufactured by Toyota. Introduced in 1966, the Corolla was the best-selling car worldwide by','corolla.jpg', 'Seller_Product_5', 1, 3);

insert into product(product_id, caption, active, description, image_path, name, status, seller_id) values (6, 'Land Cruiser 200 (2)', 1, 'The 200 Series is loaded with refined creature comforts, like heated and ventilated front seats, leather-wrapped surfaces and four-zone automatic climate control','200.jpg', 'Seller_Product_1', 1, 3);
insert into product(product_id, caption, active, description, image_path, name, status, seller_id) values (7, 'Honda Civic (2)', 1, 'The Honda Civic is a line of cars manufactured by Honda. Originally a subcompact, the Civic has gone through several generational changes, becoming both .','civic.jpg', 'Seller_Product_2', 1, 3);
insert into product(product_id, caption, active, description, image_path, name, status, seller_id) values (8, 'Lexus RX 450 (2)', 1, 'The Lexus RX  is a mid-size luxury crossover SUV sold since 1998 by Lexus, a luxury division of Toyota. Originally released in its home market of Japan in late 1997 as the Toyota Harrier, export sales began in March 1998 as the Lexus RX','lx.jpg', 'Seller_Product_3', 1, 3);
insert into product(product_id, caption, active, description, image_path, name, status, seller_id) values (9, 'Toyota Alphard (2)', 1, 'The Toyota Alphard  is a luxury minivan produced by the Japanese automaker Toyota since 2002. It is available as a seven- or eight-seater with petrol and hybrid engine','alphard.jpg', 'Seller_Product_4', 1, 3);
insert into product(product_id, caption, active, description, image_path, name, status, seller_id) values (10, 'Toyota Corolla (2)', 1, 'The Toyota Corolla is a line of subcompact and compact cars manufactured by Toyota. Introduced in 1966, the Corolla was the best-selling car worldwide by','corolla.jpg', 'Seller_Product_5', 1, 3);


insert into product(product_id, caption, active, description, image_path, name, status, seller_id) values (11, 'Land Cruiser 200 (3)', 1, 'The 200 Series is loaded with refined creature comforts, like heated and ventilated front seats, leather-wrapped surfaces and four-zone automatic climate control','200.jpg', 'Seller_Product_1', 1, 3);
insert into product(product_id, caption, active, description, image_path, name, status, seller_id) values (12, 'Honda Civic (3)', 1, 'The Honda Civic is a line of cars manufactured by Honda. Originally a subcompact, the Civic has gone through several generational changes, becoming both .','civic.jpg', 'Seller_Product_2', 1, 3);
insert into product(product_id, caption, active, description, image_path, name, status, seller_id) values (13, 'Lexus RX 450 (3)', 1, 'The Lexus RX  is a mid-size luxury crossover SUV sold since 1998 by Lexus, a luxury division of Toyota. Originally released in its home market of Japan in late 1997 as the Toyota Harrier, export sales began in March 1998 as the Lexus RX','lx.jpg', 'Seller_Product_3', 1, 3);
insert into product(product_id, caption, active, description, image_path, name, status, seller_id) values (14, 'Toyota Alphard (3)', 1, 'The Toyota Alphard  is a luxury minivan produced by the Japanese automaker Toyota since 2002. It is available as a seven- or eight-seater with petrol and hybrid engine','alphard.jpg', 'Seller_Product_4', 1, 3);
insert into product(product_id, caption, active, description, image_path, name, status, seller_id) values (15, 'Toyota Corolla (3)', 1, 'The Toyota Corolla is a line of subcompact and compact cars manufactured by Toyota. Introduced in 1966, the Corolla was the best-selling car worldwide by','corolla.jpg', 'Seller_Product_5', 1, 3);



insert into bidding(bidding_id, deposit, description, start_price, finalprice, startdate, duedate, payment_duedate, status, product_id, winner_id) values
				   (1, 2000, 'Macbook Pro 2020', 500, 1500, '2020-11-25', '2020-12-25', '2020-12-30', 1, 1, 1);
insert into bidding(bidding_id, deposit, description, start_price, finalprice, startdate, duedate, payment_duedate, status, product_id, winner_id) values
				   (2, 20000, 'Dimond', 2000, 20000,'2020-11-25', '2020-12-25', '2020-12-30', 1, 2, 1);
insert into bidding(bidding_id, deposit, description, start_price, finalprice, startdate, duedate, payment_duedate, status, product_id, winner_id) values
				   (3, 1200, 'Apple Watch 2020', 120, 1500, '2020-11-25','2020-12-25', '2020-12-30', 1, 3, 2);
insert into bidding(bidding_id, deposit, description, start_price, finalprice, startdate, duedate, payment_duedate, status, product_id) values
				   (4, 200000, 'Tesla', 50000, 0, '2020-9-25','2020-12-25', '2020-12-30', 0, 4);
insert into bidding(bidding_id, deposit, description, start_price, finalprice, startdate, duedate, payment_duedate, status, product_id) values
				   (5, 10000, 'Final Worldcup Ball', 120000, 500000, '2020-10-25', '2020-12-25', '2020-12-30', 1, 2);

insert into payment(payment_id, deposit, deposit_date, bidding_id, user_payment_id) values (1, 2000, '2020-12-07', 1, 1);
insert into payment(payment_id, deposit, deposit_date, bidding_id, user_payment_id) values (2, 20000, '2020-12-07', 2, 1);
insert into payment(payment_id, deposit, deposit_date, bidding_id, user_payment_id) values (3, 1200, '2020-12-07', 3, 2);
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