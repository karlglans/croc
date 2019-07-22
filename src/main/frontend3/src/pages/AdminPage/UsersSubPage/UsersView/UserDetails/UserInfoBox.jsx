import React from 'react';
import { makeStyles } from '@material-ui/core/styles';

import { Paper } from '@material-ui/core';

const useStyles = makeStyles(theme => ({
  root: {
    padding: theme.spacing(3, 2),
    width: '100%'
  },
}));

const UsersInfoBox = ({ user }) => {
  const email = user ? user.email : 'no select';
  // const userId = user ? user.email : 'no select';
  const classes = useStyles();
  return (
    <Paper className={classes.root}>
      Email: {email}
    </Paper>
  )
};

export default UsersInfoBox;