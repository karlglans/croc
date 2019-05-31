import React from 'react';
import PropTypes from 'prop-types';
import { Card, CardContent, Typography  } from '@material-ui/core';

import Answer from './Answer';

const AnswerableQuestion = props => {
  const { question, hasOtherMissingAnswers, survey } = props;
  return (
    <Card style={{marginBottom: 10}}>
      <CardContent>
        <Typography color="textSecondary" gutterBottom>
          Question {question.number}: {question.text}
        </Typography>
        <Answer color="textSecondary" 
          storedAnswer={question.answer}
          questionId={question.id}
          hasOtherMissingAnswers={hasOtherMissingAnswers}
          survey={survey}
          surveyId={props.surveyId} />
      </CardContent>
    </Card>
  );
}

AnswerableQuestion.propTypes = {
  question: PropTypes.object.isRequired,
  hasOtherMissingAnswers: PropTypes.func.isRequired,
};

export default AnswerableQuestion;
