import React from 'react';
import { Link, Route, Switch, withRouter } from 'react-router-dom';
import { withStyles, lighten } from '@material-ui/core/styles';
import { Drawer, MenuList, MenuItem, ListItemText } from '@material-ui/core';
import { compose } from 'recompose'

import UsersListView from './UsersListView';
import GroupListView from './GroupListView';

const drawerWidth = 240;

const styles = theme => ({
  root: {
    display: 'flex',
  },
  drawer: {
    width: drawerWidth,
    flexShrink: 0,
  },
  drawerPaper: {
    width: drawerWidth,
    backgroundColor: lighten(theme.palette.primary.light, 0.65),
  },
  halfScreen: {
    flex: 1
  },
  content: {
    flexGrow: 1,
    padding: theme.spacing(2),
  },
  toolbar: theme.mixins.toolbar,
});


class FormsPage extends React.Component {
  render() {
    const { classes, location } = this.props;
    const isOnUsers = location.pathname === '/admin/users';
    return (
      <React.Fragment>
      <Drawer
        className={classes.drawer}
        variant="permanent"
        classes={{
          paper: classes.drawerPaper,
        }}
      >
        <div className={classes.toolbar} />
          <MenuList >
            <MenuItem button key={'users'} selected={isOnUsers} component={Link} to='/admin/users'>
              <ListItemText primary={'Users'} />
            </MenuItem>
            <MenuItem button key={'groups'} selected={!isOnUsers} component={Link} to='/admin/users/groups'>
              <ListItemText primary={'Groups'} />
            </MenuItem>
          </MenuList>
        </Drawer>
        <div className={classes.content} style={{marginLeft: drawerWidth }}>
          <div className={classes.toolbar} />
          <Switch>
            <Route path="/admin/users" exact component={UsersListView}/>
            <Route path="/admin/users/groups" exact component={GroupListView}/>
          </Switch>
      </div>
      </React.Fragment>
    )
  };
}

const enhance = compose(
  withRouter,
  withStyles(styles),
)

export default enhance(FormsPage);