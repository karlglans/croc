import React from 'react';
import * as jwt_decode from 'jwt-decode';
import { Redirect, withRouter } from 'react-router-dom';
import Routes from '../constants/routes';

const SocialLoginRedicet = ({ location }) => {
  const { search } = location;
  const jwt = search.replace('?token=', '');
  const { roles } = jwt_decode(jwt);
  // const startPages = {
  //   admin: '/admin/surveys',
  //   user: '/surveys',
  //   pending: '/pending'
  // };
  if (!!roles) {
    localStorage.setItem('crocClient', roles);
    localStorage.setItem('apollotoken', jwt);
    console.log('selecting start page', roles, Routes.startPages[roles])
    return (<Redirect to={Routes.startPages[roles]} />);
  } else {
    return (<p>SocialLoginRedicet</p>);
  }
};

export default withRouter(SocialLoginRedicet);