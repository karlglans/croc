import React from 'react';
import { Paper } from '@material-ui/core';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';

const GET_GROUPS = gql`
  {
    groups {
      id
    }
  }
`;

const UsersListView = props => {
  return (
    <Query query={GET_GROUPS} >
      {({ loading, error, data }) => {
        return (
          <Paper >
            groups
          </Paper>
        );
      }}
    </Query>
  )
};

export default UsersListView;