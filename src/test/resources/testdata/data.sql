INSERT INTO user_group (id, name) VALUES
(1, 'user group 1'), (2, 'user group 2'), (3, 'user group 3');

INSERT INTO users (id, email) VALUES
(1, 'admin@purple.com'), (2, 'supervisor1@purple.com'), (3, 'employee1@purple.com');

INSERT INTO grouped_users (user_id, group_id) VALUES
(2, 1), (3, 1), (2, 2);

INSERT INTO form (id, title, isEditable) VALUES
(1, 'form1', true), (2, 'form2', true), (3, 'form3', true);

INSERT INTO survey (id, form_id, creator_id) VALUES
(1, 1, 2), (2, 1, 2);

INSERT INTO question (id, text) VALUES
(1, 'question1 from1'), (2, 'question2form1and2'), (3, 'question3form2'), (4, 'question5noForm');

INSERT INTO FORM_QUESTION (form_id, question_id, number) VALUES
(1, 1, 1), (1, 2, 2), (2, 1, 1);

INSERT INTO answer (survey_id, responder_id, question_id, value) VALUES
(1, 3, 1, 131), (1, 3, 2, 132), (2, 3, 1, 231);

--
-- -- question 1 in fqg 1, question 2 into both fqg 1 and 2
-- INSERT INTO GROUP_QUESTION (id, FORM_QUESTION_GROUP_ID, QUESTION_ID, number) VALUES
-- (1, 1, 1, 1), (2, 1, 2, 2), (3, 2, 2, 1);