import React from 'react';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';
import { withRouter } from 'react-router';

import QuestionList from './QuestionList'

const GET_QUESTIONS = gql`
  query($formId: ID) {
    form(formId: $formId) {
      isEditable
      questions {
        id
        text
      }
    }
  }
`;

const QuestionsList = ({ match }) => {
  const formId = match.params.formId;
  if (!formId) return null;
  return (
    <Query query={GET_QUESTIONS} variables={{ formId }}>
      {({ loading, error, data }) => {
        const questions = data.form ? data.form.questions : [];
        const isEditable = data.form ? data.form.isEditable : false;
        return (
          <QuestionList
            isLoading={loading}
            loadingErrors={error}
            minHeight={300}
            questions={questions}
            formId={formId}
            isEditable={isEditable}
          />
        );
      }}
    </Query>
  );
};

export default withRouter(QuestionsList);
