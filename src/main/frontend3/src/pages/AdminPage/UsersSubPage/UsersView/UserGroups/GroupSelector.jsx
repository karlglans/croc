import React from 'react';
import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/core/styles';

import { Paper, List, ListItem } from '@material-ui/core';

const useStyles = makeStyles(theme => ({
  root: {
    padding: theme.spacing(3, 2),
    width: '100%'
  },
}));

const GroupListItem = ({ group, userGroups, addUserToGroup }) => {
  const { id : groupId } = group;
  const isUserPressentInGroup = !!userGroups.find( g => g.id === groupId) ;
  return (
    <ListItem button selected={isUserPressentInGroup} onClick={() => addUserToGroup(groupId)} >
      {group.name}
    </ListItem>
)};

// onClick={() => addUserToGroup(group.id)}

// will load goups then used groups 
const GroupSelector = ({ user, isLoading, groups, loadingGroups, addUserToGroup }) => {
  // const usedId = user ? user.id : undefined;
  const classes = useStyles();
  const userGroups = user ? user.groups : []; // groups for this user
  return (
    <Paper className={classes.root}>
      <List>
        { loadingGroups && (
          <div>loading..</div>
        )}
        {!loadingGroups && groups && groups.map(group => (
          <GroupListItem
            addUserToGroup={addUserToGroup}
            key={group.id}
            group={group}
            userGroups={userGroups}/>
        ))}
      </List>
    </Paper>
  )
};

GroupSelector.propTypes = {
  addUserToGroup: PropTypes.func.isRequired
};

export default GroupSelector;
