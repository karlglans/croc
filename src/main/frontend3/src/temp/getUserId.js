const getUserId = () => {
  return localStorage.getItem('apollotoken');
}
export default getUserId;