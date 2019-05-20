import React from 'react';

import AnswerableQuestion from './AnswerableQuestion';

const conmbine = (questions, answers) => {
  let questionMap = {};
  questions.forEach(q => questionMap[q.id] = q)
  answers.forEach( a => {
    if (a.questionId && questionMap[a.questionId]) {
      questionMap[a.questionId].answer = a;
    }
  });
}


const QuestionList = props => {
  const { survey, answers } = props;
  const questions = survey.form.questions;
  conmbine(questions, answers);
  return (
    <div>
      {questions.map(question => (
        <AnswerableQuestion
          question={question}
          surveyId={survey.id}
          key={question.id} />
      ))}
    </div>
  );
}

export default QuestionList;