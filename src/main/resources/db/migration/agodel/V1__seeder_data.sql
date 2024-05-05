-- Active: 1711037597965@@127.0.0.1@3306@agodel

-- init user
INSERT INTO user (id, username, password) 
VALUES ('10000000', 'customer', 'password'), 
        ('20000000', 'owner', 'password');
-- init customer
INSERT INTO customer (customer_id, firstname, lastname, phone, email)
VALUES ('10000000', 'cfirstname0', 'clastname0', '1234567890', 'customer@email.com');

-- init owner
INSERT INTO owner (owner_id, firstname, lastname, phone, email)
VALUES ('20000000', 'ofirstname0', 'olastname0', '0987654321', 'owner@email.com');

-- set initial value user count
INSERT INTO user_count (type, count) VALUES ('customer', 1), ('owner', 1);