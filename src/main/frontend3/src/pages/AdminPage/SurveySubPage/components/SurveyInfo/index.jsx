import React from 'react';
import PropTypes from 'prop-types';

const styles = {
  container: {
    display: 'grid',
    gridTemplateColumns: 'max-content auto'
  },
  dt: {
    gridColumnStart: 1
  },
  dd: {
    gridColumnStart: 2
  }
}

const SurveyInfo = ({ survey }) => {
  const { summary } = survey;
  return (
    <div>
      <h2>Survey Info</h2>
      <dl style={styles.container}>
        <dt style={styles.dt}>Start date</dt>
        <dd style={styles.dd}>2019-01-02</dd>
        <dt style={styles.dt}>Number of participants</dt>
        <dd style={styles.dd}>{summary.nbParticipants}</dd>
        <dt style={styles.dt}>Answering participants</dt>
        <dd style={styles.dd}>{summary.nbAnsweringParticipants}</dd>
      </dl>
    </div>
  )
};

SurveyInfo.propTypes = {
  survey: PropTypes.object.isRequired
};

export default SurveyInfo;