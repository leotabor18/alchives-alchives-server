-- liquibase formatted sql
        
-- changeset Ric:1.0.0.5
-- preconditions onFail:MARK_RAN onError:HALT
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'alchives' AND TABLE_NAME = 'alumni'
CREATE TABLE `alumni` (
    `alumni_id`     INT           AUTO_INCREMENT NOT NULL,
    `student_number`  VARCHAR(256) NOT NULL,
    `first_name`  VARCHAR(256) NOT NULL,
    `last_name`   VARCHAR(256) NOT NULL,
    `suffix`   VARCHAR(256) DEFAULT NULL,
    `image`   VARCHAR(256) DEFAULT NULL,
    `batch_year`   VARCHAR(256) DEFAULT NULL,
    `quotes`   VARCHAR(256) DEFAULT NULL,
    `award`   VARCHAR(256) DEFAULT NULL,
    `email`   VARCHAR(256) DEFAULT NULL,
    `status`      CHAR(3)      DEFAULT 'ACT',
    `program_id`   INT           DEFAULT NULL,
    `who_added`   INT           DEFAULT NULL,
    `when_added`  DATETIME(0)   DEFAULT CURRENT_TIMESTAMP,
    `who_updated` INT           DEFAULT NULL,
    `ts`          DATETIME(0)   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`alumni_id`),
    CONSTRAINT `alumni_uq_01` UNIQUE (`student_number`),
    CONSTRAINT `alumni_fk_02` FOREIGN KEY (`program_id`) REFERENCES `program` (`program_id`)
);