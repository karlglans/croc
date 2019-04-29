import React from 'react';
import PropTypes from 'prop-types';
import { ListItem } from '@material-ui/core';

const style = {
  padding: '10 0',
}

const UserListItem = props => (
  <ListItem style={style} button>
    {props.user.id} - {props.user.email}
  </ListItem>
);

UserListItem.propTypes = {
  user: PropTypes.object.isRequired,
};

export default UserListItem;
