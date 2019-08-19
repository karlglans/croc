import React from 'react';
import withLoadingIndicator from '../../../../../hocs/withLoadingIndicator';
import * as Roles from '../../../../../constants/domain/roleTypes';
import ActivateUser from './ActivateUser';

const UsersInfoBox = ({ user }) => {
  const email = user ? user.email : 'no select';
  console.log('user', user);
  return (
    <React.Fragment>
      Email: {email} <br />
      Role: {user.role}

      { user.role === Roles.PENDING && (
        <React.Fragment>
        <br />
        <ActivateUser user={user} />
        </React.Fragment>
      )}
    </React.Fragment>
  )
};

export default withLoadingIndicator(UsersInfoBox);
