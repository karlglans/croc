import React from 'react';
import Question from './Question'

const QuestionList = props => (
  <React.Fragment>
    { props.isLoading && (
      <div>loading..</div>
    )}
    {!props.isLoading && props.questions && props.questions.map(question => (
      <Question key={question.id} question={question} />
    ))}
  </React.Fragment>
);

export default QuestionList;