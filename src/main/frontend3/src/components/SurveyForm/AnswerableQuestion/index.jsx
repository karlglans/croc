import React from 'react';
import PropTypes from 'prop-types';
import { Card, CardContent, Typography  } from '@material-ui/core';

import Answer from './Answer';

const AnswerableQuestion = props => {
  const { question } = props;
  return (
    <Card style={{marginBottom: 10}}>
      <CardContent>
        <Typography color="textSecondary" gutterBottom>
          Question {question.number}: {question.text}
        </Typography>
        {!!question.answer && (
          <Answer color="textSecondary" answer={question.answer} />
        )}
      </CardContent>
    </Card>
  );
}

AnswerableQuestion.propTypes = {
  question: PropTypes.object.isRequired,
};

export default AnswerableQuestion;
