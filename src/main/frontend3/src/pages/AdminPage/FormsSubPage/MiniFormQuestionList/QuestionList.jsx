import React from 'react';
import PropTypes from 'prop-types';
import { List, ListItem } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';

import CreateSurveyButton from './CreateSurveyButton';
import EditFormButton from './EditFormButton';
import withLoadingIndicator from '../../../../hocs/withLoadingIndicator';

const useStyles = makeStyles(theme => ({
  root: {
    width: '100%',
    minWidth: 360
  },
}));

const QuestionsList = ({ formId, isEditable, questions }) => {
  const classes = useStyles();
  if (!formId) return null;
  return (
    <React.Fragment>
      <CreateSurveyButton formId={ formId } />
      { isEditable &&
        (<EditFormButton formId={ formId } />)
      }
      <List className={classes.root}>
        {questions.map((question, index) => (
          <ListItem button key={question.id} >
            {index + 1} : {question.text}
          </ListItem>
        ))}
      </List>
    </React.Fragment>
  );
};

QuestionsList.propTypes = {
  isEditable: PropTypes.bool.isRequired,
  formId: PropTypes.string.isRequired,
  questions: PropTypes.array.isRequired
};

export default withLoadingIndicator(QuestionsList);
