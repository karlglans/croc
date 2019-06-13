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
    padding: 20,
    cursor: 'pointer'
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

const DELETE_FORM_QUESTION = gql`
  mutation($questionId: ID!, $formId: ID!) {
    removeQuestionFromForm(questionId: $questionId, formId: $formId) {
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
    this.handleRemoveQuestion = this.handleRemoveQuestion.bind(this);
    this.checkIfValid = this.checkIfValid.bind(this);
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
            text: this.state.inputText,
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
            text: this.state.inputText,
            questionType: this.state.questionType,
            id: this.props.editQuestionId
          }
        }
      }).then((response) => {
        // NOTE: a better solution would be to update cash here instead of excessive retun form backend
        this.setState({ inputText: ''});
        this.props.setEditQuestionId(undefined);
      })
    } else {
      console.error('missing valid question id');
    }
  }

  handleRemoveQuestion() {
    const { match } = this.props;
    const formId = match && match.params && match.params.formId ? match.params.formId : false;
    if (this.props.editQuestionId && formId) {
      this.props.removeFormQuestion({
        variables: {
          questionId: this.props.editQuestionId,
          formId
        }
      }).then((response) => {
        // NOTE: a better solution would be to update cash here instead of excessive retun form backend
        this.setState({ inputText: ''});
        this.props.setEditQuestionId(undefined);
      })
    } else {
      console.error('missing valid question id', formId, this.props.editQuestionId);
     }
  }
  
  handleChangeQuestionType(event) {
    this.setState({ questionType: event.target.value, isFormChanged: true });
  }

  static getDerivedStateFromProps(props, state) {
    if (props.editQuestionId !== state.editQuestionId) {
      const { form } = props;
      const { questions } = form;
      const question = questions.find(q => q.id === props.editQuestionId);
      const inputText = question ? question.text : '';

      const questionType = question && question.questionType ? question.questionType : questionTypes.NUMERIC;
      return { editQuestionId: props.editQuestionId, inputText, isFormChanged: false, questionType }
    }
    return null; // no change
  }

  checkIfValid() {
    const { inputText } = this.state;
    return inputText.length > 3;
  }

  render() {
    const { classes, editQuestionId } = this.props;
    const { isFormChanged } = this.state;
    const isAbleToSave = isFormChanged && this.checkIfValid();
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
              inputProps={{
                maxLength: 255,
              }}
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
          <Grid item sm={12} style={{marginTop: 20}}>
            
            {!editQuestionId && (
              <div style={{float: 'right'}} >
                <Button
                  variant='contained'
                  color='primary'
                  onClick={this.handleCreateQuestion}
                  disabled={!isAbleToSave}
                  className={classes.button}>
                  Store
                </Button>
              </div>)}
            {editQuestionId && (
              <div style={{float: 'right'}} >
                <Button
                  variant='contained'
                  color='primary'
                  onClick={this.handleRemoveQuestion}
                  className={classes.button}>
                  Remove
                </Button>
                <Button
                  variant='contained'
                  color='primary'
                  onClick={this.handleUpdateQuestion}
                  disabled={!isAbleToSave}
                  className={classes.button}>
                  Update
                </Button>
              </div>)}
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
  graphql(DELETE_FORM_QUESTION, {name: 'removeFormQuestion'}),
  withStyles(styles)
)

export default enhance(CreateQuestion);
