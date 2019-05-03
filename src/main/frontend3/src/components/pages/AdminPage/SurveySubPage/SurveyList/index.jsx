import React from 'react';
import PropTypes from 'prop-types';
import SurveyListItem from './SurveyListItem'

const SurveyList = props => (
  <React.Fragment>
    { props.isLoading && (
      <div>loading..</div>
    )}
    {!props.isLoading && props.surveys && props.surveys.map(survey => (
      <SurveyListItem key={survey.id} survey={survey} />
    ))}
  </React.Fragment>
);

SurveyList.propTypes = {
  surveys: PropTypes.array,
  isLoading: PropTypes.bool.isRequired,
};

export default SurveyList;
