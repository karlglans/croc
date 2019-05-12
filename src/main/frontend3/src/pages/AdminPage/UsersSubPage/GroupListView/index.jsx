import React from 'react';
import { Paper } from '@material-ui/core';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';
import GroupListViewLayout from './GroupListViewLayout';

const GET_GROUPS = gql`
  {
    userGroups {
      id
      name
    }
  }
`;

const GroupsListView = props => {
  return (
    <Query query={GET_GROUPS} >
      {({ loading, error, data }) => {
        return (
          <Paper >
            <GroupListViewLayout userGroups={data.userGroups} isLoading={loading}/>
          </Paper>
        );
      }}
    </Query>
  )
};

export default GroupsListView;
