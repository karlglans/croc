import React from 'react';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';
import { List, ListItem } from '@material-ui/core';
import { withRouter } from "react-router";

import CreateSurveyButton from './CreateSurveyButton';
import EditFormButton from './EditFormButton';

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

const styles = {
  listItemStyle: {
    paddingRight: 20,
  }
}

const QuestionsList = props => (
  <Query query={GET_QUESTIONS} variables={{formId: props.formId}}>
    {({ loading, error, data }) => {
      if (loading) return 'Loading...';
      if (error) return `Error! ${error.message}`;
      return (
        <React.Fragment>
          <CreateSurveyButton formId={props.formId} />
          { data.form.isEditable &&
            (<EditFormButton formId={props.formId} />)
          }
          <List style={styles.listItemStyle}>
            {data.form.questions.map((question, index) => (
              <ListItem button key={question.id} >
                {index + 1} : {question.text}
              </ListItem>
            ))}
          </List>
        </React.Fragment>
      );
    }}
  </Query>
);

// http://localhost:3000/admin/forms/NNN
const getFormIdFromPath = (url) => {
  const lastPart = url.substring(url.lastIndexOf('/') + 1)
  return lastPart;
}

const MaybeQuestionsList = props => {
  if ( !props.location.pathname.includes('forms/') ) return null;
  return (<QuestionsList formId={getFormIdFromPath(props.location.pathname)} />)
}

export default withRouter(MaybeQuestionsList);