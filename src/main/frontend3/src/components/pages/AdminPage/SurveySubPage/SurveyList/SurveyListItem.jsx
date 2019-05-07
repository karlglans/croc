import React from 'react';
import PropTypes from 'prop-types';
import { Link } from "react-router-dom";
import { ListItem, Paper } from '@material-ui/core';
import Details from './Details';

const style = {
  padding: 30,
  paddingTop: 25,
  marginBottom: 0,
  width: '100%',
}

const SurveyListItem = props => {
  const { userGroups } = props;
  const surveyId = props.survey ? Number(props.survey.id) : 0
  const clickPath = `${props.linkSuffix}/${props.survey.id}/inspect`
  if (!props.isSelected) {
    return (
      <ListItem component={Link} to={clickPath} >
        <Paper style={style}>
          {props.survey.name}
        </Paper>
      </ListItem>
    )
  } else {
    return (
      <ListItem >
        <Paper style={style}>
          <h3>{props.survey.name}</h3>
          {props.isSelected && (<Details surveyId={surveyId} userGroups={userGroups} />)}
        </Paper>
      </ListItem>
    )
  }
};

SurveyListItem.propTypes = {
  survey: PropTypes.object.isRequired,
  isSelected: PropTypes.bool.isRequired,
  linkSuffix: PropTypes.string.isRequired
};

export default SurveyListItem;