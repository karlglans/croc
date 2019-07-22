import React from 'react';
import PropTypes from 'prop-types';
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

// NOTE: inner component do have an extra rendering
const UsersGroups = ({ userId, addUserToGroup }) => {
  return (
    <Query query={GET_GROUPS} >
      {({ loading, error, data }) => {
        const loadingGroups = loading;
        const loadedGroups = data ? data.userGroups : [];
        if (!userId) {
          return(
            <GroupSelector
              loadingGroups={loadingGroups}
              isLoading={true}
              groups={loadedGroups}
              addUserToGroup={addUserToGroup}
              user={null} />
          );
        }
        return (
          <Query query={GET_USER_GROUPS} variables={{ userId }} >
          {({ loading, error, data }) => {
            const isLoading = loading || loadingGroups;
            const user = data ? data.user : null;
            return (
              <GroupSelector
                loadingGroups={loadingGroups}
                isLoading={isLoading}
                groups={loadedGroups}
                addUserToGroup={addUserToGroup}
                user={user} />
            );
          }}
        </Query>
        );
      }}
    </Query>
  )
};

UsersGroups.propTypes = {
  userId: PropTypes.string,
  addUserToGroup: PropTypes.func.isRequired
};

export default UsersGroups;