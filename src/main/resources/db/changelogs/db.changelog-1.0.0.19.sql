-- liquibase formatted sql
        
-- changeset Ric:1.0.0.19
-- preconditions onFail:MARK_RAN onError:HALT
-- precondition-sql-check expectedResult:1 SELECT COUNT(*) FROM `institute`;
ALTER TABLE institute
ADD COLUMN image VARCHAR(256),
ADD COLUMN [color] VARCHAR(256);
