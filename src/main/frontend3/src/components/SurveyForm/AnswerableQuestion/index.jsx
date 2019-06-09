import React from 'react';
import PropTypes from 'prop-types';
import { Card, CardContent, Typography  } from '@material-ui/core';
import * as questionTypes from '../../../constants/domain/questionTypes';

import AnswerNumeric from './AnswerNumeric';
import AnswerYesNo from './AnswerYesNo';

const AnswerableQuestion = ({ question, hasOtherMissingAnswers, survey, surveyId }) => {
  const { questionType } = question;
  return (
    <Card style = {{marginBottom: 10}}>
      <CardContent>
        <Typography color="textSecondary" gutterBottom>
          Question {question.number}: {question.text}
        </Typography>
        {questionType === questionTypes.NUMERIC && (<AnswerNumeric color = "textSecondary" 
          storedAnswer = {question.answer}
          questionId = {question.id}
          hasOtherMissingAnswers = {hasOtherMissingAnswers}
          survey = {survey}
          surveyId = {surveyId} />)}
        {questionType === questionTypes.YESNO && (<AnswerYesNo color = "textSecondary" 
          storedAnswer = {question.answer}
          questionId = {question.id}
          hasOtherMissingAnswers = {hasOtherMissingAnswers}
          survey = {survey}
          surveyId = {surveyId} />)}
      </CardContent>
    </Card>
  );
}

AnswerableQuestion.propTypes = {
  question: PropTypes.object.isRequired,
  hasOtherMissingAnswers: PropTypes.func.isRequired,
  surveyId: PropTypes.string.isRequired,
  survey: PropTypes.object.isRequired
};

export default AnswerableQuestion;
