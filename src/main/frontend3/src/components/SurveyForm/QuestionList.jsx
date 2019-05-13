import React from 'react';

import AnswerableQuestion from './AnswerableQuestion';

const conmbine = (questions, answers) => {
  let questionMap = {};
  questions.map( q => questionMap[q.id] = q );


  answers.forEach( a => {
    if (a.questionId && questionMap[a.questionId]) {
      questionMap[a.questionId].answer = a;
    }
  });

  console.log(questionMap);
}


const QuestionList = props => {
  const { survey, answers } = props;
  const questions = survey.form.questions;

  console.log(questions);
  console.log(answers);
  conmbine(questions, answers);
  return (
    <div>
      {questions.map(question => (<AnswerableQuestion question={question} key={question.id}/>) )}
    </div>
  );
}

export default QuestionList;