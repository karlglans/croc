import React from 'react';
import PropTypes from 'prop-types';

const styles = {
  container: {
    display: 'flex',
    width: '100%'
  },
}

const SurveyHeader = props => {
  const { survey } = props;
  const { summary } = survey;
  const compleatness = summary.nbParticipants !== 0 ?
    Math.floor(100 * summary.nbAnsweringParticipants / summary.nbParticipants) : 0;

  return (
    <div style={styles.container}>
      <div style={{ flex: 1 }}> {survey.name} </div>
      <div style={{ flex: 1, textAlign: 'right', marginRight: '1em', color: 'green' }}> {compleatness} % </div>
    </div>
  )
};

SurveyHeader.propTypes = {
  survey: PropTypes.object.isRequired
};

export default SurveyHeader;