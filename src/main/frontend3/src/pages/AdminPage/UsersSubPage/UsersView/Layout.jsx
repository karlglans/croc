import React from 'react';
import PropTypes from 'prop-types';
import { Grid } from '@material-ui/core';

import UserTable from './UserTable';
import UserDetails from './UserDetails';
import UserGroups from './UserGroups';

const Layout = ({ handleSelectUser, userId, addUserToGroup }) => {
  return (
    <Grid container style={{ maxWidth: 1000 }} spacing={2}>
        <Grid item md={8} sm={12}>
          <UserTable handleSelectUser={handleSelectUser} />
        </Grid>
        <Grid item md={4}>
          <UserDetails userId={userId} />
          <UserGroups userId={userId} addUserToGroup={addUserToGroup}/>
        </Grid>
    </Grid>
  )
};

Layout.propTypes = {
  handleSelectUser: PropTypes.func.isRequired,
  addUserToGroup: PropTypes.func.isRequired,
  userId: PropTypes.string,
};

export default Layout;