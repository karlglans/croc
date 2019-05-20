import React from 'react';
import PropTypes from 'prop-types';
import { Redirect } from "react-router-dom";
import { Button } from '@material-ui/core';

import adminPageContext from '../../adminPageContext';

class CreateSurveyButton extends React.Component {
  constructor(props){
    super(props);
    this.state = {
      redirect: false
    }
    this.handleClick = this.handleClick.bind(this);
  }
  
  handleClick(setOpenCreateSurvey) {
    // will call contextApi:setOpenCreateSurvey() after setState()
    this.setState({redirect: true}, ()=> {setOpenCreateSurvey(this.props.formId)});
  }
  
  render() {
    if (this.state.redirect) {
      return (
        <Redirect to={`/admin/surveys/create`} push={true} />
      )
    }
    return (
      <adminPageContext.Consumer>
        {({ setOpenCreateSurvey }) => (
          <Button onClick={() => this.handleClick(setOpenCreateSurvey) }>
            Create Survey
          </Button>
        )}
      </adminPageContext.Consumer>
    )
  }
}

CreateSurveyButton.propTypes = {
  formId: PropTypes.string.isRequired,
};

export default CreateSurveyButton
