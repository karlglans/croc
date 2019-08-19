import React from 'react';
import PropTypes from 'prop-types';
import * as jwt_decode from 'jwt-decode';
import { Redirect } from 'react-router-dom';
import Routes from '../constants/routes';

class TokenHandler extends React.Component {
  constructor(props) {
    super(props);
    this.state = { redirectAnotherRole : false };
  }
  
  componentDidMount() {
    const prevToken = localStorage.getItem('apollotoken');
    // Is sending current token and getting back a new fresh token.
    // Will also redirect page if the role in the new token does not match props.expectedRole
    fetch('/token/update', { 
      method: 'POST',
      headers: {
        authorization: `Bearer ${prevToken}`
      },
      body: JSON.stringify({ something: 'sss' })
    })
    .then(response => response.text())
    .then((nextJwt) => {
      const newJwtDecoded = jwt_decode(nextJwt);
      if (newJwtDecoded.roles !== this.props.expectedRole) {
        const sartpageForThisRole = Routes.startPages[newJwtDecoded.roles];
        if (sartpageForThisRole) {
          localStorage.setItem('apollotoken', nextJwt);
          this.setState({ redirectAnotherRole: Routes.startPages[newJwtDecoded.roles] });
        } else {
          console.error('redirect to login since role is wrong')
          this.setState({ redirectAnotherRole: Routes.toLoginPage});
        }
      }
    })
    .catch( () => {
      this.setState({ redirectAnotherRole: Routes.toLoginPage});
    });
  }
  
  render() {
    if (this.state.redirectAnotherRole) {
      return (<Redirect to={this.state.redirectAnotherRole} />);
    } 
    return null;
  }
}

TokenHandler.propTypes = {
  expectedRole: PropTypes.string.isRequired,
};

export default TokenHandler;