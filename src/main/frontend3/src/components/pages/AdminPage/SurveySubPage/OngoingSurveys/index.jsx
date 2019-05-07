import React from 'react';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';

import SurveyList from '../SurveyList';

const GET_SURVEYS_DATA = gql`
  {
    surveys(status: ONGOING) {
      id
      name
    }
    userGroups {
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
        <SurveyList
          surveys={data.surveys}
          userGroups={data.userGroups}
          isLoading={loading}
          linkSuffix={'/admin/surveys/ongoing'}
          isEditable={true}
        />
      );
    }}
  </Query>
);

export default OngoingSurveys;
