import React from 'react';
import { Link } from 'react-router-dom';
import gql from 'graphql-tag';
import { List, ListItem } from '@material-ui/core';
import { Query } from 'react-apollo';

const GET_SURVEYS_DATA = gql`
  {
    surveys(status: IN_CREATION) {
      id
      name
    }
  }
`;

const UnstartedSurveysList = props => (
  <Query query={GET_SURVEYS_DATA} >
    {({ loading, error, data }) => {
      if (error) return `Error! ${error.message}`;
      if (loading) {
        return (
          <div>loading...</div>
        );
      }
      
      return (
        <List>
          {
            data.surveys && data.surveys.map(survey => (
              <ListItem button key={survey.id} component={Link} to={'/admin/surveys/unstarted/'+survey.id}>
                {survey.name}
              </ListItem>
            ))
          }
        </List>
      );
    }}
  </Query>
);


export default UnstartedSurveysList;