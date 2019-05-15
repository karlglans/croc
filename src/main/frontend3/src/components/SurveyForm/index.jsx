import React from 'react';
import { withRouter } from "react-router-dom";
import gql from 'graphql-tag';
import { Query } from 'react-apollo';

import QuestionList from './QuestionList';

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
          number
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
    <Query query={GET_SURVEYS_DATA} variables={{surveyId, userId: 3}} >
    {({ loading, error, data }) => {
      if (error) return `Error! ${error.message}`;
      if (loading) {
        return null;
      }
      return (
        <QuestionList
          isLoading={loading}
          survey={data.survey}
          answers={data.answers}
        />
      );
    }}
  </Query>
  );
}

export default withRouter(SurveyFrom);
