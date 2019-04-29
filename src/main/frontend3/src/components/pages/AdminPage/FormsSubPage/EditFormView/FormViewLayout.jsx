import React from 'react';
import { Grid } from '@material-ui/core';
import EditForm from './EditForm';
import QuestionList from './QuestionList';

const EditFormViewLayout = props => (
  <Grid container spacing={24}>
    <Grid item sm={12} md={6} lg={5}>
      <EditForm form={props.form} />
    </Grid>
    <Grid item sm={12} md={6} lg={5}>
      <QuestionList questions={props.form && props.form.questions} isLoading={props.isLoading} />
    </Grid>
</Grid>
);

export default EditFormViewLayout;