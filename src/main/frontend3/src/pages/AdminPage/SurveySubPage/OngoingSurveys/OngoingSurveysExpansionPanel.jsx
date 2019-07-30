import React from 'react';
import PropTypes from 'prop-types';
import { withRouter } from "react-router-dom";

import { ExpansionPanel, ExpansionPanelSummary, ExpansionPanelDetails } from '@material-ui/core';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';

import withLoadingIndicator from '../../../../hocs/withLoadingIndicator';
import layoutInfo from '../../../../constants/layout';
import SurveyHeader from '../components/SurveyHeader'
import OngoingSurvey from './OngoingSurvey';


class ExpandableSurveyList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {loadedSurveys: []}; // an array of Survey Ids that has been expanded at some point
    this.handelExpand = this.handelExpand.bind(this);
  }

  static checkExistense = (map, key) => !!map.find(x => x === key)

  handelExpand(event, isExp, surveyId) {
    const hasBeenExpanededBefore = ExpandableSurveyList.checkExistense(this.state.loadedSurveys, surveyId);
    if(!hasBeenExpanededBefore) {
      const loadedSurveys = [ ...this.state.loadedSurveys, surveyId ];
      this.setState({loadedSurveys: loadedSurveys});
    }
  }

  render() {
    const { surveys, userGroups } = this.props;
    return (
      <React.Fragment>
        <div style={{ width: layoutInfo.innerPageStandardWidth}} >
          {
            surveys && surveys.map(survey => 
              {
                // this property will prevent the panel from loading its graphql before it has been expanded.
                const hasBeenExpandedBefore = ExpandableSurveyList.checkExistense(this.state.loadedSurveys, survey.id);
                return (
                  <ExpansionPanel key={survey.id} onChange={ (e, isExp) => this.handelExpand(e, isExp, survey.id) }>
                    <ExpansionPanelSummary expandIcon={<ExpandMoreIcon />}>
                      {/* <Typography>Expansion Panel 1</Typography> */}
                      <SurveyHeader survey={survey} />
                    </ExpansionPanelSummary>
                    <ExpansionPanelDetails>
                      <OngoingSurvey survey={survey} userGroups={userGroups} hasBeenExpanded={hasBeenExpandedBefore} />
                    </ExpansionPanelDetails>
                  </ExpansionPanel>
                )
              }
            )
          }
        </div>
      </React.Fragment>
    );
  }
};

ExpandableSurveyList.propTypes = {
  surveys: PropTypes.array,
  userGroups: PropTypes.array,
  linkSuffix: PropTypes.string.isRequired,
  isEditable: PropTypes.bool.isRequired,
};

export default withLoadingIndicator(withRouter(ExpandableSurveyList));