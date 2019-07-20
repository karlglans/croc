import React from 'react';
import { Grid } from '@material-ui/core';

import UserTable from './UserTable';

const Layout = () => {
  return (
    <Grid container style={{ maxWidth: 1000 }} spacing={4}>
        <Grid item md={8} sm={12}>
          <UserTable />
        </Grid>
        <Grid item md={4}>
          Userinfo
        </Grid>
    </Grid>
  )
};

export default Layout;