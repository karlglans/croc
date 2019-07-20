import React from 'react';
import Layout from './Layout';

const UsersView = () => {
  function addUser() {
    console.log('add user');
  }
  return (
    <Layout addUser={addUser} />
  )
};

export default UsersView;