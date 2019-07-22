import React from 'react';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';

import GroupSelector from './GroupSelector';

const GET_GROUPS = gql`
  {
    userGroups {
      id
      name
    }
  }
`;

const GET_USER_GROUPS = gql`
  query($userId: ID!) {
    user(id: $userId) {
      id
      email
      groups {
        id
      }
    }
  }
`;

const UsersGroups = ({ userId }) => {
  return (
    <Query query={GET_GROUPS} >
      {({ loading, error, data }) => {
        const loadingGroups = loading;
        const loadedGroups = data ? data.userGroups : null;
        return (
          <Query query={GET_USER_GROUPS} variables={{ userId }} >
          {({ loading, error, data }) => {
            console.log('userId', userId);
            const isLoading = loading || loadingGroups;
            const user = data ? data.user : null;
            return (
              <GroupSelector
                loadingGroups={loadingGroups}
                isLoading={isLoading}
                groups={loadedGroups}
                user={user} />
            );
          }}
        </Query>
        );
      }}
    </Query>
  )
};

export default UsersGroups;