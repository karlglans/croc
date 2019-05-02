import React from 'react';
import PropTypes from 'prop-types';
import { Paper } from '@material-ui/core';

const style = {
  padding: 10,
  marginBottom: 20,
}

const SurveyListItem = props => (
  <Paper style={style}>
    survey
  </Paper>
);

SurveyListItem.propTypes = {
  survey: PropTypes.object.isRequired,
};

export default SurveyListItem;