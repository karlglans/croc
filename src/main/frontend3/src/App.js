import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import { ApolloProvider } from 'react-apollo';
import { ApolloClient } from 'apollo-client';
import { HttpLink } from 'apollo-link-http';
import { ApolloLink, concat } from 'apollo-link';
import { InMemoryCache } from 'apollo-cache-inmemory';

import AdminPage from './pages/AdminPage';
import UserPage from './pages/UserPage';
import LoginPage from './pages/LoginPage';
import PendingAccPage from './pages/PendingAccPage';
import SocialLoginRedicet from './components/SocialLoginRedirect';

import { ThemeProvider } from '@material-ui/styles';

import * as am4core from '@amcharts/amcharts4/core';
import am4themes_animated from '@amcharts/amcharts4/themes/animated';

import theme from './theme';

am4core.useTheme(am4themes_animated);

const httpLink = new HttpLink({ uri: '/graphql' });

const authMiddleware = new ApolloLink((operation, forward) => {
  // add the authorization to the headers
  operation.setContext({
    headers: {
      // simple solution: token will be used as user id
      authorization: "Bearer " + (localStorage.getItem('apollotoken') || 1000),
    }
  });

  return forward(operation);
});

const client = new ApolloClient({
  link: concat(authMiddleware, httpLink),
  cache: new InMemoryCache(),
});

class App extends Component {
  render() {
    return (
      <ThemeProvider theme={theme}>
        <ApolloProvider client={client}>
          <Router>
            <Switch>
              <Route path='/admin' component={AdminPage} />
              <Route path='/login' component={() => <LoginPage client={client} />} />
              <Route path='/oauth2/redirect' component={SocialLoginRedicet} />
              <Route path='/pending' component={PendingAccPage} />
              <Route path='/' component={UserPage} />
            </Switch>
          </Router>
        </ApolloProvider>
      </ThemeProvider>
    );
  }
}

export default App;
