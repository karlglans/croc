import React from 'react';
import adminPageContext from './adminPageContext';
import AdminPageLayout from './AdminPageLayout'

class AdminPage extends React.Component {
  constructor(props) {
    super(props);
    this.setSelectedForm = this.setSelectedForm.bind(this);
    this.state = {
      adminPageContext: {
        selectedFormId: undefined,
        setSelectedForm: this.setSelectedForm
      }
    };
  }

  setSelectedForm(formId) {
    this.setState({adminPageContext: {
      selectedFormId: formId,
      setSelectedForm: this.setSelectedForm}
    });
  }

  render() {
    return (
      <adminPageContext.Provider value={this.state.adminPageContext}>
        <AdminPageLayout selectedFormId={this.state.adminPageContext.selectedFormId} />
      </adminPageContext.Provider>
    );
  }
}

export default AdminPage;
