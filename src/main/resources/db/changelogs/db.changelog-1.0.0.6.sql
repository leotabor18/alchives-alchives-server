-- liquibase formatted sql
        
-- changeset Ric:1.0.0.6
-- preconditions onFail:MARK_RAN onError:HALT
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'alchives' AND TABLE_NAME = 'role'
CREATE TABLE `role` (
    `role_id` INT AUTO_INCREMENT NOT NULL,
    `name` VARCHAR(256) DEFAULT NULL,
    `program_id` INT DEFAULT NULL,
    `student_id` INT DEFAULT NULL,
    `who_added` INT DEFAULT NULL,
    `when_added` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `who_updated` INT DEFAULT NULL,
    `ts` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`role_id`),
    CONSTRAINT `role_uq_01` UNIQUE (`name`)
);