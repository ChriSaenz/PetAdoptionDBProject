-- -----------------------------------------------------
-- Schema adoption_system
-- -----------------------------------------------------

-- drop database adoption_system;

-- set foreign_key_checks = 0;
-- drop table Customer;
-- drop table Request;
-- drop table Pet;
-- drop table Employee;
-- drop table Receipt;
-- set foreign_key_checks = 1;


-- drop table Customer cascade;
-- drop table Request cascade;
-- drop table Pet cascade;
-- drop table Employee cascade;
-- drop table Receipt cascade;

-- -----------------------------------------------------
-- Schema adoption_system
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `adoption_system` DEFAULT CHARACTER SET utf8 ;
USE `adoption_system` ;

-- -----------------------------------------------------
-- Table `adoption_system`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `adoption_system`.`Customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `phone_number` VARCHAR(45) NULL,
  `date_joined` DATE NULL,
  `birthday` DATE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `adoption_system`.`Request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `adoption_system`.`Request` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `pet_id` INT NOT NULL,
  `date` DATE NOT NULL,
  `status` VARCHAR(50) NULL,
  `employee_id` INT NULL,
  PRIMARY KEY (`id`, `customer_id`),
  INDEX `fk_Request_Customer1_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `fk_Request_Customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `adoption_system`.`Customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `adoption_system`.`Pet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `adoption_system`.`Pet` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Request_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `age` INT NOT NULL,
  `species` VARCHAR(45) NOT NULL,
  `breed` VARCHAR(45) NOT NULL,
  `color` VARCHAR(45) NOT NULL,
  `sex` VARCHAR(45) NOT NULL,
  `neutered` TINYINT NOT NULL,
  `vaccinated` TINYINT NOT NULL,
  `date_acquired` DATE NOT NULL,
  `cost` DOUBLE NULL,
  PRIMARY KEY (`id`, `Request_id`),
  INDEX `fk_Pet_Request1_idx` (`Request_id` ASC) VISIBLE,
  CONSTRAINT `fk_Pet_Request1`
    FOREIGN KEY (`Request_id`)
    REFERENCES `adoption_system`.`Request` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `adoption_system`.`Employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `adoption_system`.`Employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `salary` DOUBLE NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `admin` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `adoption_system`.`Receipt`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `adoption_system`.`Receipt` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `employee_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `request_id` INT NOT NULL,
  `date` DATE NOT NULL,
  `cost` DOUBLE NOT NULL,
  PRIMARY KEY (`id`, `employee_id`, `customer_id`, `request_id`),
  INDEX `fk_Receipt_Employee1_idx` (`employee_id` ASC) VISIBLE,
  INDEX `fk_Receipt_Request1_idx` (`request_id` ASC) VISIBLE,
  INDEX `fk_Receipt_Customer1_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `fk_Receipt_Employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `adoption_system`.`Employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Receipt_Request1`
    FOREIGN KEY (`request_id`)
    REFERENCES `adoption_system`.`Request` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Receipt_Customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `adoption_system`.`Customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- ------------------
INSERT INTO Customer (name, phone_Number, date_joined, birthday)
VALUES ('Chase', '123-456-7890', '2022-07-08', '1999-08-31'), 
("Diego", "234-567-8901", '2020-07-19', '2021-07-19'),
("Chris", "334-577-8905", '1990-07-19', '2022-07-19'),
("Felix", "234-567-8901", '2020-07-19', '2000-07-19');

insert into Employee(username, password, name, phone, salary, title, admin) values
("Boss", "P42", "Biggs", "(555)", 500000.00, "Big Boss", 1),
("Linked", "33", "Stack", "(505)", 80000.00, "Employee A", 0),
("Inn", "P42", "Over", "(755)", 50000.00, "Employee B", 0),
("Pause", "P42", "Flow", "(855)", 70000.00, "Employee C", 0),
("Menu", "P42", "Thanks", "(655)", 100000.00, "Employee D", 0);
-- Employee Works!


insert into Request(customer_id, pet_id, date, status, employee_id) values
(2, 1, '2022-07-7', "Pending", 1),
(2, 2, '2022-07-8', "Pending", 2),
(2, 3, '2022-07-9', "Pending", 3),
(1, 4, '2022-07-8', "Pending", 4),
(3, 5, '2022-07-8', "Pending", 5);

INSERT INTO Pet (request_id, name, age, species, breed, color, sex, neutered, vaccinated, date_acquired, cost)
VALUES (1, "Chase", 2, "Dog", "Cocker Spaniel", "Brown", "Male", 1, 1, '2022-05-20', 1500.01),
(2, "Phantom", 3, "Dog", "American Shorthair", "Gray", "Male", 1, 1, '2020-04-10', 2000.34),
(3, "Ace", 1, "Dog", "Shiba", "Brown", "Male", 1, 1, '2022-07-15', 1250),
(4, "Dexter", 5, "Cat", "Shorthair", "Tabby", "Male", 1, 1, '2017-03-18', 250);
 
INSERT INTO Pet (request_id, name, age, species, breed, color, sex, neutered, vaccinated, date_acquired, cost)
VALUES (3, "Chase3", 2, "Dog", "Cocker Spaniel", "Brown", "Male", 1, 1, '2022-05-20', 1500.01);
 
INSERT INTO Receipt (employee_id, customer_id, request_id, date, cost)
VALUES (1, 1, 1, '2022-07-02', 1500.01),
(2, 2, 2, '2021-06-03', 2000),
(3, 3, 3, '2020-01-01', 1250);

select * from Customer;
select * from Request;
select * from Pet;
select * from Employee;
select * from Receipt;

describe Customer;
describe Request;
describe Pet;
describe Employee;
describe Receipt;
