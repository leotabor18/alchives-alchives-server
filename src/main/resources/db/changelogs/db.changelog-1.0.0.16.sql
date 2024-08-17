-- liquibase formatted sql
        
-- changeset Ric:1.0.0.15
-- preconditions onFail:MARK_RAN onError:HALT
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM `content`;
INSERT INTO `content` (`type`, `title`, `description`)
VALUES 
(1, 'CORE VALUES', 'Excellence /n Stewardship /n Resiliency /n Patrimony', '2025', 'Work Hard in silence let success make the noise'),
(1, 'VISION', 'An institution of hope, a thought leader in its area of operations and preferred provider of talent in the Metro Angeles Area.'),
(1, 'MISSION', 'The City College of Angeles is committed to offer quality education for the holistic development of competitive and technically-capable professionals with deep sense of excellence, resiliency, stewardship, and patrimony.'),
(1, 'OBJECTIVES', '• To provide degree and non-degree programs that are relevant to local and global demands and are in compliance with quality standards in education.

• To strengthen the natural capability of the College through continuous benchmarking, program evaluation, innovation and professional development of the administrators, faculty and staff.

• To develop networks and linkages with local and international institutions to ensure quality of education.

• To produce research which will contribute to the efficient and effective management of the institution and to serve the community better.

• To develop and strengthen the moral values of administrators, faculty, staff and students.

• To develop a positive image of corporate and social responsibility that will benefit the community.

• To serve as an active agent for the cultivation, promotion, preservation, and appreciation of Kapampangan arts, culture and heritage.'),
(1, 'CCA HYMN', `Oh City College College of Angeles! /n 
You inculcate faith in ourselves /n 
Testament to our undying will /n 
To earn wisdom of paramount quality /n 
Hail our Alma Mater, our sanctuary! /n 
You always call us to seek excellence /n 
Yet beacon of humility and resilience /n 
For hope's alive in us as our passion /n 
To steward with kindness and compassion /n 

Hail CCA, our shelter and protection! /n 
Forever we'll hold fond memories /n 
In molding us as paragons of greatness /n 
Never we'll forget, in all times of glory /n 
To give thanks to God and our community /n 
Hail CCA, our beloved Alma Mater!`)