-- liquibase formatted sql
        
-- changeset Ric:1.0.0.8
-- preconditions onFail:MARK_RAN onError:HALT
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM `institute`;
INSERT INTO  `institute` (`name`)
VALUES 
('INSTITUTE OF COMPUTING STUDIES AND LIBRARY INFORMATION SCIENCE'),
('INSTITUTE OF BUSINESS AND MANAGEMENT'),
('INSTITUTE OF EDUCATION, ARTS, AND SCIENCES');