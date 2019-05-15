import React from 'react';
import PropTypes from 'prop-types';
import { Grid, Paper } from '@material-ui/core';

import ParticipantList from '../ParticipantList';
import AddParticipants from '../components/AddParticipants'
import StartSurvey from './StartSurvey';
import SurveyInfo from './SurveyInfo';

const style = {
  padding: 30,
  paddingTop: 25,
  marginBottom: 0,
  width: '100%',
}

const Layout = props => (
  <Paper style={style}>
    <Grid container spacing={24}>
      <Grid item sm={12} md={6}>
        <SurveyInfo surveyId={props.surveyId} />
        <ParticipantList surveyId={props.surveyId} />
      </Grid>
      <Grid item sm={12} md={6}>
        <AddParticipants surveyId={props.surveyId} />
        <StartSurvey surveyId={props.surveyId} />
      </Grid>
    </Grid>
  </Paper>
);

Layout.propTypes = {
  surveyId: PropTypes.number.isRequired
};

export default Layout;