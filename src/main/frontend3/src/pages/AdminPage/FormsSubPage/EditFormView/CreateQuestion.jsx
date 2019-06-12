import React from 'react';
import { compose } from 'recompose';
import { withRouter } from "react-router-dom";
import { withStyles } from '@material-ui/core/styles';
import { Paper, Grid, TextField, Button, Select, Input, MenuItem } from '@material-ui/core';
import { graphql } from 'react-apollo';
import gql from 'graphql-tag';

import * as questionTypes from '../../../../constants/domain/questionTypes';

const styles = theme => ({
  textField: {
    marginLeft: theme.spacing(0),
    marginRight: theme.spacing(2),
  },
  button: {
    margin: theme.spacing(2),
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

const RELOAD_FORM = gql`
  query($formId: ID!) {
    form(formId: $formId) {
      id
      questions {
        id
        text
        questionType
      }
    }
  }
`;
// this update action pulls down a form, maybe not optimal
const UPDATE_FORM_QUESTION = gql`
  mutation($inquest: InputQuestion!) {
    updateQuestion(input: $inquest) {
      id
      questions {
        id
        text
        questionType
      }
    }
  }
`;

class CreateQuestion extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      inputText: '',
      questionType: questionTypes.NUMERIC,
      isFormChanged: false,
      editQuestionId: undefined
    }
    this.setInputText = this.setInputText.bind(this);
    this.handleCreateQuestion = this.handleCreateQuestion.bind(this);
    this.handleUpdateQuestion = this.handleUpdateQuestion.bind(this);
    this.handleChangeQuestionType = this.handleChangeQuestionType.bind(this);
  }
  setInputText(event) {
    this.setState({ inputText: event.target.value, isFormChanged: true});
  }
  handleCreateQuestion(event) {
    event.preventDefault();
    const { match } = this.props;
    const formId = match && match.params && match.params.formId ? match.params.formId : false;
    if (formId) {
      this.props.createFormQuestion({
        variables: {
          inquest: {
            'text': this.state.inputText,
            questionType: this.state.questionType
          },
          formId
        },
        refetchQueries: [{ query: RELOAD_FORM, variables: {formId}}]
      }).then((response) => {
        this.setState({ inputText: ''});
      })
    } else {
      console.error('missing valid form id');
    }
  }
  handleUpdateQuestion() {
    if (this.props.editQuestionId) {
      this.props.updateQuestion({
        variables: {
          inquest: {
            'text': this.state.inputText,
            questionType: this.state.questionType,
            id: this.props.editQuestionId
          }
        }
      }).then((response) => {
        this.setState({ inputText: ''});
        this.props.setEditQuestionId(undefined);
      })
    } else {
      console.error('missing valid question id');
    }
  }
  handleChangeQuestionType(event) {
    console.log('handleChangeQuestionType', event.target.value);
    this.setState({ questionType: event.target.value});
  }
  static getDerivedStateFromProps(props, state) {
    if (props.editQuestionId !== state.editQuestionId) {
      console.log('getDerivedStateFromProps')
      const { form } = props;
      const { questions } = form;
      // const questions = props.form.questions;
      const question = questions.find(q => q.id === props.editQuestionId);
      const inputText = question ? question.text : '';
      console.log('handleChangeQuestionType() questionType', !!question && question.questionType);
      const questionType = question && question.questionType ? question.questionType : questionTypes.NUMERIC;
      return { editQuestionId: props.editQuestionId, inputText, isFormChanged: false, questionType }
    }
    return null; // no change
  }

  render() {
    const { classes, editQuestionId } = this.props;
    const inputLabel = editQuestionId ?  'Edit Question' : 'New Question';
    return (
      <Paper className={classes.paper}>
        <Grid container>
          <Grid item sm={12}>
            <h2>Edit question</h2>
            <br />
            <TextField
              id="question-text"
              label={inputLabel}
              multiline
              fullWidth
              rowsMax="4"
              value={this.state.inputText}
              onChange={this.setInputText}
              className={classes.textField}
              margin="normal"
              variant="outlined"
            />
          </Grid>
          <Grid item sm={6} style={{marginTop: 20}}>
          <Select
              value={this.state.questionType}
              onChange={this.handleChangeQuestionType}
              input={<Input name="age" id="age-helper" />}
            >
              <MenuItem value={questionTypes.NUMERIC}>Numeric 6</MenuItem>
              <MenuItem value={questionTypes.YESNO}>Yes or NO</MenuItem>
            </Select>
          </Grid>
          <Grid item sm={6} style={{marginTop: 20}}>
            <div style={{float: 'right'}} >
            {!editQuestionId &&
              (<Button
                variant='contained'
                color='primary'
                onClick={this.handleCreateQuestion}
                disabled={!this.state.isFormChanged}
                className={classes.button}>
                Store
              </Button>)}
            {editQuestionId &&
              (<Button
                variant='contained'
                color='primary'
                onClick={this.handleUpdateQuestion}
                disabled={!this.state.isFormChanged}
                className={classes.button}>
                Update
              </Button>)}
            </div>
          </Grid>
        </Grid>
      </Paper>
    )
  }
};

const enhance = compose(
  withRouter,
  graphql(CREATE_FORM_QUESTION, {name: 'createFormQuestion'}),
  graphql(UPDATE_FORM_QUESTION, {name: 'updateQuestion'}),
  withStyles(styles)
)

export default enhance(CreateQuestion);
