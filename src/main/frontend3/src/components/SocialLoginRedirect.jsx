import React from 'react';
import * as jwt_decode from 'jwt-decode';
import { Redirect, withRouter } from 'react-router-dom';
import Routes from '../constants/routes';

const SocialLoginRedicet = ({ location }) => {
  const { search } = location;
  const jwt = search.replace('?token=', '');
  const { roles } = jwt_decode(jwt);
  console.log('social login redirect!')
  if (!!roles) {
    console.log('returned form social login: ', roles);
    localStorage.setItem('apollotoken', jwt);
    return (<Redirect to={Routes.startPages[roles]} />);
  } else {
    return (<p>SocialLoginRedicet</p>);
  }
};

export default withRouter(SocialLoginRedicet);