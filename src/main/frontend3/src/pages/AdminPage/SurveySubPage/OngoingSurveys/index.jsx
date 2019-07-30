import React from 'react';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';

import OngoingSurveysExpansionPanel from './OngoingSurveysExpansionPanel';

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

const OngoingSurveys = () => (
  <Query query={GET_SURVEYS_DATA} >
    {({ loading, error, data }) => {
      const surveys = data ? data.surveys : [];
      const userGroups = data ? data.userGroups : [];
      return (
        <OngoingSurveysExpansionPanel
          surveys={surveys}
          userGroups={userGroups}
          isLoading={loading}
          loadingErrors={error}
          minHeight={300}
          linkSuffix={'/admin/surveys/ongoing'}
          isEditable={true}
        />
      );
    }}
  </Query>
);

export default OngoingSurveys;
