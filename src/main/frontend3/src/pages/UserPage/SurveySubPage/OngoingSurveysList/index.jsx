import React from 'react';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';

import OngoingSurveyList from '../../../../components/OngoingSurveyList';

// will get surveys for this client only (since user has role: user)
const GET_ONGOING_SURVEYS_FOR_USER = gql`
  {
    surveys(status: ONGOING) {
      id
      name
    }
  }
`;

const OngoingSurveys = props => (
  <Query query={GET_ONGOING_SURVEYS_FOR_USER} >
    {({ loading, error, data }) => {
      if (error) return `Error! ${error.message}`;
      if (loading) {
        return null;
      }
      return (
        <OngoingSurveyList
          isLoading={loading}
          surveys={data.surveys}
          linkPrefix={'/surveys/ongoing'}
        />
      );
    }}
  </Query>
);

export default OngoingSurveys;
