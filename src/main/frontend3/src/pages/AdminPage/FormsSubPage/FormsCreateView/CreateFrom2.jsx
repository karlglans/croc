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
    }
  }
`;

const GET_FORMS = gql`
  {
    forms {
      id
      title
    }
  }
`;

const styles = theme => ({
  container: {
    display: 'flex',
    flexWrap: 'wrap',
  },
  textField: {
    marginLeft: theme.spacing.unit,
    marginRight: theme.spacing.unit,
    width: 200,
  },
  button: {
    margin: theme.spacing.unit,
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
      refetchQueries: [{ query: GET_FORMS }] 
    }).then((response) => {
      const formId = response.data.createForm.id;
      // will produce redirect
      this.setState({ createdFormId: formId });
      this.props.setSelectedForm(formId); // using contextApi
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
      <Paper>
        <form onSubmit={this.sendForm} >
          <TextField
            id='standard-uncontrolled'
            label='Title'
            defaultValue=''
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
        </form>
      </Paper>
    );
  }
}

const enhance = compose(
  graphql(createForm, {name: 'createForm'}),
  withStyles(styles)
)

export default enhance(CreateForm);
