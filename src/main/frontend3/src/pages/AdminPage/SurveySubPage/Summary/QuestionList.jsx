import React from 'react';
import PropTypes from 'prop-types';
import { Card, CardContent } from '@material-ui/core';

import AnswersSummaryNumericQuestion from './AnswersSummaryNumericQuestion';
import AnswersSummaryYesNoQuestion from './AnswersSummaryYesNoQuestion';
import * as questionTypes from '../../../../constants/domain/questionTypes';

// TODO: make it able to handle more types of questions
const QuestionStats = props => {
  const { content } = props;
  return(
    <Card style={{ marginBottom: 20}}>
      <CardContent>
        <div style={{marginBottom: 20, padding: 15 }}>
          {props.number} {props.text} {props.text}
        </div>
        {props.questionType === questionTypes.NUMERIC && (
          <AnswersSummaryNumericQuestion
            number={props.number}
            content={content} />
        )}
        {props.questionType === questionTypes.YESNO && (
          <AnswersSummaryYesNoQuestion
            number={props.number}
            content={content} />
        )}
      </CardContent>
    </Card>
  );
}

const mapAnswerSumToQuestion = (answersSumList, questions) => {
  answersSumList.forEach( ansSum => {
    for(let qIdx = 0; qIdx < questions.length; qIdx++) {
      const question = questions[qIdx];
      if (question.id === String(ansSum.questionId)) {
        question.answerSum = ansSum;
        // NOTE: content is a json in a string.
        // Brittle soultion for now, since im avoiding making a special type for each anwerType.
        question.contentJson = JSON.parse(ansSum.content);
        break;
      }
    }
  });
}

const QuestionList = (props) => {
  mapAnswerSumToQuestion(props.answersSum, props.questions);
  return (
    <div>
      {props.questions.map( (q, idx) => 
        <QuestionStats
          key={q.id}
          text={q.text}
          questionType={q.questionType}
          content={q.contentJson}
          number={idx + 1} />
      )}
    </div>
  )
};

QuestionList.propTypes = {
  questions: PropTypes.array,
  answersSum: PropTypes.array, // json containing a summary of answers this question type
  surveyId: PropTypes.number.isRequired,
};

export default QuestionList;