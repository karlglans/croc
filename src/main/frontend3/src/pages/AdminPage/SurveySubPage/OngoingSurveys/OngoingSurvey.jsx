import React from 'react';
import PropTypes from 'prop-types';
import { Grid } from '@material-ui/core';

import ParticipantList from '../components/ParticipantList';
import AddParticipants from '../components/AddParticipants';


const SurveyListItemClosed = props => {
  // const clickPath = `${props.linkSuffix}/${props.survey.id}/inspect`;
  return (
    <Grid container spacing={24}>
    <Grid item sm={12} md={6}>
      Id: {props.surveyId}
      <ParticipantList surveyId={props.survey.id} />
    </Grid>
    <Grid item sm={12} md={6}>
      <AddParticipants surveyId={props.survey.id} userGroups={props.userGroups} />
    </Grid>
  </Grid>
  )
};

SurveyListItemClosed.propTypes = {
  survey: PropTypes.object.isRequired,
  userGroups: PropTypes.array.isRequired
};

export default SurveyListItemClosed;