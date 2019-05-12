import React from 'react';
import { Grid } from '@material-ui/core';

import UserList from './UserList';

const UsersListViewLayout = props => (
  <Grid container spacing={24}>
    <Grid item sm={12} md={6} lg={5}>
      <UserList users={props.users} isLoading={props.isLoading} />
    </Grid>
    <Grid item sm={12} md={6} lg={5}>
      section 2
    </Grid>
</Grid>
);

export default UsersListViewLayout;
