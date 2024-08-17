-- liquibase formatted sql
        
-- changeset Ric:1.0.0.14
-- preconditions onFail:MARK_RAN onError:HALT
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM `program`;
INSERT INTO `program` (`name`, `description`, `institute_id`)
VALUES 
('BACHELOR OF SCIENCE IN COMPUTER SCIENCE', 'The BS Computer Science program includes the study of computing concepts and theories, algorithmic foundations, new developments in computing, and the standards and practices in software engineering.',1),
('BACHELOR OF SCIENCE IN INFORMATION SYSTEMS', 'The BS Information Systems program includes the study of application and effect of information technology to organizations. The program also contains units in Business Analytics and Service Management Program.', 1),
('ASSOCIATE IN COMPUTER TECHNOLOGY', 'The Associate in Computer Technology (ACT) is a 2-year degree program that provides specialized computing and information technology tracks equipping graduates with the required skills and competencies catering to the needs of industry.', 1),
('BACHELOR OF LIBRARY AND INFORMATION SCIENCE', 'The BLIS program is the study of the development, deployment, and management of information resources in print, non-print, electronic and digital formats and services.', 1),
('BACHELOR OF SCIENCE IN TOURISM MANAGEMENT', 'Bachelor of Science in Tourism Management will equip students with competencies related to the basic and core requirements as well as those associated with major and minor areas of concentration and elective courses. This curricular program aims to address the need for professionals who can help in promoting the tourism industry in the Philippines.', 2),
('BACHELOR OF SCIENCE IN ENTREPRENEURSHIP', 'Bachelor of Science in Entrepreneurship prepares an individual to start and manage his/her own business. It aims to develop entrepreneurs who are motivated and knowledgeable in identifying opportunities, developing and preparing business plans and actually starting and managing a business.', 2),
('BACHELOR OF SCIENCE IN ACCOUNTANCY', 'Bachelor of Science in Accountancy provides general accounting education to students wanting to pursue a professional career in accountancy in general and in public accounting in particular.', 2),
('BACHELOR OF PHYSICAL EDUCATION', 'The Bachelor of Physical Education or BPEd is a (4) four-year program aimed at equipping graduates with the competencies to meet the psychomotor, cognitive and affective needs of learners.', 3),
('BACHELOR OF TECHNICAL-VOCATIONAL TEACHER EDUCATION MAJOR IN FOOD AND SERVICE MANAGEMENT', 'The BTVTEd is a four-year degree program that equips learners with adequate and relevant competencies in teaching specific area in food and service management and with technology and livelihood courses.', 3),
('BACHELOR OF ARTS IN ENGLISH LANGUAGE STUDIES', 'The BAELS is a four-year degree program designed to integrate theory and practice to prepare students the students for effective communication in English in diverse contexts and situations.', 3),
('BACHELOR OF SPECIAL NEEDS EDUCATION', 'The Bachelor of Special Needs Education (BSNEd) is an undergraduate degree program which specializes in special needs education.', 3),
('BACHELOR OF SCIENCE IN PSYCHOLOGY', 'The BS Psychology is a four-year degree program that provides initial training for those interested in teaching, research and practice of psychology. The program aims to study how the person’s mental processes and behavior are affected by internal, relational and social factors.', 3),
('BACHELOR OF SCIENCE IN MATHEMATICS', 'Mathematics is often described as the science of patterns. Mathematicians seek to discover, analyze, and classify abstract objects and natural phenomena.', 3)
