import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch} from "react-router-dom";
import ApolloClient from 'apollo-boost';
import { ApolloProvider } from 'react-apollo';

import AdminPage from './pages/AdminPage';
import UserPage from './pages/UserPage';

const client = new ApolloClient();

class App extends Component {
  render() {
    return (
      <ApolloProvider client={client}>
        <Router>
          <Switch>
            <Route path="/admin" component={AdminPage} />
            <Route path="/" component={UserPage} />
          </Switch>
        </Router>
      </ApolloProvider>
    );
  }
}

export default App;
