import React from 'react';
import { Link } from 'react-router-dom';
import withLoadingIndicator from '../../../../hocs/withLoadingIndicator';

import { List, ListItem, ListItemText, ListItemIcon } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import DraftsIcon from '@material-ui/icons/Drafts';

const useStyles = makeStyles(theme => ({
  root: {
    width: '100%',
    minWidth: 360,
  },
}));

const FormsList = ({ forms, formId }) => {
  const classes = useStyles();
  return (
    <List className={classes.root}>
      {forms.map(form => {
        const link = `/admin/form/${form.id}`;
        return (
          <ListItem button key={form.id} selected={form.id===formId} component={Link} to={link} >
            <ListItemText primary={form.title} />
            {form.isEditable && (<ListItemIcon><DraftsIcon /></ListItemIcon>)}
          </ListItem>
        )
      })}
    </List>
  );
};

export default withLoadingIndicator(FormsList);
