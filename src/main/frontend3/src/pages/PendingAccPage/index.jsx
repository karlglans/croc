import React from 'react';

import TokenHandler from '../../components/TokenHandler';
import Layout from './Layout';


const PendingAccPage = () => (
  <React.Fragment>
    <TokenHandler expectedRole={'pending'} />
    <Layout />
  </React.Fragment>);

export default PendingAccPage;