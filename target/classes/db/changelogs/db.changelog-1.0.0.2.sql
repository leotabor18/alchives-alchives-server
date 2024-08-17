-- liquibase formatted sql
        
-- changeset Ric:1.0.0.2
-- preconditions onFail:MARK_RAN onError:HALT
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'alchives' AND TABLE_NAME = 'verification'
CREATE TABLE `verification` (
    `verification_id` INT AUTO_INCREMENT NOT NULL,
    `user_id` INT NOT NULL,
    `code` VARCHAR(255) DEFAULT NULL,
    `type` VARCHAR(255) DEFAULT NULL,
    `expiration` INT NOT NULL,
    `status` CHAR(3) DEFAULT NULL,
    PRIMARY KEY (`verification_id`),
    CHECK (`expiration` > 0),
    CONSTRAINT `verification_fk_01` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
);