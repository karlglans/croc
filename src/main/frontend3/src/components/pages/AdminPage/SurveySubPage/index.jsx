import React from 'react';
import { Link, Route, Switch, withRouter } from "react-router-dom";
import { withStyles } from '@material-ui/core/styles';
import { Drawer, MenuList, MenuItem, ListItemText } from '@material-ui/core';
import { compose } from 'recompose'

import OngoingSurveys from './OngoingSurveys';
import ClosedSurveys from './ClosedServeys';

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
    const { classes, location } = this.props;
    const isOnOngoing = location.pathname === '/admin/surveys';
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
            <MenuItem button key={'ongoing'} selected={isOnOngoing} component={Link} to='/admin/surveys'>
              <ListItemText primary={'Ongoing'} />
            </MenuItem>
            <MenuItem button key={'closed'} selected={!isOnOngoing} component={Link} to='/admin/surveys/closed'>
              <ListItemText primary={'Closed'} />
            </MenuItem>
          </MenuList>
        </Drawer>
        <div className={classes.content} style={{marginLeft: drawerWidth }}>
          <div className={classes.toolbar} />
          <Switch>
            <Route path="/admin/surveys" exact component={OngoingSurveys}/>
            <Route path="/admin/surveys/closed" exact component={ClosedSurveys}/>
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