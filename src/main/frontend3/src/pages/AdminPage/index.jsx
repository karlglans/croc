import React from 'react';
import AdminPageLayout from './AdminPageLayout';
import AdminPageContext from './AdminPageContext';
import TokenHandler from '../../components/TokenHandler';
import * as roleTypes from '../../constants/domain/roleTypes';

class AdminPage extends React.Component {
  constructor(props) {
    super(props);
    // only redirect to login if strings does not match
    this.setSelectedFormId = this.setSelectedFormId.bind(this);
    this.state = {
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
    return (
      <AdminPageContext.Provider value={this.state.pageContex}>
        <TokenHandler expectedRole={roleTypes.SUPERVISOR} />
        <AdminPageLayout selectedFormId={this.state.pageContex.selectedFormId}  />
      </AdminPageContext.Provider>
    );
  }
}

export default AdminPage;
