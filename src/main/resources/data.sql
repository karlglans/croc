INSERT INTO roles (id, role) VALUES
(1, 'user'), (2, 'supervisor'), (3, 'administrator');

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

INSERT INTO user_roles (user_id, role_id) VALUES
(1, 3), (2, 1), (2, 2), (3, 1), (3, 2), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1), (9, 1), (10, 1), (11, 1), (12, 1),
(13, 1), (14, 1), (15, 1), (16, 1), (17, 1), (18, 1), (19, 1), (20, 1), (21, 1);

INSERT INTO grouped_users (user_id, group_id) VALUES
(2, 1), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1), (9, 1), (10, 1), (11, 1), (12, 1), (13, 1),
(14, 3), (15, 3), (16, 3), (17, 3), (18, 3), (19, 3), (20, 3), (21, 3), (22, 3),
(12, 2), (13, 2), (14, 2), (15, 2);

INSERT INTO form (id, title, isEditable) VALUES
(1, 'form1 ongoing survey', false), (2, 'form2 ongoing survey', false), (3, 'form3', true);

INSERT INTO question (id, text) VALUES
(1, 'question1 from1'), (2, 'question2 form1 and form2'), (3, 'question3 form2'), (4, 'question4 no Form');


INSERT INTO survey (id, form_id, creator_id, status, name, COUNTED_ANSWERING_PARTICIPANTS) VALUES
(1, 1, 2, 'ONGOING', 'Det stora vårforfoluläret 2019', 1), (2, 1, 2, 'ONGOING', 'Undersökning', 3),
(3, 2, 2, 'ONGOING', 'completely answered survey', 0), (4, 2, 2, 'CLOSED', 'Det stora vårforfoluläret 2018', 0);

INSERT INTO FORM_QUESTION (form_id, question_id, number) VALUES
(1, 1, 1), (1, 2, 2), (2, 2, 1), (2, 3, 2);

-- survey1: 2 participants, user3: is in 2 survyes.
INSERT INTO survey_participant (survey_id, participant_id) VALUES
(1, 3), (1, 4), (2, 4),
(3, 10), (3, 11), (3, 12); -- Survey 3 completely answered

-- employee1 has answered both questions, employee2 has just answered 1 question
INSERT INTO answer (survey_id, responder_id, question_id, value) VALUES
(1, 4, 1, 2), (1, 4, 2, 3), (1, 3, 1, 5),
(3, 10, 2, 1), (3, 10, 3, 5),  (3, 11, 2, 1), (3, 11, 3, 5),  (3, 12, 2, 1), (3, 12, 3, 5); -- survey 3 completely answered