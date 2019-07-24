import React from 'react';
import { graphql } from 'react-apollo';
import gql from 'graphql-tag';
import Layout from './Layout';
import { compose } from 'recompose';

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

const REMOVE_USER_FROM_GROUP = gql`
  mutation($userId: ID!, $userGroupId: ID!) {
    removeUserFromGroup(userId: $userId, userGroupId: $userGroupId) {
      id
      email
      groups {
        id
      }
    }
  }
`;

const UsersView = ({ addUserToUserGroup, removeUserFromUserGroup }) => {
  const [userId, setUserId] = React.useState(undefined);

  function addUser() {
    console.log('add user');
  }
  function handleSelectUser(userId) {
    setUserId(userId);
  }
  function addUserToGroup(groupId) {
    addUserToUserGroup({
      variables: {
        userId,
        userGroupId: groupId
      }})
      .then( () => {
        console.log('addUserToGroup SUCCESS');
      });
  }
  function removeUserFromGroup(groupId) {
    removeUserFromUserGroup({
      variables: {
        userId,
        userGroupId: groupId
      }})
      .then( () => {
        console.log('removeUserFromGroup SUCCESS');
      });
  }
  return (
    <Layout
      addUser={addUser}
      handleSelectUser={handleSelectUser}
      addUserToGroup={addUserToGroup}
      removeUserFromGroup={removeUserFromGroup}
      userId={userId}
    />
  )
};

const enhance = compose(
  graphql(ADD_USER_TO_GROUP, {name: 'addUserToUserGroup'}),
  graphql(REMOVE_USER_FROM_GROUP, {name: 'removeUserFromUserGroup'}),
);

export default enhance(UsersView);

// export default graphql(ADD_USER_TO_GROUP, {name: 'addUserToUserGroup'})(UsersView);