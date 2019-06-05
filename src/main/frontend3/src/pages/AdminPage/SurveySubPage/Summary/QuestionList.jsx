import React from 'react';
import PropTypes from 'prop-types';
import { Card, CardContent } from '@material-ui/core';

// TODO: make it able to handle more types of questions
const QuestionStats = props => {
  return(
    <Card style={{ marginBottom: 20}}>
      <CardContent>
        {props.number} {props.text}
      </CardContent>
    </Card>
  );
}

const QuestionList = (props) => {
  return (
    <div>
      {props.questions.map( (q, idx) => 
        <QuestionStats key={q.id} text={q.text} number={idx + 1} />) }
    </div>
  )
};

QuestionList.propTypes = {
  questions: PropTypes.array,
  surveyId: PropTypes.number.isRequired,
};

export default QuestionList;