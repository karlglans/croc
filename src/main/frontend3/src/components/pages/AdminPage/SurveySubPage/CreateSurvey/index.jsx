import React from 'react';
import PropTypes from 'prop-types';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';

import CreateSurveySubPage from './CreateSurveySubPage'


const GET_FROM_DATA = gql`
  query GetFormAndUsergroups($formId:ID) {
    form(formId: $formId) {
      id
      title
    }
  }
`;

const CreateSurvey = props => {
  const { formId } = props;
  return ( 
    <Query query={GET_FROM_DATA} variables={{ formId }} >
      {({ loading, error, data }) => {
        if (error) return `Error! ${error.message}`;
        return (
          <CreateSurveySubPage form={data.form} isLoading={loading} />
        )
      }}
    </Query>
  )
};
  

CreateSurvey.propTypes = {
  formId: PropTypes.number.isRequired,
};

export default CreateSurvey;
