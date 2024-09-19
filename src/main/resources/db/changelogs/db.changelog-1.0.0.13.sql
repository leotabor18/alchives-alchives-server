-- liquibase formatted sql
        
-- changeset Ric:1.0.0.13
-- preconditions onFail:MARK_RAN onError:HALT
-- precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM `user` WHERE email_address = 'admin@gmail.com';
INSERT INTO `user` (`first_name`, `last_name`, `email_address`, `password`, `status`, `role`)
VALUES ('admin', 'test', 'admin@gmail.com', '$2a$10$eKv1PjeuW96zZezHdFBAhO3OEhPV9hqFMsrufnxncviREVUAP1EKC', 'ACT', 'ADMIN');