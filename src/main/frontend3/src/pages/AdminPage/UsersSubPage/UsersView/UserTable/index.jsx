import React from 'react';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';
import Table from './Table';

const GET_USERS = gql`
  {
    users {
      id
      email
    }
  }
`;

const UsersView = props => {
  return (
    <Query query={GET_USERS} >
      {({ loading, error, data }) => {
        return (
          <Table users={data.users} isLoading={loading} />
        );
      }}
    </Query>
  )
};

export default UsersView;