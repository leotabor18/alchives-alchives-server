-- liquibase formatted sql
        
-- changeset Ric:1.0.0.4
-- preconditions onFail:MARK_RAN onError:HALT
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'alchives' AND TABLE_NAME = 'program'
CREATE TABLE `program` (
    `program_id`     INT           AUTO_INCREMENT NOT NULL,
    `institute_id`   INT           DEFAULT NULL,
    `name`  VARCHAR(256) NOT NULL,
    `description`  VARCHAR(1000) NULL,
    `who_added`   INT           DEFAULT NULL,
    `when_added`  DATETIME(0)   DEFAULT CURRENT_TIMESTAMP,
    `who_updated` INT           DEFAULT NULL,
    `ts`          DATETIME(0)   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`program_id`),
    CONSTRAINT `program_uq_01` UNIQUE (`name`),
    CONSTRAINT `program_fk_01` FOREIGN KEY (`institute_id`) REFERENCES `institute` (`institute_id`)
);