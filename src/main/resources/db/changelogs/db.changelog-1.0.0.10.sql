-- liquibase formatted sql
        
-- changeset Ric:1.0.0.10
-- preconditions onFail:MARK_RAN onError:HALT
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'alchives' AND TABLE_NAME = 'batch_picture'
CREATE TABLE `batch_picture` (
    `batch_picture_id` INT AUTO_INCREMENT NOT NULL,
    `event_id`         INT NOT NULL,
    `image`            VARCHAR(256) NOT NULL,
    PRIMARY KEY (`batch_picture_id`),
    CONSTRAINT `batch_picture_fk_01` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`)
);