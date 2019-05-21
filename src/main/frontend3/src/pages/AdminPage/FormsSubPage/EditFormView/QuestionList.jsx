import React from 'react';
import PropTypes from 'prop-types';
import Question from './Question'

const QuestionList = props => (
  <React.Fragment>
    { props.isLoading && (
      <div>loading..</div>
    )}
    {!props.isLoading && props.questions && props.questions.map((question, number) => (
      <Question
        key={question.id}
        editQuestionId={props.editQuestionId}
        setEditQuestionId={props.setEditQuestionId}
        question={question}
        number={number + 1} />
    ))}
  </React.Fragment>
);

QuestionList.propTypes = {
  editQuestionId: PropTypes.string,
  setEditQuestionId: PropTypes.func.isRequired
};

export default QuestionList;