-- liquibase formatted sql
        
-- changeset Ric:1.0.0.7
-- preconditions onFail:MARK_RAN onError:HALT
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'alchives' AND TABLE_NAME = 'event_program'
CREATE TABLE `event_program` (
    `event_program_id` INT AUTO_INCREMENT NOT NULL,
    `event_id`         INT NOT NULL,
    `program_id`       INT NOT NULL,
    PRIMARY KEY (`event_program_id`),
    CONSTRAINT `event_program_fk_01` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`),
    CONSTRAINT `event_program_fk_02` FOREIGN KEY (`program_id`) REFERENCES `program` (`program_id`)
);