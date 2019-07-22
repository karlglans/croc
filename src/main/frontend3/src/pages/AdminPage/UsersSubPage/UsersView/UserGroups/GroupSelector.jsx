import React from 'react';
import { makeStyles } from '@material-ui/core/styles';

import { Paper, List, ListItem } from '@material-ui/core';

const useStyles = makeStyles(theme => ({
  root: {
    padding: theme.spacing(3, 2),
    width: '100%'
  },
}));

const GroupListItem = ({ group, userGroups }) => {
  const isUserPressentInGroup = !!userGroups.find( g => g.id === group.id) ;
  return (
    <ListItem button selected={isUserPressentInGroup} >
      {group.name}
    </ListItem>
)};

// will load goups then used groups 
const GroupSelector = ({ user, isLoading, groups, loadingGroups }) => {
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
          <GroupListItem key={group.id} group={group} userGroups={userGroups}/>
        ))}
      </List>
    </Paper>
  )
};


export default GroupSelector;