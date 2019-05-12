import React from 'react';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';

import HandleAddParticipants from './HandleAddParticipants';

const GET_USERGROUP_DATA = gql`
  {
    userGroups {
      id
      name
    }
  }
`;

const AddParticipants = props => (
  <Query query={GET_USERGROUP_DATA} >
    {({ loading, error, data }) => {
      if (error) return `Error! ${error.message}`;
      if (loading) {
        return (
          <div>loading...</div>
        );
      }
      return (
        <HandleAddParticipants userGroups={data.userGroups} surveyId={props.surveyId} />
      );
    }}
  </Query>
);


export default AddParticipants;