import React from 'react';
import { Grid } from '@material-ui/core';
import CreateForm from './CreateFrom';

const FormsCreateView = () => (
  <Grid container>
    <Grid item sm={12} md={6} lg={5}>
      <CreateForm />
    </Grid>
    <Grid item sm={12} md={6} lg={5}>
    </Grid>
  </Grid>
)

export default FormsCreateView;