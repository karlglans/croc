import React from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';
import { Grid, Button } from '@material-ui/core';

import ParticipantList from '../components/ParticipantList';
import AddParticipants from '../components/AddParticipants';
import ActionPanel from './ActionPanel';

class SurveyListItemClosed extends React.Component {
  render() {
    const surveyId = this.props.survey.id;
    const { hasBeenExpanded } = this.props;
    return (
      <Grid container>
        <Grid item sm={12} md={6}>
          <Button to={'/admin/surveys/results/'+surveyId} component={Link}>View Results</Button>
          { hasBeenExpanded && (
            <ParticipantList surveyId={surveyId} />) 
          }
        </Grid>
        <Grid item sm={12} md={6}>
          { hasBeenExpanded && (
              <React.Fragment>
                <ActionPanel surveyId={surveyId} />
                <AddParticipants surveyId={surveyId} userGroups={this.props.userGroups} />
              </React.Fragment>
            ) 
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