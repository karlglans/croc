import React from 'react';
import PropTypes from 'prop-types';
import { Grid } from '@material-ui/core';

import QuestionList from './QuestionList';

class Layout extends React.Component {
  render() {
    const { surveyId, survey, isLoading } = this.props;
    const questions = isLoading ? []: survey.form.questions;
    const answersSum = isLoading ? []: survey.answersSum;
    return (
      <Grid container style={{ maxWidth: 1000 }}>
        <Grid item sm={12} md={12}>
          <QuestionList
            surveyId={surveyId}
            questions={questions}
            answersSum={answersSum}
          />
        </Grid>
      </Grid>
    )}
};

Layout.propTypes = {
  surveyId: PropTypes.string.isRequired,
  survey: PropTypes.object,
  isLoading: PropTypes.bool.isRequired
};

export default Layout;