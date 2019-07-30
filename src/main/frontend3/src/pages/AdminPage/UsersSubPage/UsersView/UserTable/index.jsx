import React from 'react';
import PropTypes from 'prop-types';
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

const UsersView = ({handleSelectUser}) => {
  return (
    <Query query={GET_USERS} >
      {({ loading, error, data }) => {
        return (
          <Table
            users={data ? data.users : []}
            isLoading={loading}
            loadingErrors={error}
            handleSelectUser={handleSelectUser} />
        );
      }}
    </Query>
  )
};

UsersView.propTypes = {
  handleSelectUser: PropTypes.func.isRequired
};

export default UsersView;