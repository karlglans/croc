import React from 'react';
import PropTypes from 'prop-types';
import { Grid } from '@material-ui/core';

import AddParticipants from '../components/AddParticipants';
import ActionPanel from './ActionPanel';
import SurveyInfo from '../components/SurveyInfo';

class SurveyListItemClosed extends React.Component {
  render() {
    const { hasBeenExpanded, survey } = this.props;
    const surveyId = this.props.survey.id;
    return (
      <Grid container>
        <Grid item sm={12} md={6}>
          { hasBeenExpanded && ( <SurveyInfo survey={survey} /> )}
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