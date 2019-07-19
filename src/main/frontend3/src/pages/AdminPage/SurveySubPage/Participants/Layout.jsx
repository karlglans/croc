import React from 'react';
import PropTypes from 'prop-types';
import { Grid } from '@material-ui/core';

// import PartisipantList from './ParticipantList';
import SortedParticipantList from './SortedParticipantList';
import AddParticipants from '../components/AddParticipants'

// <PartisipantList participants={participants} isLoading={isLoading} />

const Layout = ({ surveyId, survey, isLoading }) => {
    const participants = survey ? survey.participants : undefined;
    // const surveyId = survey ? survey.id : 0;
    return (
      <Grid container style={{ maxWidth: 1000 }} spacing={4}>
        <Grid item md={8}>
          <SortedParticipantList participants={participants} />
        </Grid>
        <Grid item md={4}>
          <AddParticipants surveyId={surveyId} /> 
        </Grid>
      </Grid>
    )
};

Layout.propTypes = {
  surveyId: PropTypes.string.isRequired,
  survey: PropTypes.object,
  isLoading: PropTypes.bool.isRequired
};

export default Layout;