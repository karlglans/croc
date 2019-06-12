import React from 'react';
import PropTypes from 'prop-types';
import { Paper } from '@material-ui/core';
import colors from '../../../../constants/colors';

import QuestionOrdering from './QuestionOrdering';

const styles = {
  container: {
    display: 'flex',
    justifyContent: 'space-between',
    minHeight: 150,
    marginBottom: 20,
  },
  arrowContainer: {
    flexGrow: 0,
    display: 'inline-block',
    backgroundColor: '#66ff66',
    width: 48,
    minWidth: 40,
    minHeight: 100,
  },
  paper: {
    flexGrow: 1000,
    padding: 10,
  },
}

const Question = props => {
  const hasSelectedThisQuestion = props.question.id === props.editQuestionId;
  const { number, formId, question, isLastQuestion } = props;
  return (
    <div style={styles.container} >
      <div style={styles.arrowContainer}>
        <QuestionOrdering
          number={number}
          formId={formId}
          isLastQuestion={isLastQuestion}
          questionId={question.id} />
      </div>
      <Paper style={{backgroundColor:
          hasSelectedThisQuestion ? colors.select1BgColor : 'white', ...styles.paper}}
          onClick={() => {
            if (hasSelectedThisQuestion) {
              props.setEditQuestionId(undefined);
            } else {
              props.setEditQuestionId(question.id);
            }
          }}
      >
        {props.question.text}
        <br />
        QuestionType: {props.question.questionType}
      </Paper>
    
    </div>
  )};

Question.propTypes = {
  question: PropTypes.object.isRequired,
  number: PropTypes.number.isRequired,
  formId: PropTypes.string.isRequired,
  editQuestionId: PropTypes.string,
  setEditQuestionId: PropTypes.func.isRequired,
  isLastQuestion: PropTypes.bool.isRequired
};

export default Question;
