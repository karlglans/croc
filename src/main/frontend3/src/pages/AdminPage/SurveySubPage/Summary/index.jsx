import React from 'react';
import PropTypes from 'prop-types';
import { withRouter } from 'react-router-dom';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';
import Layout from './Layout';

const GET_SURVEYS_DATA = gql`
  query($surveyId: ID!) {
    survey(id: $surveyId) {
      id
      name
      form {
        id
        title
        questions {
          id
          text
          questionType
        }
      }
    }
  }
`;

const Summary = props => {
  const { match } = props;
  const surveyId = match && match.params && match.params.surveyId ?
    match.params.surveyId : 0;
  return (
    <Query query={GET_SURVEYS_DATA} variables={{ surveyId }} >
      {({ loading, error, data }) => {
        if (error) return `Error! ${error.message}`;
        return (
          <Layout
            surveyId={props.surveyId}
            survey={data.survey}
            isLoading={loading}
          />
        );
      }}
    </Query>
  );
};

Summary.propTypes = {
  surveyId: PropTypes.number.isRequired,
};

export default withRouter(Summary);
