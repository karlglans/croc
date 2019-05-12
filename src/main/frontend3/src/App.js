import React, { Component } from 'react';
import { BrowserRouter as Router, Route} from "react-router-dom";
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
          <Route path="/admin" component={AdminPage} />
          <Route path="/user" component={UserPage} />
        </Router>
      </ApolloProvider>
    );
  }
}

export default App;
