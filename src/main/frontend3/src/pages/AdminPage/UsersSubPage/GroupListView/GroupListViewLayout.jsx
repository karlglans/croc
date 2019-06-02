import React from 'react';
import PropTypes from 'prop-types';
import { Grid } from '@material-ui/core';

import GroupList from './GroupList';

const GroupListViewLayout = props => (
  <Grid container>
    <Grid item sm={12} md={6} lg={5}>
      <GroupList userGroups={props.userGroups} isLoading={props.isLoading} />
    </Grid>
    <Grid item sm={12} md={6} lg={5}>
      section 2
    </Grid>
</Grid>
);

GroupListViewLayout.propTypes = {
  userGroups: PropTypes.array,
  isLoading: PropTypes.bool.isRequired,
};

export default GroupListViewLayout;