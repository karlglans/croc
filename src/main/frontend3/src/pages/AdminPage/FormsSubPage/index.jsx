import React from 'react';
import { Link, Route, Switch, withRouter } from "react-router-dom";
import { withStyles, lighten } from '@material-ui/core/styles';
import { Drawer, MenuList, MenuItem, ListItemText } from '@material-ui/core';
import { compose } from 'recompose';

import FormsListView from './FormsListView';
import FormsCreateView from './FormsCreateView';
import EditFormView from './EditFormView';

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


class FormsSubPage extends React.Component {
  render() {
    const { classes, location, /*match*/ } = this.props;
    // const formId = match.params.formId; // is located at lower levels
    const isOnCreate = location.pathname.includes('/create');
    const isOnEdit = location.pathname.includes('/edit');
    const isOnListForms = !isOnCreate && !isOnEdit; // default
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
            <MenuItem button key={'list'} selected={isOnListForms} component={Link} to='/admin/forms'>
              <ListItemText primary={'List'} />
            </MenuItem>
            { !this.props.selectedFormId && (
              <MenuItem button key={'create'} selected={isOnCreate} component={Link} to='/admin/form/create'>
                <ListItemText primary={'Create'} />
              </MenuItem>
            )}
            { isOnEdit && (
              <MenuItem button key={'edit'} selected={isOnEdit} >
                <ListItemText primary={'Edit'} />
              </MenuItem>
            )}
          </MenuList>
        </Drawer>
        <div className={classes.content} style={{marginLeft: drawerWidth }}>
          <div className={classes.toolbar} />
          <Switch>
            <Route path="/admin/form/create" exact component={FormsCreateView}/>
            <Route path="/admin/form/:formId/edit" component={EditFormView}/>
            <Route path="/admin/form/:formId" component={FormsListView}/>
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

export default enhance(FormsSubPage);
