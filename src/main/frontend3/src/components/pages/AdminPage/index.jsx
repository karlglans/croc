import React from 'react';
import adminPageContext from './adminPageContext';
import AdminPageLayout from './AdminPageLayout'

class AdminPage extends React.Component {
  constructor(props) {
    super(props);
    this.setSelectedForm = this.setSelectedForm.bind(this);
    this.setOpenCreateSurvey = this.setOpenCreateSurvey.bind(this);
    this.setOpenPopulateSurvey = this.setOpenPopulateSurvey.bind(this);
    this.state = {
      // this is a central state for admin page. Maybe store thiese in sessionStorage
      adminPageContext: {
        selectedFormId: undefined, // selected form from edit
        selectedFormIdForSurvey: undefined, // the user has selected to create a survey from this from
        setSelectedForm: this.setSelectedForm,
        setOpenCreateSurvey: this.setOpenCreateSurvey,
        setOpenPopulateSurvey: this.setOpenPopulateSurvey
      }
    };
  }

  // this method will also be referenced in adminPageContext
  setSelectedForm(formId) {
    const nextContext = { ...this.state.adminPageContext };
    nextContext.selectedFormId = formId;
    this.setState({ adminPageContext: nextContext });
  }

  // this method will also be referenced in adminPageContext
  setOpenCreateSurvey(formId) {
    const nextContext = { ...this.state.adminPageContext };
    nextContext.selectedFormIdForSurvey = formId;
    this.setState({ adminPageContext: nextContext });
  }

  // this method will also be referenced in adminPageContext
  setOpenPopulateSurvey(msg) {
    console.log('setOpenPopulateSurvey', msg);
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
