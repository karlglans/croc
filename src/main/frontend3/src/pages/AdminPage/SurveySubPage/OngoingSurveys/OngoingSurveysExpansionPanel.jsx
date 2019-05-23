import React from 'react';
import PropTypes from 'prop-types';
import { withRouter } from "react-router-dom";

import { ExpansionPanel, ExpansionPanelSummary, ExpansionPanelDetails } from '@material-ui/core';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';

import SurveyHeader from '../components/SurveyHeader'
import OngoingSurvey from './OngoingSurvey';

const ExpandableSurveyList = props => {
  const { surveys, userGroups } = props;
  
  return (
    <React.Fragment>
      { props.isLoading && (
        <div>loading...</div>
      )}
      <div style={{ width: '100%'}} >
        {
          !props.isLoading && surveys && surveys.map(survey => 
            {
              return (
                <ExpansionPanel key={survey.id} >
                  <ExpansionPanelSummary expandIcon={<ExpandMoreIcon />}>
                    {/* <Typography>Expansion Panel 1</Typography> */}
                    <SurveyHeader survey={survey} />
                  </ExpansionPanelSummary>
                  <ExpansionPanelDetails>
                    <OngoingSurvey survey={survey} userGroups={userGroups} />
                  </ExpansionPanelDetails>
                </ExpansionPanel>
              )
            }
          )
        }
      </div>
    </React.Fragment>
  );
};

ExpandableSurveyList.propTypes = {
  surveys: PropTypes.array,
  userGroups: PropTypes.array,
  isLoading: PropTypes.bool.isRequired,
  linkSuffix: PropTypes.string.isRequired,
  isEditable: PropTypes.bool.isRequired,
};

export default withRouter(ExpandableSurveyList);