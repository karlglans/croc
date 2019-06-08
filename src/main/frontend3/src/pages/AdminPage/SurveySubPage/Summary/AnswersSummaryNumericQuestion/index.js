import React from 'react';
import PropTypes from 'prop-types';

const AnswerNumeric = ({number, maxCount, count}) => {
  const cx = number * 100 + 50;
  const maxheight = 30;
  const paddingbottom = 2;
  const height = (maxheight * count) / maxCount;
  const diffToBottom = maxheight - height;
  return (
    <rect width={20} height={height + paddingbottom} x={cx} y={15 + diffToBottom} />
  );
}

const AnswersSummaryNumericQuestion = props => {
  const { content } = props;
  const maxCount = Math.max(...content.count);
  return (<svg width={650} height={60} viewBox="0 0 650 60" style={{backgroundColor: '#ddd'}}>
    {content.count.map( 
      (count, idx) => <AnswerNumeric key={idx} number={idx} maxCount={maxCount} count={count} /> )}
  </svg>);
}

AnswersSummaryNumericQuestion.propTypes = {
  content: PropTypes.object.isRequired
};


export default AnswersSummaryNumericQuestion;
