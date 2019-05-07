import React from 'react';
// import PropTypes from 'prop-types';
import { List, ListItem } from '@material-ui/core';


const PartisipantListItem = props => {
  // const userId = props.partisipant ? Number(props.partisipant.id) : 0
  return (
  <ListItem >
      {props.partisipant.email}
  </ListItem>
  )
};


const PartisipantList = props => {
  const{participants, isLoading} = props;
  return (
    <List >
      { props.isLoading && (
        <ListItem>loading..</ListItem>
      )}
      {
        !isLoading && participants && participants.map(partisipant => (
          <PartisipantListItem
            key={partisipant.id}
            partisipant={partisipant}
          />
        ))
      }
    </List>
  )
};

export default PartisipantList;