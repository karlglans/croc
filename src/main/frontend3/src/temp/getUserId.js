import * as jwt_decode from 'jwt-decode';

const getUserId = () => {
  // TODO: add error handeling
  const { sub } = jwt_decode(localStorage.getItem('apollotoken'));
  return sub;
}

export default getUserId;
