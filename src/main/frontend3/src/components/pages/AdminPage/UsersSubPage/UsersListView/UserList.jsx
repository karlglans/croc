import React from 'react';
import PropTypes from 'prop-types';

import { List } from '@material-ui/core';
import UserListItem from './UserListItem';

const UserList = props => (
  <List>
    { props.isLoading && (
      <div>loading..</div>
    )}
    {!props.isLoading && props.users && props.users.map(user => (
      <UserListItem key={user.id} user={user}/>
    ))}
  </List>
);

UserList.propTypes = {
  users: PropTypes.array,
  isLoading: PropTypes.bool.isRequired,
};

export default UserList;
