import React from 'react';
import PropTypes from 'prop-types';
import { withRouter } from "react-router-dom";
import gql from 'graphql-tag';
import { Query } from 'react-apollo';

import QuestionList from './QuestionList';
import getUserId from '../../temp/getUserId';

const GET_SURVEYS_DATA = gql`
  query($surveyId: ID!, $userId: ID!) {
    survey(id: $surveyId) {
      id
      name
      form {
        id
        title
        questions {
          id
          text
        }
      }
    }
    answers(surveyId: $surveyId, userId: $userId) {
      questionId
      value
    }
  }
`;

const SurveyFrom = props => {
  const { match } = props;
  const surveyId = match.params.surveyId ? match.params.surveyId : 1; // temp solution
  return (
    <Query query={GET_SURVEYS_DATA} variables={{surveyId, userId: getUserId()}} >
    {({ loading, error, data }) => {
      if (error) return `Error! ${error.message}`;
      if (loading) {
        return null;
      }
      return (
        <React.Fragment>
          <h2>Survey id {surveyId} </h2>
          <QuestionList
            isLoading={loading}
            survey={data.survey}
            answers={data.answers}
          />
        </React.Fragment>
      );
    }}
  </Query>
  );
};

SurveyFrom.propTypes = {
  match: PropTypes.object.isRequired
};

export default withRouter(SurveyFrom);
