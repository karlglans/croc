import React from 'react';
import Layout from './Layout';

const UsersView = () => {
  const [userId, setUserId] = React.useState(undefined);

  function addUser() {
    console.log('add user');
  }
  function handleSelectUser(userId) {
    setUserId(userId);
    console.log('select user', userId);
  }
  return (
    <Layout
      addUser={addUser}
      handleSelectUser={handleSelectUser}
      userId={userId}
    />
  )
};

export default UsersView;