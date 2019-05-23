import React from 'react';
import PropTypes from 'prop-types';

const SurveyHeader = props => {
  return (
    <div>
      Ongoing : {props.survey.name} {props.survey.id}
    </div>
  )
};

SurveyHeader.propTypes = {
  survey: PropTypes.object.isRequired
};

export default SurveyHeader;