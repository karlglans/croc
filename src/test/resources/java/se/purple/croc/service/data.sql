INSERT INTO users (id, email) VALUES
(1, 'admin@purple.com'), (2, 'supervisor1@purple.com'), (3, 'employee1@purple.com');

INSERT INTO form (id, title) VALUES
(1, 'form1'), (2, 'form2'), (3, 'form3');

INSERT INTO survey (id, form_id, creator_id) VALUES
(1, 1, 2), (2, 1, 2);

INSERT INTO question (id, text) VALUES
(1, 'question1 from1'), (2, 'question2form1and2'), (3, 'this is question3');

INSERT INTO FORM_QUESTION (form_id, question_id, number) VALUES
(1, 1, 1), (1, 2, 2), (2, 1, 1);

INSERT INTO answer (survey_id, responder_id, question_id, value) VALUES
(1, 3, 1, 111);
--
-- -- question 1 in fqg 1, question 2 into both fqg 1 and 2
-- INSERT INTO GROUP_QUESTION (id, FORM_QUESTION_GROUP_ID, QUESTION_ID, number) VALUES
-- (1, 1, 1, 1), (2, 1, 2, 2), (3, 2, 2, 1);