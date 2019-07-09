import React from 'react';
import { Redirect } from 'react-router-dom';
import AdminPageLayout from './AdminPageLayout';
import * as Role from '../../constants/domain/roleTypes';

class AdminPage extends React.Component {
  constructor(props) {
    super(props);
    // only redirect to login if strings does not match
    const redirectPath = localStorage.getItem('crocClient') === Role.SUPERVISOR ? undefined : '/login';
    this.state = {
      redirectPath
    };
  }

  render() {
    if (this.state.redirectPath) {
      return(<Redirect to={this.state.redirectPath} />)
    }

    return (
        <AdminPageLayout />
    );
  }
}

export default AdminPage;
