import React from 'react';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';

import OngoingSurveyList from '../../../../components/OngoingSurveyList';

const GET_SURVEYS_DATA = gql`
  {
    surveys(status: ONGOING) {
      id
      name
    }
  }
`;

const OngoingSurveys = props => (
  <Query query={GET_SURVEYS_DATA} >
    {({ loading, error, data }) => {
      if (error) return `Error! ${error.message}`;
      return (
        <OngoingSurveyList
          isLoading={loading}
          surveys={data.surveys}
          linkPrefix={'/survey'}
        />
      );
    }}
  </Query>
);

export default OngoingSurveys;
