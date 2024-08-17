-- liquibase formatted sql
        
-- changeset Ric:1.0.0.11
-- preconditions onFail:MARK_RAN onError:HALT
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'alchives' AND TABLE_NAME = 'content'
CREATE TABLE `content` (
    `content_id`   INT           AUTO_INCREMENT NOT NULL,
    `type`         INT           NOT NULL,
    `title`        VARCHAR(256)  NOT NULL,
    `description`  TEXT          NOT NULL,
    `image_url`    VARCHAR(256)  NOT NULL,
    `who_added`    INT           DEFAULT NULL,
    `when_added`   DATETIME(0)   DEFAULT CURRENT_TIMESTAMP,
    `who_updated`  INT           DEFAULT NULL,
    `ts`           DATETIME(0)   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`content_id`)
);