import React from 'react';
import PropTypes from 'prop-types';
import { Route, Link, Switch, withRouter } from "react-router-dom";
import { withStyles } from '@material-ui/core/styles';
import { compose } from 'recompose'
import CssBaseline from '@material-ui/core/CssBaseline'
import MenuIcon from '@material-ui/icons/Menu';
import { Tab, Tabs, IconButton, Button, Typography, Toolbar, AppBar} from '@material-ui/core';

import UsersSubPage from './UsersSubPage';
import SurveyPage from './SurveyPage';
import FormsSubPage from './FormsSubPage';

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
    let value = 0; // default
    if (props.location.pathname.includes('/surveys')) {
      value = 1;
    } else if (props.location.pathname.includes('/forms')) {
      value = 2;
    }
    this.state = {
      value
    };
  }

  handleChange = (event, value) => {
    this.setState({ value });
  };

  render() {
    const { classes } = this.props;
    const { value } = this.state;

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
            
              <Tabs variant="fullWidth" value={value} onChange={this.handleChange}>
                <Tab label="Users" to="/admin/users" component={Link} />
                <Tab label="Surveys" to="/admin/surveys" component={Link} />
                <Tab label="Forms" to="/admin/forms" component={Link} />
              </Tabs>

              <Typography variant="h6" color="inherit" style={{flexGrow: 10}}>
              </Typography>

              <Button color="inherit">Login</Button>
            </Toolbar>
          </AppBar>
          <Switch>
            <Route path="/admin/users" component={UsersSubPage}/>
            <Route path="/admin/surveys" component={SurveyPage}/>
            <Route path="/admin/forms" component={ props => <FormsSubPage selectedFormId={this.props.selectedFormId} />} />
          </Switch>
      </div>
    );
  }
}

AdminPageLayout.propTypes = {
  classes: PropTypes.object.isRequired,
};

const enhance = compose(
  withRouter,
  withStyles(styles),
)

export default enhance(AdminPageLayout);
