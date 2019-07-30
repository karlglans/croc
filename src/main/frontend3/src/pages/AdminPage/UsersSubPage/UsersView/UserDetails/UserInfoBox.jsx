import React from 'react';
import withLoadingIndicator from '../../../../../hocs/withLoadingIndicator';

const UsersInfoBox = ({ user }) => {
  const email = user ? user.email : 'no select';
  return (
    <React.Fragment>
      Email: {email}
    </React.Fragment>
  )
};

export default withLoadingIndicator(UsersInfoBox);
