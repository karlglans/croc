import React from 'react';
import PropTypes from 'prop-types';
import { Button } from '@material-ui/core';
import AdminPageContext from '../../AdminPageContext';
import { withRouter } from 'react-router';

const CreateSurveyButton = ({ formId, history }) => {

  const handleClick = (setSelectedFormId) => {
    setSelectedFormId(formId);
    history.push('/admin/survey/create');
  }
  
  return (
    <AdminPageContext.Consumer>
      {({ setSelectedFormId }) => (
        <Button
          size='small'
          color="primary"
          variant='contained'
          onClick={() => handleClick(setSelectedFormId) }>
          Create Survey
        </Button>
      )}
    </AdminPageContext.Consumer>
  )
  
}

CreateSurveyButton.propTypes = {
  formId: PropTypes.string.isRequired,
};

export default withRouter(CreateSurveyButton);
