-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema peter_workshop1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema peter_workshop1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `peter_workshop1` DEFAULT CHARACTER SET utf8 ;
USE `peter_workshop1` ;

-- -----------------------------------------------------
-- Table `peter_workshop1`.`account_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `peter_workshop1`.`account_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `peter_workshop1`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `peter_workshop1`.`customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `middlename` VARCHAR(15) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `peter_workshop1`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `peter_workshop1`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(180) NOT NULL,
  `account_type_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  INDEX `fk_account_type` (`account_type_id` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  INDEX `fk_customer_id_1` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `fk_account_type`
    FOREIGN KEY (`account_type_id`)
    REFERENCES `peter_workshop1`.`account_type` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_customer_id_1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `peter_workshop1`.`customer` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `peter_workshop1`.`address_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `peter_workshop1`.`address_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `peter_workshop1`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `peter_workshop1`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NOT NULL,
  `house_number` INT NOT NULL,
  `house_extension` VARCHAR(5) NULL DEFAULT NULL,
  `zip_code` VARCHAR(6) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `customer_id` INT NOT NULL,
  `address_type_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_address_type` (`address_type_id` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_customer_id_2` (`customer_id` ASC) INVISIBLE,
  CONSTRAINT `fk_address_type`
    FOREIGN KEY (`address_type_id`)
    REFERENCES `peter_workshop1`.`address_type` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_customer_id_2`
    FOREIGN KEY (`customer_id`)
    REFERENCES `peter_workshop1`.`customer` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `peter_workshop1`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `peter_workshop1`.`product` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` DECIMAL(6,2) NOT NULL,
  `stock` INT(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `peter_workshop1`.`order_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `peter_workshop1`.`order_status` (
  `id` INT(11) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `peter_workshop1`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `peter_workshop1`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `total_cost` DECIMAL(6,2) NOT NULL,
  `customer_id` INT NOT NULL,
  `order_status_id` INT NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_order_status_id_idx` (`order_status_id` ASC) VISIBLE,
  INDEX `fk_customer_id_3_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_status_id`
    FOREIGN KEY (`order_status_id`)
    REFERENCES `peter_workshop1`.`order_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_id_3`
    FOREIGN KEY (`customer_id`)
    REFERENCES `peter_workshop1`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peter_workshop1`.`order_line`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `peter_workshop1`.`order_line` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `amount` INT NOT NULL,
  INDEX `fk_product_id` (`product_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_order_id_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_id`
    FOREIGN KEY (`product_id`)
    REFERENCES `peter_workshop1`.`product` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `peter_workshop1`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
