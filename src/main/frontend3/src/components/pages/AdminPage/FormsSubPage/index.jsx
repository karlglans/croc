import React from 'react';
import { Link, Route, Switch, withRouter } from "react-router-dom";
import { withStyles } from '@material-ui/core/styles';
import { Drawer, MenuList, MenuItem } from '@material-ui/core';
import { compose } from 'recompose'
import ListItemText from '@material-ui/core/ListItemText';

import FormsListView from './FormsListView';
import FormsCreateView from './FormsCreateView';
import EditFromView from './EditFromView';

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
  },
  halfScreen: {
    flex: 1
  },
  content: {
    flexGrow: 1,
    padding: theme.spacing.unit * 3,
  },
  toolbar: theme.mixins.toolbar,
});


class FormsPage extends React.Component {
  render() {
    const { classes, location, match } = this.props;
    const formId = match && match.params && match.params.formId ? match.params.formId : false;
    const editFormPath = '/admin/forms/'+this.props.selectedFormId+'/edit';
    const isOnForms = location.pathname === '/admin/forms';
    const isOnCreate = location.pathname.includes('/create');
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
            <MenuItem button key={'list'} selected={isOnForms} component={Link} to='/admin/forms'>
              <ListItemText primary={'List'} />
            </MenuItem>
            { !this.props.selectedFormId && (
              <MenuItem button key={'create'} selected={isOnCreate} component={Link} to='/admin/forms/create'>
                <ListItemText primary={'Create'} />
              </MenuItem>
            )}
            { this.props.selectedFormId && (
              <MenuItem button key={'edit'} selected={!!formId} component={Link} to={editFormPath}>
                <ListItemText primary={'Edit'} />
              </MenuItem>
            )}
          </MenuList>
        </Drawer>
        <div className={classes.content} style={{marginLeft: drawerWidth }}>
          <div className={classes.toolbar} />
          <Switch>
            <Route path="/admin/forms/create" exact component={FormsCreateView}/>
            <Route path="/admin/forms/:formId/edit" component={EditFromView}/>
            <Route path="/admin/forms/:formId" component={FormsListView}/>
            <Route path="/admin/forms" component={FormsListView}/>
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