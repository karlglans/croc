import React from 'react';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import { withRouter } from "react-router";


const GET_QUESTIONS = gql`
  query($formId: Int) {
    form(formId: $formId) {
      questions {
        id
        text
        number
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
        <List style={styles.listItemStyle}>
          {data.form.questions.map(question => (
            <ListItem button key={question.id} >
              {question.number} : {question.text}
            </ListItem>
          ))}
        </List>
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
