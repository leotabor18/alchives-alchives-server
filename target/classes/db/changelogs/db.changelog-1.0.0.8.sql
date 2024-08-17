-- liquibase formatted sql
        
-- changeset Ric:1.0.0.8
-- preconditions onFail:MARK_RAN onError:HALT
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'alchives' AND TABLE_NAME = 'personnel'
CREATE TABLE `personnel` (
    `personnel_id` INT AUTO_INCREMENT NOT NULL,
    `full_name`    VARCHAR(256) NOT NULL,
    `prefix`       VARCHAR(256) DEFAULT NULL,
    `suffix`       VARCHAR(256) DEFAULT NULL,
    `title`        VARCHAR(256) NOT NULL,
    `department`   VARCHAR(256) NOT NULL,
    `position`     VARCHAR(256) NOT NULL,
    `role`         VARCHAR(256) NOT NULL,
    `profile_pic`  VARCHAR(256) DEFAULT NULL,
    `who_added`    INT DEFAULT NULL,
    `when_added`   DATETIME(0) DEFAULT CURRENT_TIMESTAMP,
    `who_updated`  INT DEFAULT NULL,
    `ts`           DATETIME(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`personnel_id`),
    CONSTRAINT `personnel_uq_01` UNIQUE (`full_name`)
);