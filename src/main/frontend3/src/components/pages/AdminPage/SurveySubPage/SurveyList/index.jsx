import React from 'react';
import SurveyListItem from './SurveyListItem'

const SurveyList = props => (
  <React.Fragment>
    { props.isLoading && (
      <div>loading..</div>
    )}
    {!props.isLoading && props.surveys && props.questions.map(survey => (
      <SurveyListItem key={survey.id} survey={survey} />
    ))}
  </React.Fragment>
);

export default SurveyList;