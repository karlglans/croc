import React from 'react';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';

import SemiClosableSurveyList from '../components/SemiClosableSurveyList';

const GET_SURVEYS_DATA = gql`
  {
    surveys(status: ONGOING) {
      id
      name
      summary {
        nbParticipants
        nbAnsweringParticipants
      }
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
        <SemiClosableSurveyList
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
