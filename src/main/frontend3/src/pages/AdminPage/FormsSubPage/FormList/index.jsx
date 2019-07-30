import React from 'react';
import { withRouter } from 'react-router';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';

import FormList from './List';

const GET_FORMS = gql`
  {
    forms {
      id
      title
      isEditable
    }
  }
`;

const FormsList = ({ match }) => {
  const formId = match.params.formId;
  return (<Query query={GET_FORMS}>
    {({ loading, error, data }) => {
      return (
        <FormList
          forms={data.forms}
          isLoading={loading}
          loadingErrors={error}
          formId={formId} />
      );
    }}
  </Query>
)};

export default withRouter(FormsList);
