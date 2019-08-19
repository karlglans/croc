import * as jwt_decode from 'jwt-decode';

export function getUserRole() {
  if (localStorage.getItem('apollotoken')) {
    const { roles } = jwt_decode(localStorage.getItem('apollotoken'));
    return roles;
  } else {
    return 'unidentified';
  }
}

export function getUserSub() {
  const { sub } = jwt_decode(localStorage.getItem('apollotoken'));
  return sub;
}

export function logout() {
  localStorage.setItem('apollotoken', 'none');
  // localStorage.getItem('apollotoken');
}