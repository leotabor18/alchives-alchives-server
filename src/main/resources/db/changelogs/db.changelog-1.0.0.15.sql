-- liquibase formatted sql
        
-- changeset Ric:1.0.0.15
-- preconditions onFail:MARK_RAN onError:HALT
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM `alumni`;
INSERT INTO `alumni` (`student_number`, `first_name`, `last_name`, `batch_year`, `quotes`, `program_id`)
VALUES 
('20-778', 'Vincent', 'Clark', '2024', 'Work Hard in silence let success make the noise', 2),
('21-0267', 'Ric Isaac', 'Montalvo', '2024', 'Success Doesn’t just come and find you, You have to go out and get it.', 2),
('21-0297', 'Joshua', 'Salunga', '2024', 'Life is a journey and only you hold the map', 2),
('21-0351', 'Karl Christian', 'Punsalan', '2024', 'Don’t let yesterday take up too much of today', 2)