-- Generating Values for Spring Pet Shop
CREATE SCHEMA IF NOT EXISTS `adoption_systemV2` DEFAULT CHARACTER SET utf8 ;
USE `adoption_systemV2` ;

-- drop database adoption_systemV2;

-- Tables should already be created by Spring
show tables;
show databases;
-- ---------------------------
-- Adding values to the tables
-- ---------------------------

describe Customer;
select * from Customer;
-- set SQL_SAFE_UPDATES = 0; delete from Customer; set SQL_SAFE_UPDATES = 1;
INSERT INTO Customer (id, name, phone, date_joined, birthday) VALUES 
(1, 'Chase', '(123)456-7890', '2022-07-08', '1999-08-31'), 
(2, "Diego", "(234)567-8901", '2020-07-19', '2021-07-20'),
(3, "Chris", "(334)577-8905", '1990-01-09', '2022-07-23'),
(4, "Felix", "(234)567-8901", '2020-08-19', '2000-07-23'),
(5, "CustomerA", "(123)235-2533", '2020-04-25', '2022-08-01'),
(6, "CustomerB", "(497)654-4896", '2020-05-11', '2022-08-06'),
(7, "CustomerC", "(708)888-8901", '2020-01-17', '2022-08-08');


describe Employee;
select * from Employee;
-- set SQL_SAFE_UPDATES = 0; delete from Employee; set SQL_SAFE_UPDATES = 1;
insert into Employee(id, name, phone, salary, title) values
(8, "Boss", "(555)559-7985", 500000.00, "Big Boss"),
(9, "Linked", "(505)165-7465", 80000.00, "Employee A"),
(10, "Inn", "(755)216-9849", 50000.00, "Employee B"),
(11, "Pause", "(855)789-2160", 70000.00, "Employee C"),
(12, "Menu", "(655)468-4650", 100000.00, "Employee D");


describe Pet;
select * from Pet;
-- set SQL_SAFE_UPDATES = 0; delete from Pet; set SQL_SAFE_UPDATES = 1;
INSERT INTO Pet (id, name, age, species, breed, color, sex, neutered, vaccinated, date_acquired, cost) values
(13, "Chase", 2, "Dog", "Cocker Spaniel", "Brown", "Male", 0, 0, '2022-05-20', 1500.01),
(14, "Phantom", 3, "Fish", "American Shorthair Fish", "Red", "Female", 0, 1, '2020-04-10', 2000.34),
(15, "Ace", 1, "Bird", "Flying Shiba", "Golden", "Male", 1, 1, '2022-07-15', 1250),
(16, "Dexter", 5, "Cat", "Shorthair", "Tabby", "Male", 1, 0, '2017-03-18', 250),
(17, "Cloud", 5, "Cat", "Puffy", "White", "Female", 0, 1, '2018-03-18', 25.0),
(18, "Chase3", 2, "Dog", "Cocker Spaniel", "Brown", "Male", 1, 1, '2022-05-20', 1500.01);

-- Should be auto generated
describe Request;
select * from Request;
-- set SQL_SAFE_UPDATES = 0; delete from Request; set SQL_SAFE_UPDATES = 1;
insert into Request(id, employee_id, customer_id, pet_id, date, status) values
(19, 8, 1, 13, '2022-07-7', "Pending"),
(20, 11, 2, 14, '2022-07-8', "Pending"),
(21, 12, 1, 18, '2022-07-10', "Pending");

-- Input manually in Postman
describe User;
select * from User;
-- -- set SQL_SAFE_UPDATES = 0; delete from User; set SQL_SAFE_UPDATES = 1;
-- insert into User(id, username, password) values
-- ();

-- Should be auto Generated
describe Receipt;
select * from Receipt;
-- set SQL_SAFE_UPDATES = 0; delete from Receipt; set SQL_SAFE_UPDATES = 1;
-- INSERT INTO Receipt (employee_id, customer_id, request_id, date, cost)
-- VALUES (1, 1, 1, '2022-07-02', 1500.01),
-- (2, 2, 2, '2021-06-03', 2000),
-- (3, 3, 3, '2020-01-01', 1250);


-- After adding all customs rows, set hibernate next_val to latest id + 1
describe hibernate_sequence;
select * from hibernate_sequence;
set SQL_SAFE_UPDATES = 0;
update hibernate_sequence set next_val = 22;
set SQL_SAFE_UPDATES = 1;
select * from hibernate_sequence;




