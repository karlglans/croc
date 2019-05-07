import React from 'react';
import PropTypes from 'prop-types';

import { List } from '@material-ui/core';
import GroupListItem from './GroupListItem';

const GroupList = props => (
  <List>
    { props.isLoading && (
      <div>loading..</div>
    )}
    {!props.isLoading && props.userGroups && props.userGroups.map(group => (
      <GroupListItem key={group.id} userGroup={group}/>
    ))}
  </List>
);

GroupList.propTypes = {
  userGroups: PropTypes.array,
  isLoading: PropTypes.bool.isRequired,
};

export default GroupList;