const getUserId = () => {
  return localStorage.getItem('crocClientId');
}
export default getUserId;