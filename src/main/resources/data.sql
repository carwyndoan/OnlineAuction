INSERT INTO ROLE(role_id, role) VALUES (1, 'ROLE_USER');
INSERT INTO ROLE(role_id, role) VALUES (2, 'ROLE_ADMIN');
INSERT INTO ROLE(role_id, role) VALUES (3, 'ROLE_SELLER');
INSERT INTO ROLE(role_id, role) VALUES (4, 'ROLE_CUSTOMER');

INSERT into USER(user_id, email, password, name, enable, profile_verified, registration_verified, user_type, street, city, state, zipcode) values (1, 'user@miu.edu', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.', 'User', 1, 1, 1, 0, 'Golden Dome Way', 'Fairfield', 'IA', '52556');
INSERT into USER(user_id, email, password, name, enable, profile_verified, registration_verified, user_type) values (2, 'admin@miu.edu', '$2a$12$Pdn2Mxp1c7loJguhLtfzp.RRSHREL8Sp.bsasHnQzCLt8TSJzqiE6', 'Admin', 1, 1, 1, 1);
INSERT into USER(user_id, email, password, name, enable, profile_verified, registration_verified, user_type) values (3, 'seller@miu.edu', '$2a$12$Pdn2Mxp1c7loJguhLtfzp.RRSHREL8Sp.bsasHnQzCLt8TSJzqiE6', 'Seller', 1, 1, 1, 2);
INSERT into USER(user_id, email, password, name, enable, profile_verified, registration_verified, user_type) values (4, 'customer1@miu.edu', '$2a$12$Pdn2Mxp1c7loJguhLtfzp.RRSHREL8Sp.bsasHnQzCLt8TSJzqiE6', 'Seller', 1, 1, 1, 3);
INSERT into USER(user_id, email, password, name, enable, profile_verified, registration_verified, user_type) values (5, 'customer2@miu.edu', '$2a$12$Pdn2Mxp1c7loJguhLtfzp.RRSHREL8Sp.bsasHnQzCLt8TSJzqiE6', 'Seller', 1, 1, 1, 3);

insert into user_roles(user_id, role_id) values (1, 1);
insert into user_roles(user_id, role_id) values (2, 2);
insert into user_roles(user_id, role_id) values (3, 3);
insert into user_roles(user_id, role_id) values (4, 4);
insert into user_roles(user_id, role_id) values (5, 4);

insert into category(category_id, name, active) values (1, 'Car', 1);
insert into category(category_id, name, active) values (2, 'Computer', 1);
insert into category(category_id, name, active) values (3, 'Electronic', 1);

insert into product(product_id, caption, active, description, image_path, name, status, seller_id, price, upload_Date) values (1, 'Land Cruiser 200', 1, 'The 200 Series is loaded with refined creature comforts, like heated and ventilated front seats, leather-wrapped surfaces and four-zone automatic climate control','200.jpg', 'Seller_Product_1', 1, 3, 100000, '2020-11-20');
insert into product(product_id, caption, active, description, image_path, name, status, seller_id, price, upload_Date) values (2, 'Honda Civic', 1, 'The Honda Civic is a line of cars manufactured by Honda. Originally a subcompact, the Civic has gone through several generational changes, becoming both .','civic.jpg', 'Seller_Product_2', 1, 3, 28000, '2020-11-22');
insert into product(product_id, caption, active, description, image_path, name, status, seller_id, price, upload_Date) values (3, 'Lexus RX 450', 1, 'The Lexus RX  is a mid-size luxury crossover SUV sold since 1998 by Lexus, a luxury division of Toyota. Originally released in its home market of Japan in late 1997 as the Toyota Harrier, export sales began in March 1998 as the Lexus RX','lx.jpg', 'Seller_Product_3', 1, 3, 35000,'2020-11-23');
insert into product(product_id, caption, active, description, image_path, name, status, seller_id, price, upload_Date) values (4, 'Toyota Alphard', 1, 'The Toyota Alphard  is a luxury minivan produced by the Japanese automaker Toyota since 2002. It is available as a seven- or eight-seater with petrol and hybrid engine','alphard.jpg', 'Seller_Product_4', 1, 3,30000, '2020-11-25');
insert into product(product_id, caption, active, description, image_path, name, status, seller_id, price, upload_Date) values (5, 'Toyota Corolla', 1, 'The Toyota Corolla is a line of subcompact and compact cars manufactured by Toyota. Introduced in 1966, the Corolla was the best-selling car worldwide by','corolla.jpg', 'Seller_Product_5', 1, 3, 18000, '2020-12-01');

insert into product(product_id, caption, active, description, image_path, name, status, seller_id, price, upload_Date) values (6, 'Apple macbook 2020', 1, 'The Apple macbook 2020 is a brand of Macintosh laptop computers designed and marketed by Apple Inc. that use Apples macOS operating system. Two different lines simply named "MacBook" existed from 2006 to 2012 and 2015 to 2019.','macbook.jpg', 'Seller_Product_1', 1, 3, 2000, '2020-12-03');
insert into product(product_id, caption, active, description, image_path, name, status, seller_id, price, upload_Date) values (7, 'ASUS ROG', 1, 'ASUS ROG The body blends clean lines with refined design to make an impression without demanding attention. Brush strokes slash across the lid and echo in cooling vents that rake across the deck below the display','asus.jpg', 'Seller_Product_2', 1, 3, 1900, '2020-12-04');
insert into product(product_id, caption, active, description, image_path, name, status, seller_id, price, upload_Date) values (8, 'Microsoft Surface',  1, 'Microsoft Store offers Microsoft Surface 60-day returns on Surface products plus free expert help, digital workshops, and remote learning opportunities. Our online associates will provide free personal assistance ','surface2.jpg', 'Seller_Product_3', 1, 3, 1800, '2020-12-05');
insert into product(product_id, caption, active, description, image_path, name, status, seller_id, price, upload_Date) values (9, 'Samsung Laundry', 1, 'We know Samsung Laundry washing machines are a big purchase. And for an appliance thats supposed to make daily chores a bit easier, it can be a pain to choose the right one that’ll meet all your needs.','laundry.jpg', 'Seller_Product_4', 1, 3, 3000, '2020-12-06');
insert into product(product_id, caption, active, description, image_path, name, status, seller_id, price, upload_Date) values (10, 'HiFi music', 1, 'HiFi music fidelity is a term used by listeners, audiophiles and home audio enthusiasts to refer to ... Classical music fans, who were opinion leaders in the audio market, quickly adopted LPs because, unlike with older records, most classical works','m1.jpg', 'Seller_Product_5', 1, 3, 1200, '2020-12-08');
insert into product(product_id, caption, active, description, image_path, name, status, seller_id, price, upload_Date) values (11, 'Microwave oven', 1, 'Elegant design meets impressive performance with the newest lineup of LG microwave ovens. ','oven1.jpg', 'Seller_Product_1', 1, 3, 200, '2020-12-12');
insert into product(product_id, caption, active, description, image_path, name, status, seller_id, price, upload_Date) values (12, 'Insigna 55 inch tv', 1, 'Insigna 55 inch tv is Best Buys signature house brand for televisions. Insignia Systems introduced The Like Machine to their lineup of in-store marketing solutions. The Like Machine allows consumers to express their opinions on ','tv.jpg', 'Seller_Product_2', 1, 3, 700, '2020-12-13');

insert into category_product(product_id,category_id) values(1,1);
insert into category_product(product_id,category_id) values(2,1);
insert into category_product(product_id,category_id) values(3,1);
insert into category_product(product_id,category_id) values(4,1);
insert into category_product(product_id,category_id) values(5,1);

insert into category_product(product_id,category_id) values(6,2);
insert into category_product(product_id,category_id) values(7,2);
insert into category_product(product_id,category_id) values(8,2);

insert into category_product(product_id,category_id) values(9,3);
insert into category_product(product_id,category_id) values(10,3);
insert into category_product(product_id,category_id) values(11,3);
insert into category_product(product_id,category_id) values(12,3);

insert into photo(photo_id, image_url, product_id) values (1,'200_1.jpg', 1);
insert into photo(photo_id, image_url, product_id) values (2,'200_2.jpg', 1);
insert into photo(photo_id, image_url, product_id) values (3,'200_3.jpg', 1);
insert into photo(photo_id, image_url, product_id) values (4,'200_4.jpg', 1);

insert into photo(photo_id, image_url, product_id) values (5,'civic_1.jpg', 2);
insert into photo(photo_id, image_url, product_id) values (6,'civic_2.jpg', 2);
insert into photo(photo_id, image_url, product_id) values (7,'civic_3.jpg', 2);
insert into photo(photo_id, image_url, product_id) values (8,'civic_4.jpg', 2);

insert into photo(photo_id, image_url, product_id) values (9, 'rx_1.jpg', 3);
insert into photo(photo_id, image_url, product_id) values (10,'rx_2.jpg', 3);
insert into photo(photo_id, image_url, product_id) values (11,'rx_3.jpg', 3);
insert into photo(photo_id, image_url, product_id) values (12,'rx_4.jpg', 3);


insert into photo(photo_id, image_url, product_id) values (13,'alphard_1.jpg', 4);
insert into photo(photo_id, image_url, product_id) values (14,'alphard_2.jpg', 4);
insert into photo(photo_id, image_url, product_id) values (15,'alphard_3.jpg', 4);
insert into photo(photo_id, image_url, product_id) values (16,'alphard_4.jpg', 4);


insert into photo(photo_id, image_url, product_id) values (17,'corolla_1.jpg', 5);
insert into photo(photo_id, image_url, product_id) values (18,'corolla_2.jpg', 5);
insert into photo(photo_id, image_url, product_id) values (19,'corolla_3.jpg', 5);
insert into photo(photo_id, image_url, product_id) values (20,'corolla_4.jpg', 5);

insert into photo(photo_id, image_url, product_id) values (21,'macbook.jpg', 6);
insert into photo(photo_id, image_url, product_id) values (22,'macbook2.jpg', 6);
insert into photo(photo_id, image_url, product_id) values (23,'macbook3.jpg', 6);
insert into photo(photo_id, image_url, product_id) values (24,'macbook4.jpg', 6);

insert into photo(photo_id, image_url, product_id) values (25,'asus.jpg', 7);
insert into photo(photo_id, image_url, product_id) values (26,'asus2.jpg', 7);
insert into photo(photo_id, image_url, product_id) values (27,'asus3.jpg', 7);
insert into photo(photo_id, image_url, product_id) values (28,'asus4.jpg', 7);

insert into photo(photo_id, image_url, product_id) values (29,'surface.jpg', 8);
insert into photo(photo_id, image_url, product_id) values (30,'surface2.jpg', 8);
insert into photo(photo_id, image_url, product_id) values (31,'surface3.jpg', 8);
insert into photo(photo_id, image_url, product_id) values (32,'surface4.jpg', 8);

insert into photo(photo_id, image_url, product_id) values (33,'laundry.jpg', 9);
insert into photo(photo_id, image_url, product_id) values (34,'laundry2.jpg', 9);
insert into photo(photo_id, image_url, product_id) values (35,'laundry3.jpg', 9);
insert into photo(photo_id, image_url, product_id) values (36,'laundry4.jpg', 9);

insert into photo(photo_id, image_url, product_id) values (37,'m1.jpg', 10);
insert into photo(photo_id, image_url, product_id) values (38,'m2.jpg', 10);

insert into photo(photo_id, image_url, product_id) values (39,'oven1.jpg', 11);
insert into photo(photo_id, image_url, product_id) values (40,'oven2.jpg', 11);
insert into photo(photo_id, image_url, product_id) values (41,'oven3.jpg', 11);
insert into photo(photo_id, image_url, product_id) values (42,'oven4.jpg', 11);

insert into photo(photo_id, image_url, product_id) values (43,'tv.jpg', 12);
insert into photo(photo_id, image_url, product_id) values (44,'tv2.jpg', 12);
insert into photo(photo_id, image_url, product_id) values (45,'tv3.jpg', 12);

insert into bidding(bidding_id, deposit, description, start_price, finalprice, startdate, duedate, payment_duedate, status, product_id, winner_id) values
				   (1, 200, 'Macbook Pro 2020', 2000, 3000, '2020-11-25', '2020-12-25', '2020-12-30', 1, 1, 1);
insert into bidding(bidding_id, deposit, description, start_price, finalprice, startdate, duedate, payment_duedate, status, product_id, winner_id) values
				   (2, 10000, 'Dimond', 100000, 150000,'2020-11-25', '2020-12-25', '2020-12-30', 1, 2, 1);
insert into bidding(bidding_id, deposit, description, start_price, finalprice, startdate, duedate, payment_duedate, status, product_id, winner_id) values
				   (3, 55, 'Apple Watch 2020', 550, 700, '2020-11-25','2020-12-25', '2020-12-30', 1, 3, 2);
insert into bidding(bidding_id, deposit, description, start_price, finalprice, startdate, duedate, payment_duedate, status, product_id) values
				   (4, 5000, 'Tesla', 50000, 85000, '2020-9-25','2020-12-25', '2020-12-30', 0, 4);
insert into bidding(bidding_id, deposit, description, start_price, finalprice, startdate, duedate, payment_duedate, status, product_id) values
				   (5, 12000, 'Final Worldcup Ball', 120000, 1000000, '2020-10-25', '2020-12-25', '2020-12-30', 1, 2);
insert into bidding(bidding_id, deposit, description, start_price, finalprice, startdate, duedate, payment_duedate, status, product_id) values
				   (6, 10000, 'Dimond', 100000, 150000,'2020-11-25', '2020-12-25', '2020-12-30', 1, 10);
insert into bidding(bidding_id, deposit, description, start_price, finalprice, startdate, duedate, payment_duedate, status, product_id) values
				   (7, 100000, 'The car owned by Elon Musk', 1000000, 1500000,'2020-10-25', '2020-12-25', '2020-12-30', 1, 11);

insert into payment(payment_id, deposit, deposit_date, bidding_id, user_payment_id) values (1, 200, '2020-12-07', 1, 1);
insert into payment(payment_id, deposit, deposit_date, bidding_id, user_payment_id) values (2, 10000, '2020-12-07', 2, 1);
insert into payment(payment_id, deposit, deposit_date, bidding_id, user_payment_id) values (3, 55, '2020-12-07', 3, 2);
insert into payment(payment_id, deposit, deposit_date, bidding_id, user_payment_id) values (4, 5000, '2020-12-07', 4, 1);
insert into payment(payment_id, deposit, deposit_date, bidding_id, user_payment_id) values (5, 12000, '2020-12-07', 5, 3);
insert into payment(payment_id, deposit, deposit_date, bidding_id, user_payment_id) values (6, 55, '2020-12-07', 6, 5);
insert into payment(payment_id, deposit, deposit_date, bidding_id, user_payment_id) values (7, 100000, '2020-12-07', 6, 1);

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