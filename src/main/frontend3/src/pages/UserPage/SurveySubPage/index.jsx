import React from 'react';
import { Link, Route, Switch, withRouter } from "react-router-dom";
import { withStyles } from '@material-ui/core/styles';
import { Drawer, MenuList, MenuItem, ListItemText } from '@material-ui/core';
import { compose } from 'recompose'

import Ongoing from './Ongoing';
import SurveyForm from '../../../components/SurveyForm';

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

const Dummy = () => (
  <p>dummy</p>
);

class SurveysSubPage extends React.Component {
  render() {
    const { classes, location } = this.props;
    const isOnClosed = location.pathname.includes('closed'); // temp solution
    const isOnOngoing = !isOnClosed; // default option
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
          <MenuItem button key={'ongoing'} selected={isOnOngoing} component={Link} to='/surveys'>
            <ListItemText primary={'Ongoing'} />
          </MenuItem>
          <MenuItem button key={'closed'} selected={isOnClosed} component={Link} to='/surveys/closed'>
            <ListItemText primary={'Closed'} />
          </MenuItem>
        </MenuList>
        </Drawer>
        <div className={classes.content} style={{marginLeft: drawerWidth }}>
        <div className={classes.toolbar} />
          <Switch>
            <Route path="/surveys" exact component={Ongoing}/>
            <Route path="/survey/:surveyId" exact component={SurveyForm}/>
            <Route path="/surveys/closed" exact component={Ongoing}/>
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

export default enhance(SurveysSubPage);
