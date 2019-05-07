import React from 'react';
import { Link, Route, Switch, withRouter } from "react-router-dom";
import { withStyles } from '@material-ui/core/styles';
import { Drawer, MenuList, MenuItem, ListItemText } from '@material-ui/core';
import { compose } from 'recompose'

import OngoingSurveys from './OngoingSurveys';
import ClosedSurveys from './ClosedServeys';
import CreateSurvey from './CreateSurvey'

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
    const isOnOngoing = location.pathname === '/admin/surveys' || location.pathname.includes('ongoing');
    const isOnCreate = location.pathname === '/admin/surveys/create';
    const isOnClosed = !isOnOngoing && !isOnCreate; // temp solution
    const selectedFormIdForSurvey = this.props.selectedFormIdForSurvey || 2; // <-- 2 is temp solution
    const surveyId = 1;
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
            <MenuItem button key={'closed'} selected={isOnClosed} component={Link} to='/admin/surveys/closed'>
              <ListItemText primary={'Closed'} />
            </MenuItem>
            {this.props.selectedFormIdForSurvey && (
              <MenuItem button key={'create'} selected={isOnCreate} component={Link} to='/admin/surveys/create'>
              <ListItemText primary={'Create (Step 1)'} />
            </MenuItem>
            )}
          </MenuList>
        </Drawer>
        <div className={classes.content} style={{marginLeft: drawerWidth }}>
          <div className={classes.toolbar} />
          <Switch>
            <Route path="/admin/surveys" exact component={OngoingSurveys}/>
            <Route path="/admin/surveys/ongoing" exact component={OngoingSurveys}/>
            <Route path="/admin/surveys/closed" exact component={ClosedSurveys}/>
            <Route path="/admin/surveys/create" exact component={
              () => <CreateSurvey formId={selectedFormIdForSurvey} />}
            />
            <Route path="/admin/surveys/ongoing/:surveyId/inspect" exact component={
              () => <OngoingSurveys surveyId={surveyId} />}
            />
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
