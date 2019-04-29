INSERT INTO users (id, email) VALUES
(1, 'admin@purple.com'), (2, 'supervisor1@purple.com'), (3, 'employee1@purple.com');

INSERT INTO form (id, title, isEditable) VALUES
(1, 'form1', false), (2, 'form2', false), (3, 'form3', false);

INSERT INTO survey (id, form_id, creator_id) VALUES
(1, 1, 2), (2, 1, 2);

INSERT INTO question (id, text) VALUES
(1, 'question1 from1'), (2, 'question2form1and2'), (3, 'question3form2'), (4, 'question5noForm');

INSERT INTO FORM_QUESTION (form_id, question_id, number) VALUES
(1, 1, 1), (1, 2, 2), (2, 1, 1);

INSERT INTO answer (survey_id, responder_id, question_id, value) VALUES
(1, 3, 1, 131), (1, 3, 2, 132), (2, 3, 1, 231);
