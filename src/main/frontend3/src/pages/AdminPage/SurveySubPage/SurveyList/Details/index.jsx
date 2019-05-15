import React from 'react';
import PropTypes from 'prop-types';
import { Grid } from '@material-ui/core';

import ParticipantList from '../../ParticipantList'
import AddParticipants from '../AddParticipants';

const Details = props => (
  <Grid container spacing={24}>
    <Grid item sm={12} md={6}>
      Id: {props.surveyId}
      <ParticipantList surveyId={props.surveyId} />
    </Grid>
    <Grid item sm={12} md={6}>
      <AddParticipants surveyId={props.surveyId} userGroups={props.userGroups} />
    </Grid>
  </Grid>
);

export default Details;

Details.propTypes = {
  surveyId: PropTypes.number.isRequired,
  userGroups: PropTypes.array.isRequired
};