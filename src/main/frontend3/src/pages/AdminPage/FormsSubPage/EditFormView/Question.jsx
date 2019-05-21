import React from 'react';
import PropTypes from 'prop-types';
import { Paper } from '@material-ui/core';
import colors from '../../../../constants/colors';

const style = {
  padding: 10,
  marginBottom: 20,
}

const Question = props => {
  const hasSelectedThisQuestion = props.question.id === props.editQuestionId;
  return (
    <Paper style={{backgroundColor:
        hasSelectedThisQuestion ? colors.select1BgColor : 'white', ...style}}
        onClick={() => {
          if (hasSelectedThisQuestion) {
            props.setEditQuestionId(undefined);
          } else {
            props.setEditQuestionId(props.question.id);
          }
        }}
    >
      {props.number} - {props.question.text}
    </Paper>
  )};

Question.propTypes = {
  question: PropTypes.object.isRequired,
  number: PropTypes.number.isRequired,
  editQuestionId: PropTypes.string,
  setEditQuestionId: PropTypes.func.isRequired
};

export default Question;
