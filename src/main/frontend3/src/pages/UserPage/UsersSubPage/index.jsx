import React from 'react';
import { withStyles } from '@material-ui/core/styles';

const styles = theme => ({
  root: {
    display: 'flex',
  },
  halfScreen: {
    flex: 1
  },
  content: {
    flexGrow: 1,
    padding: theme.spacing.unit * 3,
  },
  toolbar: theme.mixins.toolbar,
});

const UsersSubPage = (props) => {
  const { classes } = props;
  return (
    <React.Fragment>
      <div className={classes.toolbar} />
      <p>no content yet</p>
    </React.Fragment>
  )
};


export default withStyles(styles)(UsersSubPage);