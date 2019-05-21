import React from 'react';
import { compose } from 'recompose'
import { withRouter } from "react-router-dom";
import { withStyles } from '@material-ui/core/styles';
import { Paper, Grid, TextField, Button } from '@material-ui/core';
import { graphql } from 'react-apollo';
import gql from 'graphql-tag';

const styles = theme => ({
  textField: {
    marginLeft: theme.spacing.unit,
    marginRight: theme.spacing.unit,
  },
  button: {
    margin: theme.spacing.unit,
  },
  paper: {
    padding: 20
  }
});

const CREATE_FORM_QUESTION = gql`
  mutation($inquest: InputQuestion!, $formId: ID!) {
    createFormQuestion(input: $inquest, formId: $formId) {
      id
    }
  }
`;

const LOAD_FORM = gql`
  query($formId: ID!) {
    form(formId: $formId) {
      id
      questions {
        id
        text
      }
    }
  }
`;

class EditQuestion extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      inputText: ''
    }
    this.setInputText = this.setInputText.bind(this);
    this.handleCreateQuestion = this.handleCreateQuestion.bind(this);
  }
  setInputText(event) {
    this.setState({ inputText: event.target.value});
  }
  handleCreateQuestion(event) {
    event.preventDefault();
    const { match } = this.props;
    const formId = match && match.params && match.params.formId ? match.params.formId : false;
    if (formId) {
      this.props.createFormQuestion({
        variables: {
          inquest: {
            'text': this.state.inputText
          },
          formId
        },
        refetchQueries: [{ query: LOAD_FORM, variables: {formId}}]
      }).then((response) => {
        this.setState({ inputText: ''});
      })
    } else {
      console.error('missing valid form id');
    }
  }
  render() {
    const { match, classes } = this.props;
    const formId = match && match.params && match.params.formId ? match.params.formId : false;
    return (
      <Paper className={classes.paper}>
        <Grid container>
          <h2>Edit question</h2>
          <br />
          <TextField
            id="outlined-multiline-flexible"
            label="New Question"
            multiline
            fullWidth
            rowsMax="4"
            value={this.state.inputText}
            onChange={this.setInputText}
            className={classes.textField}
            margin="normal"
            variant="outlined"
          />
          <Button
            variant='contained'
            color='primary'
            onClick={this.handleCreateQuestion}
            className={classes.button}>
            Store
          </Button>
          formId: {formId}
        </Grid>
      </Paper>
    )
  }
};

const enhance = compose(
  withRouter,
  graphql(CREATE_FORM_QUESTION, {name: 'createFormQuestion'}),
  withStyles(styles)
)

export default enhance(EditQuestion);
