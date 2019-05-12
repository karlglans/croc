import React from 'react';
import { withRouter } from "react-router-dom";

import Layout from './Layout';

const UnstartedSurveyDetails = (props) => {
  const { match } = props;
  const surveyId = match && match.params && match.params.surveyId ?
    Number(match.params.surveyId) : 0;
  return (
    <Layout surveyId={surveyId} />
  )
};

export default withRouter(UnstartedSurveyDetails);