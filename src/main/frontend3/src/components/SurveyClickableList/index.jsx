import React from 'react';
import PropTypes from 'prop-types';
import { Link } from "react-router-dom";
import { List, ListItem } from '@material-ui/core';

const SurveyListItem = props => {
  const { survey, linkPrefix } = props;
  const clickPath = `${linkPrefix}/${survey.id}`;  // clickPath = `${linkPrefix}/${survey.id}`
  return (
    <ListItem button component={Link} to={clickPath}>
      {survey.name}
    </ListItem>
  );
}

const SurveyClickableList = props => {
  const { surveys, linkPrefix } = props;
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
              linkPrefix={linkPrefix}
            />
          ))
        }
      </React.Fragment>
    </List>
  );
};

SurveyClickableList.propTypes = {
  surveys: PropTypes.array,
  isLoading: PropTypes.bool.isRequired,
  // linkSuffix: PropTypes.string.isRequired,
  linkPrefix: PropTypes.string.isRequired
};

export default SurveyClickableList;
