import React from 'react';
import loadingIndicator from '../../../../../components/Loading';


const UsersInfoBox = ({ user }) => {
  const email = user ? user.email : 'no select';
  // const userId = user ? user.email : 'no select';
  // const classes = useStyles();
  return (
    <React.Fragment>
      Email: {email}
    </React.Fragment>
  )
};

export default loadingIndicator(UsersInfoBox);
