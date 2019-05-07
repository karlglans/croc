import React from 'react';
import adminPageContext from './adminPageContext';
import AdminPageLayout from './AdminPageLayout'

class AdminPage extends React.Component {
  constructor(props) {
    super(props);
    this.setSelectedForm = this.setSelectedForm.bind(this);
    this.setOpenCreateSurvey = this.setOpenCreateSurvey.bind(this);
    this.state = {
      // this is a central state for admin page. Maybe store thiese in sessionStorage
      adminPageContext: {
        selectedFormId: undefined, // selected form from edit
        selectedFormIdForSurvey: undefined, // the user has selected to create a survey from this from
        setSelectedForm: this.setSelectedForm,
        setOpenCreateSurvey: this.setOpenCreateSurvey
      }
    };
  }

  // this method will be referenced in adminPageContext
  setSelectedForm(formId) {
    this.setState({adminPageContext: {
      selectedFormId: formId,
      setSelectedForm: this.setSelectedForm}
    });
  }

  // this method will be referenced in adminPageContext
  setOpenCreateSurvey(formId) {
    const nextContext = { ...this.state.adminPageContext };
    nextContext.selectedFormIdForSurvey = formId;
    this.setState({ adminPageContext: nextContext });
  }

  render() {
    return (
      <adminPageContext.Provider value={this.state.adminPageContext}>
        <AdminPageLayout
          selectedFormId={this.state.adminPageContext.selectedFormId} 
          selectedFormIdForSurvey={this.state.adminPageContext.selectedFormIdForSurvey}
        />
      </adminPageContext.Provider>
    );
  }
}

export default AdminPage;
