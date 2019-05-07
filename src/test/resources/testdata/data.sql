INSERT INTO user_group (id, name) VALUES
(1, 'Everyone from A'), (2, 'Subset of A and B'), (3, 'Everyone from B');

INSERT INTO users (id, email) VALUES
(1, 'admin@purple.com'), (2, 'supervisor1@purple.com'), (3, 'supervisor2@purple.com'), (4, 'employee1@purple.com'),
(5, 'employee2@purple.com'), (6, 'employee3@purple.com'), (7, 'employee4@purple.com'),
(8, 'employee5@purple.com'), (9, 'employee6@purple.com'), (10, 'employee7@purple.com'),
(11, 'employee8@purple.com'), (12, 'employee9@purple.com'), (13, 'employee10@purple.com'),
(14, 'employee11@purple.com'), (15, 'employee12@purple.com'), (16, 'employee13@purple.com'),
(17, 'employee14@purple.com'), (18, 'employee15@purple.com'), (19, 'employee16@purple.com'),
(20, 'employee17@purple.com'), (21, 'employee18@purple.com'), (22, 'employee19@purple.com');

INSERT INTO grouped_users (user_id, group_id) VALUES
(2, 1), (3, 1), (2, 2);

INSERT INTO form (id, title, isEditable) VALUES
(1, 'form1', true), (2, 'form2', true), (3, 'form3', true);

INSERT INTO survey (id, form_id, creator_id, status) VALUES
(1, 1, 2, 'ONGOING'), (2, 1, 2, 'ONGOING'), (3, 2, 2, 'ONGOING'), (4, 2, 2, 'CLOSED');

INSERT INTO question (id, text) VALUES
(1, 'question1 from1'), (2, 'question2form1and2'), (3, 'question3form2'), (4, 'question5noForm');

INSERT INTO FORM_QUESTION (form_id, question_id, number) VALUES
(1, 1, 1), (1, 2, 2), (2, 1, 1);

-- survey1: 2 participants
INSERT INTO survey_participant (survey_id, participant_id) VALUES
(1, 3), (1, 4);

INSERT INTO answer (survey_id, responder_id, question_id, value) VALUES
(1, 3, 1, 131), (1, 3, 2, 132), (2, 3, 1, 231);

--
-- -- question 1 in fqg 1, question 2 into both fqg 1 and 2
-- INSERT INTO GROUP_QUESTION (id, FORM_QUESTION_GROUP_ID, QUESTION_ID, number) VALUES
-- (1, 1, 1, 1), (2, 1, 2, 2), (3, 2, 2, 1);