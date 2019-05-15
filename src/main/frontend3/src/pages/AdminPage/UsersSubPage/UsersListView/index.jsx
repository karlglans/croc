import React from 'react';
import { Paper } from '@material-ui/core';
import UsersListViewLayout from './UsersListViewLayout';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';

const GET_USERS = gql`
  {
    users {
      id
      email
    }
  }
`;

const UsersListView = props => {
  return (
    <Query query={GET_USERS} >
      {({ loading, error, data }) => {
        return (
          <Paper >
            <UsersListViewLayout users={data.users} isLoading={loading}  />
          </Paper>
        );
      }}
    </Query>
  )
};

export default UsersListView;
