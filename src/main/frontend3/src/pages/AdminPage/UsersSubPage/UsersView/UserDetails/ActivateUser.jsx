import React from 'react';
import { useMutation } from '@apollo/react-hooks';
import gql from 'graphql-tag';
import { Button } from '@material-ui/core';

const ACTIVATE_USER = gql`
  mutation acceptUser($type: String!) {
    acceptUser(type: $type) {
      id
      email
      role
      groups {
        id
      }
    }
  }
`;

const ActivateUser = () => {
  const [acceptUser] = useMutation(ACTIVATE_USER);
  return (<Button onClick={acceptUser}>Accept user</Button>); 
}

export default ActivateUser;