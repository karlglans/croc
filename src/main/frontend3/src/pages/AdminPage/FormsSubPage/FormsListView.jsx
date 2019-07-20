import React from 'react';
import { Grid } from '@material-ui/core';

import MiniFormQuestionList from './MiniFormQuestionList';
import FormsList from './FormList';

const FormsListView = () => (
  <Grid container spacing={2}>
    <Grid item sm={12} md={6} lg={5}>
      <FormsList/>
    </Grid>
    <Grid item sm={12} md={6} lg={5}>
      <MiniFormQuestionList/>
    </Grid>
  </Grid>
);

export default FormsListView;
