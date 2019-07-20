import * as jwt_decode from 'jwt-decode';

const getUserId = () => {
  // TODO: add error handeling
  const { id } = jwt_decode(localStorage.getItem('apollotoken')); 
  console.log('getUserId', id); 
  return id;
}
export default getUserId;