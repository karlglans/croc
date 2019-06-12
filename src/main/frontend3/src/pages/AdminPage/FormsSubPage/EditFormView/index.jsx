import React, { useState } from 'react';
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
        questionType
      }
    }
  }
`;

const EditFormView = props => {
  const { match } = props;
  const [editQuestionId, setEditQuestionId] = useState(undefined);
  const formId = match && match.params && match.params.formId ? match.params.formId : false;
  return (
    <Query query={GET_FROM_DATA} variables={{ formId }} >
      {({ loading, error, data }) => {
        if (error) return `Error! ${error.message}`;
        return (
          <FormViewLayout
            form={data.form}
            isLoading={loading}
            editQuestionId={editQuestionId}
            setEditQuestionId={setEditQuestionId} />
        );
      }}
    </Query>
    )
};

export default withRouter(EditFormView);
