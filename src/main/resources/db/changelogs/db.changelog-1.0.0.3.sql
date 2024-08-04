-- liquibase formatted sql
        
-- changeset Ric:1.0.0.3
-- preconditions onFail:MARK_RAN onError:HALT
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'alchives' AND TABLE_NAME = 'institute'
CREATE TABLE `institute` (
    `institute_id`     INT           AUTO_INCREMENT NOT NULL,
    `name`  VARCHAR(256) NOT NULL,
    `description`  VARCHAR(1000) NULL,
    `who_added`   INT           DEFAULT NULL,
    `when_added`  DATETIME(0)   DEFAULT CURRENT_TIMESTAMP,
    `who_updated` INT           DEFAULT NULL,
    `ts`          DATETIME(0)   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`institute_id`),
    CONSTRAINT `institute_uq_01` UNIQUE (`name`)
);