-- -----------------------------------------------------
-- Schema adoption_system
-- -----------------------------------------------------

-- drop database adoption_system;

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

-- -----------------------------------------
-- Copying Ends Here
-- ----------------------------------------



-- ------------------
-- Insert stuffs here
-- ------------------
insert into Employee(username, password, name, phone, salary, title, admin) values
("T-User", "P42", "Trevor", "(555)", 80000.00, "Employee A", 0);

select * from Employee;

-- insert into Customer(name, phoneNumber, date_joined, birthday)--  values
-- ("Diego Cordova", "(555)", '2020-07-19', '2020-07-19'),
-- ;

INSERT INTO Customer (name, phoneNumber, date_joined, birthday)
VALUES ('Chase', '123-456-7890', '2022-07-08', '1999-08-31'), 
("Diego", "234-567-8901", '2020-07-19', '2021-07-19'),
("Chris", "334-577-8905", '1990-07-19', '2022-07-19'),
("Felix", "234-567-8901", '2020-07-19', '2000-07-19');

select * from Customer;

