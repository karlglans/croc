import React from 'react';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';

import UserInfoBox from './UserInfoBox';

const GET_USER = gql`
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

const UsersDetails = ({userId}) => {
  return (
    <Query query={GET_USER} variables={{ userId }}>
      {({ loading, error, data }) => {
        return (
          <UserInfoBox user={data ? data.user: null} isLoading={loading} />
        );
      }}
    </Query>
  )
};

export default UsersDetails;