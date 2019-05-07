import React from 'react';
import { Grid } from '@material-ui/core';

// import FormsList from '../../../FormsList';
import MiniFormQuestionList from './MiniFormQuestionList';
import FormsList2 from './FormList';

const FormsListView = () => (
  <Grid container>
  <Grid item sm={12} md={6} lg={5}>
    <FormsList2/>
  </Grid>
  <Grid item sm={12} md={6} lg={5}>
    <MiniFormQuestionList/>
  </Grid>
</Grid>
);

export default FormsListView;
