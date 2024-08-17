-- liquibase formatted sql
        
-- changeset Ric:1.0.0.9
-- preconditions onFail:MARK_RAN onError:HALT
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'alchives' AND TABLE_NAME = 'event_personnel'
CREATE TABLE `event_personnel` (
    `event_personnel_id` INT AUTO_INCREMENT NOT NULL,
    `event_id`           INT NOT NULL,
    `personnel_id`       INT NOT NULL,
    `type`              VARCHAR(256) NOT NULL,
    `message`           VARCHAR(256) NOT NULL,
    PRIMARY KEY (`event_personnel_id`),
    CONSTRAINT `event_personnel_fk_01` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`),
    CONSTRAINT `event_personnel_fk_02` FOREIGN KEY (`personnel_id`) REFERENCES `personnel` (`personnel_id`)
);