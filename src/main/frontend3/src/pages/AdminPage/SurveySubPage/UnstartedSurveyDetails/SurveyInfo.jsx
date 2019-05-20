import React from 'react';
import PropTypes from 'prop-types';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';

const GET_SURVEY_DATA = gql`
  query($surveyId: ID!) {
    survey(id: $surveyId) {
      name
    }
  }
`;

const SurveyInfo = props => (
  <Query query={GET_SURVEY_DATA} variables={{surveyId: props.surveyId}} >
    {({ loading, error, data }) => {
      if (error) return `Error! ${error.message}`;
      if (loading) {
        return (
          <div>id: {props.surveyId}</div>
        );
      }
      return (
        <div>
          <h3>{data.survey.name}</h3>
        </div>
      );
    }}
  </Query>
);

SurveyInfo.propTypes = {
  surveyId: PropTypes.number.isRequired
};

export default SurveyInfo;
