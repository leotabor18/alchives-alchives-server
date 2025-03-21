-- liquibase formatted sql
        
-- changeset Ric:1.0.0.18
-- preconditions onFail:MARK_RAN onError:HALT
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'alchives' AND TABLE_NAME = 'achievement'
CREATE TABLE `achievement` (
    `achievement_id` INT AUTO_INCREMENT NOT NULL,
    `alumni_id` INT NOT NULL,
    `text` VARCHAR(255) DEFAULT NULL,
    `date` VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (`achievement_id`),
    CONSTRAINT `achievements_fk_01` FOREIGN KEY (`alumni_id`) REFERENCES `alumni` (`alumni_id`) ON DELETE CASCADE
);