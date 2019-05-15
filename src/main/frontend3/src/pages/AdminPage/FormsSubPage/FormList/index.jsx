import React from 'react';
import { Link } from "react-router-dom";
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

const FormsList = () => {
  return (<Query query={GET_FORMS}>
    {({ loading, error, data }) => {
      if (loading) return 'Loading...';
      if (error) return `Error! ${error.message}`;

      return (
        <List style={styles.listItemStyle}>
          {data.forms.map(form => {
            return (
              <ListItem button key={form.id} component={Link} to={`/admin/forms/${form.id}`} >
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

export default FormsList;
