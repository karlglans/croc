import React from 'react';
import { Grid } from '@material-ui/core';
import EditForm from './EditForm';

const FormsListView = () => (
  <Grid container>
    <Grid item sm={12} md={6} lg={5}>
      <EditForm />
    </Grid>
    <Grid item sm={12} md={6} lg={5}>
      question list
    </Grid>
</Grid>
);

export default FormsListView;