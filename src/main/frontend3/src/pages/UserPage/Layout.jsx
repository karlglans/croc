import React from 'react';
import PropTypes from 'prop-types';
import { Route, Link, Switch, withRouter } from "react-router-dom";
import { withStyles } from '@material-ui/core/styles';
import { compose } from 'recompose'
import CssBaseline from '@material-ui/core/CssBaseline'
import MenuIcon from '@material-ui/icons/Menu';
import { Tab, Tabs, IconButton, Button, Typography, Toolbar, AppBar} from '@material-ui/core';

import SurveysSubPage from './SurveySubPage';
import UsersSubPage from './UsersSubPage';

const styles = theme => ({
  root: {
    flexGrow: 1,
  },
  appbar: {
    zIndex: theme.zIndex.drawer + 1,
  },
  grow: {
    flexGrow: 1,
  },
  menuButton: {
    marginLeft: -12,
    marginRight: 20,
  },
});

class AdminPageLayout extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      tabIndex: 0 // first tab will be active 
    };
  }

  static transfromPathToTabIndex(pathname) {
    let tabIndex = 0; // default
    if (pathname.includes('/users')) {
      tabIndex = 1;
    } 
    return tabIndex;
  }

  static getDerivedStateFromProps(props, state) {
    let nextState = { ...state }
    const tabIndex = AdminPageLayout.transfromPathToTabIndex(props.location.pathname);
    if (tabIndex === state.tabIndex) {
      return state; // no change
    }
    nextState = {
      tabIndex
    };
    return nextState;
  }

  handleChange = (event, value) => {
    this.setState({ value });
  };

  render() {
    const { classes } = this.props;
    const { tabIndex } = this.state;

    return (
      <div className={classes.root}>
          <CssBaseline /> 
          <AppBar position="fixed" className={classes.appbar}>
            <Toolbar>
              <IconButton className={classes.menuButton} color="inherit" aria-label="Menu">
                <MenuIcon />
              </IconButton>
              <Typography variant="h6" color="inherit" className={classes.grow}>
                Cro
              </Typography>
            
              <Tabs variant="fullWidth" value={tabIndex} onChange={this.handleChange} aa={this.props.selectedFormIdForSurvey}>
                <Tab label="Surveys" to="/surveys" component={Link} />
                <Tab label="Users" to="/users" component={Link} />
              </Tabs>

              <Typography variant="h6" color="inherit" style={{flexGrow: 10}}>
              </Typography>

              <Button color="inherit" to="/admin/surveys" component={Link} >Login</Button>
            </Toolbar>
          </AppBar>
          <Switch>
            <Route path="/surveys"
              component={ props => (<SurveysSubPage />) }
             />
             <Route path="/survey"
              component={ props => (<SurveysSubPage />) }
             />
            <Route path="/users" component={UsersSubPage}/>
          </Switch>
      </div>
    );
  }
}

AdminPageLayout.propTypes = {
  classes: PropTypes.object.isRequired,
  selectedFormIdForSurvey: PropTypes.number
};

const enhance = compose(
  withRouter,
  withStyles(styles),
)

export default enhance(AdminPageLayout);