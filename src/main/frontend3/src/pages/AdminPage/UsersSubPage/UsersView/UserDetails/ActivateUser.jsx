import React from 'react';
import gql from 'graphql-tag';
import { graphql } from 'react-apollo';
import { Button } from '@material-ui/core';


const ACCEPT_USER = gql`
  mutation acceptUser($userId: ID!) {
    acceptUser(userId: $userId) {
      id
      email
      role
      groups {
        id
      }
    }
  }
`;

const ActivateUser = ({user, acceptUser}) => {
  return (<Button onClick={() => acceptUser({ variables: { userId: user.id } })}>Accept user</Button>); 
}

export default graphql(ACCEPT_USER, {name: 'acceptUser'})(ActivateUser);