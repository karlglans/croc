import React from 'react';
import PropTypes from 'prop-types';
import { Route, Link, Switch, withRouter } from "react-router-dom";
import { compose } from 'recompose'
import CssBaseline from '@material-ui/core/CssBaseline'
import MenuIcon from '@material-ui/icons/Menu';
import { ThemeProvider } from '@material-ui/styles';
import { withStyles, createMuiTheme} from '@material-ui/core/styles';
import { Tab, Tabs, IconButton, Button, Typography, Toolbar, AppBar } from '@material-ui/core';

import routes from '../../constants/routes';
import UsersSubPage from './UsersSubPage';
import FormsSubPage from './FormsSubPage';
import SurveySubPage from './SurveySubPage';

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
  }
});

// added sub-theme just change secondary color, not sure if this solution is good enogh.
const innerTheme = createMuiTheme({
  palette: {
    secondary: {
      main: '#fff',
    },
  },
});

class AdminPageLayout extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      tabIndex: 0 // first tab will be active 
    };
    this.onClickLogout = this.onClickLogout.bind(this);
  }

  static transfromPathToTabIndex(pathname) {
    let tabIndex = 0; // default
    if (pathname.includes('/surveys') || pathname.includes('/survey')) {
      tabIndex = 1;
    } else if (pathname.includes('/forms') || pathname.includes('/form')) {
      tabIndex = 2;
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

  onClickLogout() {
    localStorage.setItem('crocClient', 'none');
  }

  render() {
    const { classes, selectedFormId } = this.props;
    const { tabIndex } = this.state;

    return (
      <div className={classes.root}>
          <CssBaseline /> 
          <AppBar position="fixed" className={classes.appbar}>
          <ThemeProvider theme={innerTheme}>
            <Toolbar>
              <IconButton className={classes.menuButton} color="inherit" aria-label="Menu">
                <MenuIcon />
              </IconButton>
              <Typography variant="h6" color="inherit" className={classes.grow}>
                Cro
              </Typography>
            
              <Tabs variant="fullWidth" value={tabIndex} onChange={this.handleChange} aa={selectedFormId}>
                <Tab label="Users" to="/admin/users" component={Link} />
                <Tab label="Surveys" to="/admin/surveys" component={Link} />
                <Tab label="Forms" to="/admin/forms" component={Link} />
              </Tabs>

              <Typography variant="h6" color="inherit" style={{flexGrow: 10}}>
              </Typography>

              <Button color="inherit" to={routes.toLoginPage} onClick={this.onClickLogout} component={Link}>logout</Button>
            </Toolbar>
            </ThemeProvider>
          </AppBar>
          <Switch>
            <Route path="/admin/users" component={UsersSubPage}/>
            <Route path="/admin/surveys"
              component={ props => (<SurveySubPage selectedFormId={selectedFormId} />) }
             />
             <Route path="/admin/survey/:surveyId"
              component={ props => (<SurveySubPage selectedFormId={selectedFormId} />) }
             />
            <Route path="/admin/forms"
              component={ props => (<FormsSubPage selectedFormId={selectedFormId} />) } 
            />
            <Route path="/admin/form/:formId"
              component={ props => (<FormsSubPage selectedFormId={selectedFormId} />) } 
            />
          </Switch>
      </div>
    );
  }
}

AdminPageLayout.propTypes = {
  classes: PropTypes.object.isRequired,
  selectedFormId: PropTypes.string
};

const enhance = compose(
  withRouter,
  withStyles(styles),
)

export default enhance(AdminPageLayout);
