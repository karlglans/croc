import React from 'react';
import PropTypes from 'prop-types';
import { Grid } from '@material-ui/core';
import CreateQuestion from './CreateQuestion';
// import EditQuestion from './EditQuestion';
import QuestionList from './QuestionList';

const EditFormViewLayout = props => (
  <Grid container spacing={24}>
    <Grid item sm={12} md={6} lg={5}>
      {props.editQuestionId && (
        <CreateQuestion editQuestionId={props.editQuestionId} form={props.form}/>
      )}
      {!props.editQuestionId && (
        <CreateQuestion form={props.form} />
      )}
    </Grid>
    <Grid item sm={12} md={6} lg={5}>
      <QuestionList
        questions={props.form && props.form.questions}
        editQuestionId={props.editQuestionId}
        setEditQuestionId={props.setEditQuestionId}
        isLoading={props.isLoading} />
    </Grid>
</Grid>
);

EditFormViewLayout.propTypes = {
  editQuestionId: PropTypes.string,
  setEditQuestionId: PropTypes.func.isRequired
};

export default EditFormViewLayout;
