import React from 'react';
import { withRouter } from "react-router-dom";

class EditForm extends React.Component {
  render() {
    const { match } = this.props;
    const formId = match && match.params && match.params.formId ? match.params.formId : false;
    return (
      <div>
        Editform render! {formId}
      </div>
    )
  }
}

export default withRouter(EditForm)