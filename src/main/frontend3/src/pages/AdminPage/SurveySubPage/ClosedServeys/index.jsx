import React from 'react';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';

import SemiClosableSurveyList from '../components/SemiClosableSurveyList';

const GET_SURVEYS_DATA = gql`
  {
    surveys(status: CLOSED) {
      id
    }
  }
`;

const ClosedSurveys = () => (
  <Query query={GET_SURVEYS_DATA} >
    {({ loading, error, data }) => {
      if (error) return `Error! ${error.message}`;
      return (
        <SemiClosableSurveyList
          surveys={data.surveys}
          isLoading={loading}
          linkSuffix={'/admin/surveys/closed'}
          isEditable={false}
        />
      );
    }}
  </Query>
);

export default ClosedSurveys;
