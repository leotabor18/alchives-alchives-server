-- liquibase formatted sql
        
-- changeset Ric:1.0.0.1
-- preconditions onFail:MARK_RAN onError:HALT
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'alchives' AND TABLE_NAME = 'user'
CREATE TABLE `user` (
    `user_id`     INT           AUTO_INCREMENT NOT NULL,
    `first_name`  NVARCHAR(256) DEFAULT NULL,
    `last_name`   NVARCHAR(256) DEFAULT NULL,
    `email_address` NVARCHAR(256) DEFAULT NULL,
    `password`    VARCHAR(256) DEFAULT NULL,
    `status`      CHAR(3)      DEFAULT 'ACT',
    `role_id`   INT           DEFAULT NULL,
    `who_added`   INT           DEFAULT NULL,
    `when_added`  DATETIME(0)   DEFAULT CURRENT_TIMESTAMP,
    `who_updated` INT           DEFAULT NULL,
    `ts`          DATETIME(0)   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`user_id`),
    CHECK (`who_added` > 0),
    CHECK (`who_updated` > 0),
    CONSTRAINT `user_uq_01` UNIQUE (`email_address`)
);