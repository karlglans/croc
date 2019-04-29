import React from 'react';
import { withRouter } from "react-router-dom";
import gql from 'graphql-tag';
import { Query } from 'react-apollo';

import FormViewLayout from './FormViewLayout';

const GET_FROM_DATA = gql`
  query GetFom($formId:ID) {
    form(formId: $formId) {
      id
      questions {
        id
        text
        number
      }
    }
  }
`;

const EditFormView = props => {
  const { match } = props;
  const formId = match && match.params && match.params.formId ? match.params.formId : false;
  return (
    <Query query={GET_FROM_DATA} variables={{ formId }} >
      {({ loading, error, data }) => {
        // if (loading) {
        //   console.log('is loading from data again')
        // }
        if (error) return `Error! ${error.message}`;
        return (
          <FormViewLayout form={data.form} isLoading={loading} />
        );
      }}
    </Query>
    )
};

export default withRouter(EditFormView);
