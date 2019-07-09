const exp = {};
module.exports = exp;
const jwt = require('jsonwebtoken');

function checkArgs(secret, roles, email, id) {
  const idNumber = parseInt(id, 10)
  if (idNumber != id) { // comparing string & int
    console.log("id should be number!")
    return false
  }
  if (!email) {
    console.log('missing email')
    return false
  }
  if (!secret) {
    console.log('missing secret')
    return false
  }
  // TODO: check roles too
  return true
}

exp.Main = function(secret, roles, email, id) {
  const idNumber = parseInt(id, 10);
  if (checkArgs(secret, roles, email, idNumber)) {
    console.log('roles: ', roles)
    console.log('email: ', email)
    console.log('id: ', idNumber)
    const token = jwt.sign({
      // exp: Math.floor(Date.now() / 1000) + (60 * 60),
      iss: 'croc',
      sub: 'google-11111', // fake!
      email: email,
      roles: roles || 'user',
      id: idNumber,
    }, secret);
    console.log('token: ' + token);
  }
}