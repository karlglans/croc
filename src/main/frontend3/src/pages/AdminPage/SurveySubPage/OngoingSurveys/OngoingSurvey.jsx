import React from 'react';
import PropTypes from 'prop-types';
import { Grid } from '@material-ui/core';

import ParticipantList from '../components/ParticipantList';
import AddParticipants from '../components/AddParticipants';

class SurveyListItemClosed extends React.Component {
  render() {
    const surveyId = this.props.survey.id;
    const { hasBeenExpanded } = this.props;
    return (
      <Grid container spacing={24}>
      <Grid item sm={12} md={6}>
        Idd: {surveyId}
        { hasBeenExpanded && (
          <ParticipantList surveyId={surveyId} />) 
        }
      </Grid>
      <Grid item sm={12} md={6}>
        { hasBeenExpanded && (
          <AddParticipants surveyId={surveyId} userGroups={this.props.userGroups} />) 
        }
      </Grid>
    </Grid>
    )}
};

SurveyListItemClosed.propTypes = {
  hasBeenExpanded: PropTypes.bool.isRequired, // we want to lazy load panels
  survey: PropTypes.object.isRequired,
  userGroups: PropTypes.array.isRequired
};

export default SurveyListItemClosed;