import React from 'react';
import PropTypes from 'prop-types';
import { Grid } from '@material-ui/core';

import QuestionList from './QuestionList';

class Layout extends React.Component {
  render() {
    const { surveyId, survey, isLoading } = this.props;
    const questions = isLoading ? []: survey.form.questions;
    return (
      <Grid container>
        <Grid item sm={12} md={12}>
          <QuestionList surveyId={surveyId} questions={questions} />
        </Grid>
      </Grid>
    )}
};

Layout.propTypes = {
  surveyId: PropTypes.number.isRequired,
};

export default Layout;