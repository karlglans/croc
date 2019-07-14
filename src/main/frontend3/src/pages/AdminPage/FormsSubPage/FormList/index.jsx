import React from 'react';
import { Link } from 'react-router-dom';
import { withRouter } from 'react-router';
import gql from 'graphql-tag';
import { Query } from 'react-apollo';
import { List, ListItem, ListItemText, ListItemIcon } from '@material-ui/core';
import DraftsIcon from '@material-ui/icons/Drafts';

const GET_FORMS = gql`
  {
    forms {
      id
      title
      isEditable
    }
  }
`;

const styles = {
  listItemStyle: {
    paddingRight: 20,
  }
}

const FormsList = ({ match }) => {
  const formId = match.params.formId;
  return (<Query query={GET_FORMS}>
    {({ loading, error, data }) => {
      if (loading) return 'Loading...';
      if (error) return `Error! ${error.message}`;
      if (!data.forms) {
        console.log('FormsList, no forms');
        return 'missing forms';
      }

      return (
        <List style={styles.listItemStyle}>
          {data.forms.map(form => {
            return (
              <ListItem button key={form.id} selected={form.id===formId} component={Link} to={`/admin/form/${form.id}`} >
                <ListItemText primary={form.title} />
                {form.isEditable && (<ListItemIcon><DraftsIcon /></ListItemIcon>)}
              </ListItem>
            )
          })}
        </List>
      );
    }}
  </Query>
)};

export default withRouter(FormsList);
