import React from 'react';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';

import SurveyList from '../SurveyList';

const GET_SURVEYS_DATA = gql`
  {
    surveys(status: CLOSED) {
      id
    }
  }
`;

const ClosedSurveys = props => {
  return (
    <Query query={GET_SURVEYS_DATA} >
      {({ loading, error, data }) => {
        if (error) return `Error! ${error.message}`;
        return (
          <SurveyList surveys={data.surveys} isLoading={loading} />
        );
      }}
    </Query>
    )
};

export default ClosedSurveys;
