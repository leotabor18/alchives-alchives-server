-- liquibase formatted sql
        
-- changeset Ric:1.0.0.6
-- preconditions onFail:MARK_RAN onError:HALT
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'alchives' AND TABLE_NAME = 'event'
CREATE TABLE `event` (
    `event_id`     INT           AUTO_INCREMENT NOT NULL,
    `name`         VARCHAR(256)  NOT NULL,
    `theme`        VARCHAR(256)  DEFAULT NULL,
    `venue`        VARCHAR(256)  NOT NULL,
    `date`         DATETIME(0)   NOT NULL,
    `batch_year`   VARCHAR(256)  NOT NULL,
    `theme_song`   VARCHAR(256)  DEFAULT NULL,
    `program_id`   INT           DEFAULT NULL,
    `who_added`    INT           DEFAULT NULL,
    `when_added`   DATETIME(0)   DEFAULT CURRENT_TIMESTAMP,
    `who_updated`  INT           DEFAULT NULL,
    `ts`           DATETIME(0)   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`event_id`)
);