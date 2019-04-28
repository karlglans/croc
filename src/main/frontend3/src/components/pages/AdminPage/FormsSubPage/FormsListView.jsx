import React from 'react';
import { Grid } from '@material-ui/core';

import FormsList from '../../../FormsList';
import QuestionList from '../../../QuestionList';

const FormsListView = () => (
  <Grid container>
  <Grid item sm={12} md={6} lg={5}>
    <FormsList/>
  </Grid>
  <Grid item sm={12} md={6} lg={5}>
    <QuestionList/>
  </Grid>
</Grid>
);

export default FormsListView;
