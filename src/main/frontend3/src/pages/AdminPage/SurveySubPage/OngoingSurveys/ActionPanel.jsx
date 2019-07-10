import React from 'react';
import clsx from 'clsx';
import { Link } from 'react-router-dom';
import { Button, Icon } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import SaveIcon from '@material-ui/icons/Save';

const useStyles = makeStyles(theme => ({
  button: {
    margin: theme.spacing(2),
  },
  leftIcon: {
    marginRight: theme.spacing(1),
  },
  rightIcon: {
    marginLeft: theme.spacing(1),
  },
  iconSmall: {
    fontSize: 18,
  },
}));

const ActionPanel = props => {
  const { surveyId } = props;
  const classes = useStyles();
  return (
    <React.Fragment>
      <Button
        size='small'
        color="primary"
        variant='contained'
        to={'/admin/surveys/results/' + surveyId}
        component={Link}
        className={classes.button}
        >
          View Results
          <SaveIcon className={clsx(classes.leftIcon, classes.iconSmall)} />
      </Button>
      <Button
        color="primary"
        size='small'
        variant='contained'
        className={classes.button}
        >
          Close Survey
      </Button>
    </React.Fragment>
  );
}

export default ActionPanel;