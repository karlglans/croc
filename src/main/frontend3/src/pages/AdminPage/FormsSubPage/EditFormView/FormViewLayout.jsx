import React from 'react';
import PropTypes from 'prop-types';
import { Grid } from '@material-ui/core';
import CreateQuestion from './CreateQuestion';
import QuestionList from './QuestionList';

const EditFormViewLayout = props => (
  <Grid container spacing={3}>
    <Grid item sm={12} md={6} lg={5}>
      <CreateQuestion
        editQuestionId={props.editQuestionId}
        form={props.form}
        setEditQuestionId={props.setEditQuestionId}/>
    </Grid>
    <Grid item sm={12} md={6} lg={5}>
      <QuestionList
        questions={props.form && props.form.questions}
        formId={props.form && props.form.id}
        editQuestionId={props.editQuestionId}
        setEditQuestionId={props.setEditQuestionId}
        isLoading={props.isLoading} />
    </Grid>
</Grid>
);

EditFormViewLayout.propTypes = {
  form: PropTypes.object,
  editQuestionId: PropTypes.string,
  setEditQuestionId: PropTypes.func.isRequired
};

export default EditFormViewLayout;
