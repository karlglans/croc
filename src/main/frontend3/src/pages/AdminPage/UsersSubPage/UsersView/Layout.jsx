import React from 'react';
import PropTypes from 'prop-types';
import { Grid, Paper } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';

import UserTable from './UserTable';
import UserDetails from './UserDetails';
import UserGroups from './UserGroups';

const useStyles = makeStyles(theme => ({
  container: {
    padding: theme.spacing(3, 2)
  },
}));

const Layout = ({ handleSelectUser, userId, addUserToGroup, removeUserFromGroup }) => {
  const classes = useStyles();
  return (
    <Grid container style={{ maxWidth: 1000 }} spacing={2}>
      <Grid item md={8} sm={12}>
        <UserTable handleSelectUser={handleSelectUser} />
      </Grid>
      <Grid item md={4} xs={12}>
        <Grid container spacing={2}>
          <Grid item xs={12}>
            {userId && (
              <Paper className={classes.container}>
                <UserDetails userId={userId} />
              </Paper>
            )}
          </Grid>
          <Grid item xs={12}>
            <Paper className={classes.container}>
              <UserGroups
                userId={userId}
                addUserToGroup={addUserToGroup}
                removeUserFromGroup={removeUserFromGroup} />
            </Paper>
          </Grid>
        </Grid>
      </Grid>
    </Grid>
  )
};

Layout.propTypes = {
  handleSelectUser: PropTypes.func.isRequired,
  addUserToGroup: PropTypes.func.isRequired,
  removeUserFromGroup: PropTypes.func.isRequired,
  userId: PropTypes.string,
};

export default Layout;