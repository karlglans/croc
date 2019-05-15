import React from 'react';
import PropTypes from 'prop-types';
import { ListItem } from '@material-ui/core';

const style = {
  padding: '10 0',
}

const GroupListItem = props => (
  <ListItem style={style} button>
    {props.userGroup.id} - {props.userGroup.name}
  </ListItem>
);

GroupListItem.propTypes = {
  userGroup: PropTypes.object.isRequired,
};

export default GroupListItem;