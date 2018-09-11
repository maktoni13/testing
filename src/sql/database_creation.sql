-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema testing_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema testing_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `testing_db` DEFAULT CHARACTER SET utf8 ;
USE `testing_db` ;

-- -----------------------------------------------------
-- Table `testing_db`.`theme`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testing_db`.`theme` ;

CREATE TABLE IF NOT EXISTS `testing_db`.`theme` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `name_ua` VARCHAR(45) NOT NULL,
  `description` LONGTEXT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
  `description_ua` LONGTEXT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `theme_id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testing_db`.`test`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testing_db`.`test` ;

CREATE TABLE IF NOT EXISTS `testing_db`.`test` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `name_ua` VARCHAR(45) NOT NULL,
  `description` LONGTEXT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
  `description_ua` LONGTEXT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
  `inactive` TINYINT ZEROFILL NOT NULL DEFAULT 0,
  `theme_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `theme_id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_test_theme_idx` (`theme_id` ASC) VISIBLE,
  CONSTRAINT `fk_test_theme`
    FOREIGN KEY (`theme_id`)
    REFERENCES `testing_db`.`theme` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testing_db`.`question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testing_db`.`question` ;

CREATE TABLE IF NOT EXISTS `testing_db`.`question` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `id_local` INT(11) NOT NULL,
  `description` LONGTEXT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
  `description_ua` LONGTEXT CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
  `test_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `theme_id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_question_test1_idx` (`test_id` ASC) VISIBLE,
  CONSTRAINT `fk_question_test1`
    FOREIGN KEY (`test_id`)
    REFERENCES `testing_db`.`test` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testing_db`.`answer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testing_db`.`answer` ;

CREATE TABLE IF NOT EXISTS `testing_db`.`answer` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `id_local` INT(11) NOT NULL,
  `description` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
  `description_ua` VARCHAR(45) NOT NULL,
  `correct_flag` TINYINT NOT NULL DEFAULT 0,
  `question_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `theme_id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_answer_question1_idx` (`question_id` ASC) VISIBLE,
  CONSTRAINT `fk_answer_question1`
    FOREIGN KEY (`question_id`)
    REFERENCES `testing_db`.`question` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testing_db`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testing_db`.`user` ;

CREATE TABLE IF NOT EXISTS `testing_db`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(25) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
  `password` CHAR(79) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
  `email` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `first_name_ua` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `last_name_ua` VARCHAR(50) NOT NULL,
  `enabled_flag` TINYINT NOT NULL DEFAULT 0,
  `admin_flag` TINYINT NOT NULL DEFAULT 0,
  `tests_completed` INT(11) NOT NULL DEFAULT 0,
  `average_evaluation` INT(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `student_id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testing_db`.`summary`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testing_db`.`summary` ;

CREATE TABLE IF NOT EXISTS `testing_db`.`summary` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `test_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `informed_flag` TINYINT NOT NULL,
  `start_date` TIMESTAMP NOT NULL,
  `finish_date` TIMESTAMP NULL,
  `questions_quantity` INT NOT NULL,
  `correct_answered` INT NOT NULL,
  `best_result_flag` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_summary_test1_idx` (`test_id` ASC) VISIBLE,
  INDEX `fk_summary_student1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_summary_test1`
    FOREIGN KEY (`test_id`)
    REFERENCES `testing_db`.`test` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_summary_student1`
    FOREIGN KEY (`user_id`)
    REFERENCES `testing_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testing_db`.`question_result`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testing_db`.`question_result` ;

CREATE TABLE IF NOT EXISTS `testing_db`.`question_result` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `id_local` INT(11) NOT NULL,
  `description` LONGTEXT NOT NULL,
  `description_ua` LONGTEXT NOT NULL,
  `summary_id` INT(11) NOT NULL,
  `incorrect_flag` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_question_result_summary1_idx` (`summary_id` ASC) VISIBLE,
  CONSTRAINT `fk_question_result_summary1`
    FOREIGN KEY (`summary_id`)
    REFERENCES `testing_db`.`summary` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testing_db`.`answer_result`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `testing_db`.`answer_result` ;

CREATE TABLE IF NOT EXISTS `testing_db`.`answer_result` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `id_local` INT(11) NOT NULL,
  `description` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
  `description_ua` VARCHAR(45) NOT NULL,
  `correct_flag` TINYINT NOT NULL DEFAULT 0,
  `chosen_flag` TINYINT NOT NULL,
  `question_result_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `theme_id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_answer_result_question_result1_idx` (`question_result_id` ASC) VISIBLE,
  CONSTRAINT `fk_answer_result_question_result1`
    FOREIGN KEY (`question_result_id`)
    REFERENCES `testing_db`.`question_result` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
