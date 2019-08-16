import React from 'react';
import { Redirect } from 'react-router-dom';
import AdminPageLayout from './AdminPageLayout';
import * as Role from '../../constants/domain/roleTypes';
import AdminPageContext from './AdminPageContext';


class AdminPage extends React.Component {
  constructor(props) {
    super(props);
    // only redirect to login if strings does not match
    const redirectPath = localStorage.getItem('crocClient') === Role.SUPERVISOR ? undefined : '/login-portal';
    this.setSelectedFormId = this.setSelectedFormId.bind(this);
    this.state = {
      redirectPath,
      pageContex: {
        selectedFormId: undefined, // used when formId can't be 
        setSelectedFormId: this.setSelectedFormId,
        storedRedirctAction: undefined
      }
    };
  }

  // This method will be a part of AdminPageContext. Its used for storing the selected form when that
  // info cant be transmitted thru an uri. 
  // Its used when creating survey from form tab.
  setSelectedFormId(formId) {
    if (formId !== this.state.pageContex.selectedFormId) {
      const nextPageContex = { ...this.state.pageContex }
      nextPageContex.selectedFormId = formId;
      this.setState({ pageContex: nextPageContex});
    }
  }

  render() {
    if (this.state.redirectPath) {
      return(<Redirect to={this.state.redirectPath} />)
    }

    return (
      <AdminPageContext.Provider value={this.state.pageContex}>
        <AdminPageLayout selectedFormId={this.state.pageContex.selectedFormId}  />
      </AdminPageContext.Provider>
    );
  }
}

export default AdminPage;
