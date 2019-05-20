import React from 'react';
import PropTypes from 'prop-types';
import { Paper } from '@material-ui/core';

const style = {
  padding: 10,
  marginBottom: 20,
}

const Question = props => (
  <Paper style={style}>
    {props.number} - {props.question.text}
  </Paper>
);

Question.propTypes = {
  question: PropTypes.object.isRequired,
  number: PropTypes.number.isRequired
};

export default Question;
