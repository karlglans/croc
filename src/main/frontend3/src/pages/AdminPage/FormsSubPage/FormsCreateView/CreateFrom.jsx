import React from 'react';
import { Redirect } from "react-router-dom";
import { Paper, TextField, Button } from '@material-ui/core';
import { withStyles } from '@material-ui/core/styles';
import { compose } from 'recompose'
import { graphql } from 'react-apollo';
import gql from 'graphql-tag';

const createForm = gql`
  mutation ($name: String!) {
    createForm(title: $name) {
      id
      title
      isEditable
    }
  }
`;

const GET_FORMS = gql`
  {
    forms {
      id
      title
      isEditable
    }
  }
`;

const styles = theme => ({
  container: {
    display: 'flex',
    flexWrap: 'wrap',
    width: '100%',
    padding: 30
  },
  textField: {
    marginLeft: theme.spacing(2),
    marginRight: theme.spacing(2),
  },
  button: {
    margin: theme.spacing(4),
  }
});

class CreateForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isFormValid: false,
      formTitle: '',
      createdFormId: false // if set will redirect
    }
    this.handleChange = this.handleChange.bind(this);
    this.sendForm = this.sendForm.bind(this);
  }

  validateFrom() {

  }

  sendForm(event) {
    event.preventDefault();
    this.props.createForm({
      variables: {
        name: this.state.formTitle
      },
      // TODO: avoid this query by storing prev result in cash
      refetchQueries: [{ query: GET_FORMS }]
    }).then((response) => {
      const formId = response.data.createForm.id;
      // will produce redirect
      this.setState({ createdFormId: formId });
    })
  }

  handleChange(event) {
    this.setState({formTitle: event.target.value});
  }

  render() {
    const { classes } = this.props;
    if (this.state.createdFormId) {
      return <Redirect to={`/admin/forms/${this.state.createdFormId}/edit`} push={true} />
    }
    return (
      <form onSubmit={this.sendForm} >
        <Paper className={classes.container}>
          <h2>Create new form</h2>
          <TextField
            id='standard-uncontrolled'
            label='Title'
            defaultValue=''
            fullWidth={true}
            className={classes.textField}
            margin='normal'
            onChange={this.handleChange}
          />
          <br />
          <Button
            type="submit"
            variant='contained'
            color='primary'
            className={classes.button}>
            Create
          </Button>
        </Paper>
      </form>
    );
  }
}

const enhance = compose(
  graphql(createForm, {name: 'createForm'}),
  withStyles(styles)
)

export default enhance(CreateForm);
