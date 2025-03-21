-- liquibase formatted sql
        
-- changeset Ric:1.0.0.17
-- preconditions onFail:MARK_RAN onError:HALT
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM `content`;
ALTER TABLE event
ADD COLUMN theme_song_title VARCHAR(256);
