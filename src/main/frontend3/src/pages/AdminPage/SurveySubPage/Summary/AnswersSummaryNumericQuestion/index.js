import React from 'react';
import PropTypes from 'prop-types';

const AnswerNumeric = ({number, maxCount, count}) => {
  const cx = number * 100 + 50;
  const maxheight = 30;
  const paddingbottom = 2;
  const height = maxCount !== 0 ? (maxheight * count) / maxCount : 0 ;
  const diffToBottom = maxheight - height;
  return (
    <rect width={20} height={height + paddingbottom} x={cx} y={15 + diffToBottom} />
  );
}

const deafultNoAnswers = { count: [0,0,0,0,0,0] }

const AnswersSummaryNumericQuestion = ({ content }) => {
  const answersCountList = content || deafultNoAnswers;
  const maxCount = Math.max(...answersCountList.count);
  return (<svg width={650} height={60} viewBox="0 0 650 60" style={{backgroundColor: '#ddd'}}>
    {answersCountList.count.map( 
      (count, idx) => <AnswerNumeric key={idx} number={idx} maxCount={maxCount} count={count} /> )}
  </svg>);
}

AnswersSummaryNumericQuestion.propTypes = {
  content: PropTypes.object
};


export default AnswersSummaryNumericQuestion;
