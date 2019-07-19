import React from 'react';
import PropTypes from 'prop-types';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';

import Layout from './Layout'

const GET_PARTICPANT_DATA = gql`
  query($surveyId: ID) {
    survey(id: $surveyId) {
      id
      participants {
        id
        email
        isComplete
      }
    }
  }
`;

const ParticipantList = props => (
  <Query query={GET_PARTICPANT_DATA} variables={{surveyId: props.surveyId}} >
    {({ loading, error, data }) => {
      if (error) return `Error! ${error.message}`;
      return (
        <Layout 
          surveyId={props.surveyId}
          survey={data.survey}
          isLoading={loading} />
      );
    }}
  </Query>
);

ParticipantList.propTypes = {
  surveyId: PropTypes.string.isRequired,
};

export default ParticipantList;