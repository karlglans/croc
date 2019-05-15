import React from 'react';
import PropTypes from 'prop-types';
import SurveyListItem from './SurveyListItem'
import { withRouter } from "react-router-dom";
import { List } from '@material-ui/core';

const SurveyList = props => {
  const { match, surveys, userGroups } = props;
  const surveyPathId = match && match.params && match.params.surveyId ?
    Number(match.params.surveyId) : 0;
  
  return (
    <List>
      <React.Fragment>
        { props.isLoading && (
          <div>loading...</div>
        )}
        {
          !props.isLoading && surveys && surveys.map(survey => (
            <SurveyListItem
              key={survey.id}
              survey={survey}
              userGroups={userGroups}
              linkSuffix={props.linkSuffix}
              isSelected={surveyPathId === Number(survey.id)}
            />
          ))
        }
      </React.Fragment>
    </List>
  );
};

SurveyList.propTypes = {
  surveys: PropTypes.array,
  isLoading: PropTypes.bool.isRequired,
  linkSuffix: PropTypes.string.isRequired,
  isEditable: PropTypes.bool.isRequired,
};

export default withRouter(SurveyList);
