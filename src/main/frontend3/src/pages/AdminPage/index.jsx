import React from 'react';
import { Redirect } from 'react-router-dom';
import AdminPageLayout from './AdminPageLayout';

class AdminPage extends React.Component {
  constructor(props) {
    super(props);
    const redirectPath = localStorage.getItem('crocClient') === 'supervicor' ? undefined : '/login';
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
