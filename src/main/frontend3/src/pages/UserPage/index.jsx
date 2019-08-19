import React from 'react';
import Layout from './Layout';
import * as roleTypes from '../../constants/domain/roleTypes';
import TokenHandler from '../../components/TokenHandler';

const UserPage = () => {
  return (
    <React.Fragment>
      <TokenHandler expectedRole={roleTypes.USER}/>
      <Layout/>
    </React.Fragment>
  );
}


export default UserPage;