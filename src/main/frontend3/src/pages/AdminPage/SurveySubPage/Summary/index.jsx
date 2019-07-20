import React from 'react';
import PropTypes from 'prop-types';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';
import Layout from './Layout';

const GET_SURVEYS_DATA = gql`
  query($surveyId: ID!) {
    survey(id: $surveyId) {
      id
      name
      answersSum {
        questionId
        content
      }
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
  const { surveyId } = props;
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
  surveyId: PropTypes.string.isRequired,
};

export default Summary;
