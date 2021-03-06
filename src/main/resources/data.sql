INSERT INTO user_group (id, name) VALUES
(1, 'Everyone from A'), (2, 'Subset of A and B'), (3, 'Everyone from B');

INSERT INTO users (id, email, role) VALUES
(1, 'admin@purple.com', 'administrator'), (2, 'supervisor1@purple.com', 'supervisor'), (3, 'supervisor2@purple.com', 'supervisor'),
(4, 'employee1@purple.com', 'user'), (5, 'employee2@purple.com', 'user'), (6, 'employee3@purple.com', 'user'),
(7, 'employee4@purple.com', 'user'), (8, 'employee5@purple.com', 'user'), (9, 'employee6@purple.com', 'user'),
(10, 'karlglans@gmail.com', 'pending'), (11, 'employee8@purple.com', 'user'), (12, 'employee9@purple.com', 'user'),
(13, 'employee10@purple.com', 'user'), (14, 'employee11@purple.com', 'user'), (15, 'employee12@purple.com', 'user'),
(16, 'employee13@purple.com', 'user'), (17, 'employee14@purple.com', 'user'), (18, 'employee15@purple.com', 'user'),
(19, 'employee16@purple.com', 'user'), (20, 'employee17@purple.com', 'user'), (21, 'employee18@purple.com', 'user'),
(22, 'employee19@purple.com', 'user');
ALTER SEQUENCE users_id_seq RESTART WITH 23;

INSERT INTO grouped_users (user_id, group_id) VALUES
(2, 1), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1), (9, 1), (10, 1), (11, 1), (12, 1), (13, 1),
(14, 3), (15, 3), (16, 3), (17, 3), (18, 3), (19, 3), (20, 3), (21, 3), (22, 3),
(12, 2), (13, 2), (14, 2), (15, 2);

INSERT INTO form (id, title, isEditable) VALUES
(1, 'form1 ongoing survey', false), (2, 'form2 ongoing survey', false), (3, 'form3', true), (4, 'form4', false);
ALTER SEQUENCE form_id_seq RESTART WITH 5;

INSERT INTO question (id, text, question_type) VALUES
(1, 'question1 from1', 'NUMERIC'), (2, 'question2 form1 and form2', 'NUMERIC'), (3, 'question3 form2', 'NUMERIC'),
(4, 'question4 no Form', 'NUMERIC'), (5, 'question5 from4', 'YESNO'), (6, 'question6 form4', 'NUMERIC'),
(7, 'question7 form4', 'NUMERIC'), (8, 'question8 form4', 'NUMERIC');
ALTER SEQUENCE question_id_seq RESTART WITH 9;


INSERT INTO survey (id, form_id, creator_id, status, name, COUNTED_ANSWERING_PARTICIPANTS) VALUES
(1, 1, 2, 'ONGOING', 'Det stora vårforfoluläret 2019', 1),
(2, 1, 2, 'ONGOING', 'Undersökning', 3),
(3, 2, 2, 'ONGOING', 'completely answered survey', 0),
(4, 2, 2, 'CLOSED', 'Det stora vårforfoluläret 2018 (older)', 0),
(5, 4, 2, 'ONGOING', 'Massa frågor', 3),
(6, 4, 2, 'CLOSED', 'Äldre 1', 30),
(7, 4, 2, 'CLOSED', 'Äldre 2', 14),
(8, 4, 2, 'CLOSED', 'Äldre 3', 31);
ALTER SEQUENCE survey_id_seq RESTART WITH 9;

INSERT INTO FORM_QUESTION (form_id, question_id, number) VALUES
(1, 1, 1), (1, 2, 2), (2, 2, 1), (2, 3, 2),
(4, 5, 1), (4, 6, 2), (4, 7, 3), (4, 8, 4);

-- survey1: 2 participants, user3: is in 2 surveys. complete
INSERT INTO survey_participant (survey_id, participant_id, complete) VALUES
(1, 3, false), (1, 4, true), (1, 5, true), (2, 4, false),
(3, 10, true), (3, 11, true), (3, 12, true), -- Survey 3 completely answered
(5, 4, false);

-- employee1 has answered both questions, employee2 has just answered 1 question
INSERT INTO answer (survey_id, responder_id, question_id, value) VALUES
(1, 4, 1, 2), (1, 4, 2, 3), (1, 3, 1, 5), (1, 5, 1, 5), (1, 5, 2, 3),
(3, 10, 2, 1), (3, 10, 3, 5),  (3, 11, 2, 1), (3, 11, 3, 5),  (3, 12, 2, 1), (3, 12, 3, 5); -- survey 3 completely answered