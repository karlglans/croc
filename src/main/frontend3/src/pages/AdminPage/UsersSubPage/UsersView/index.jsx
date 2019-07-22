import React from 'react';
import { graphql } from 'react-apollo';
import gql from 'graphql-tag';
import Layout from './Layout';

const ADD_USER_TO_GROUP = gql`
  mutation($userId: ID!, $userGroupId: ID!) {
    addUserToGroup(userId: $userId, userGroupId: $userGroupId) {
      id
      email
      groups {
        id
      }
    }
  }
`;

const UsersView = ({ addUserToUserGroup }) => {
  const [userId, setUserId] = React.useState(undefined);

  function addUser() {
    console.log('add user');
  }
  function handleSelectUser(userId) {
    setUserId(userId);
    console.log('select user', userId);
  }
  function addUserToGroup(groupId) {
    console.log('addUserToGroup', addUserToUserGroup, groupId);
    console.log('userId', userId, 'groupId', groupId);
    addUserToUserGroup({
      variables: {
        userId,
        userGroupId: groupId
      }})
      .then( () => {
        console.log('addUserToGroup SUCCESS');
      })
  }
  return (
    <Layout
      addUser={addUser}
      handleSelectUser={handleSelectUser}
      addUserToGroup={addUserToGroup}
      userId={userId}
    />
  )
};

export default graphql(ADD_USER_TO_GROUP, {name: 'addUserToUserGroup'})(UsersView);