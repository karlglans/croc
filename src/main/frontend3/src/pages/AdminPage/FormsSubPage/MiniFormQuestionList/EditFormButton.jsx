import React from 'react';
import PropTypes from 'prop-types';
import { Redirect } from "react-router-dom";
import { Button } from '@material-ui/core';

class CreateSurveyButton extends React.Component {
  constructor(props){
    super(props);
    this.state = {
      redirect: false
    }
    this.handleClick = this.handleClick.bind(this);
  }
  
  handleClick() {
    this.setState({ redirect: true });
  }
  
  render() {
    if (this.state.redirect) {
      return (
        <Redirect to={`/admin/forms/${this.props.formId}/edit`} push={true} />
      )
    }

    return (
      <Button onClick={() => this.handleClick() }> Edit Form </Button>
    )
  }
}

CreateSurveyButton.propTypes = {
  formId: PropTypes.string.isRequired,
};

export default CreateSurveyButton