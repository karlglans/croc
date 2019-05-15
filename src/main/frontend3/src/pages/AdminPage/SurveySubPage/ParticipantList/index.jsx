import React from 'react';
import PropTypes from 'prop-types';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';

import PartisipantList from './List'

const GET_PARTICPANT_DATA = gql`
  query($surveyId: ID) {
    survey(id: $surveyId) {
      participants {
        id
        email
      }
    }
  }
`;

const ParticipantList = props => (
  <Query query={GET_PARTICPANT_DATA} variables={{surveyId: props.surveyId}} >
    {({ loading, error, data }) => {
      if (error) return `Error! ${error.message}`;
      return (
        <div>
        <PartisipantList
          participants={data.survey && data.survey.participants}
          isLoading={loading}
          isEditable={true}
        />
        </div>
      );
    }}
  </Query>
);

ParticipantList.propTypes = {
  surveyId: PropTypes.number.isRequired,
};

export default ParticipantList;