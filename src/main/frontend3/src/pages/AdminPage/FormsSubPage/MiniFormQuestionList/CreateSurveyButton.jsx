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
    this.setState({redirect: true});
  }
  
  render() {
    if (this.state.redirect) {
      return (
        <Redirect to={`/admin/survey/create`} push={true} />
      )
    }
    return (
      <Button
        size='small'
        color="primary"
        variant='contained'
        onClick={() => this.handleClick() }>
        Create Survey
      </Button>
    )
  }
}

CreateSurveyButton.propTypes = {
  formId: PropTypes.string.isRequired,
};

export default CreateSurveyButton
