import React from 'react';
import { Link as RouterLink } from 'react-router-dom'
import PropTypes from 'prop-types';
import gql from 'graphql-tag';
import { graphql } from 'react-apollo';
import Link from '@material-ui/core/Link';
import { Paper, Typography, TextField, Button } from '@material-ui/core';

import adminPageContext from '../../adminPageContext';

const createSurvey = gql`
  mutation ($formId: ID!, $name: String!) {
    createSurvey(formId: $formId, name: $name) {
      id
      name
    }
  }
`;

const styles = {
  paper: {
    maxWidth: 500,
    padding: 40,
    marginBottom: 20,
  },
  texfield: {
    width: 400
  },
  submit: {
    marginTop: 40,
    marginBottom: 20,
    marginLeft: 330,
  }
}

class CreateSurveySubPage extends React.Component {
  constructor() {
    super()
    this.state = {
      pristine: true,
      isFormValid: true,
      inputName: ''
    }
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    const isFormValid = event.target.value.length > 2;
    this.setState({ inputName: event.target.value, pristine: false, isFormValid })
  }

  handleSubmit(event, setOpenPopulateSurvey) {
    event.preventDefault();
    // setOpenPopulateSurvey('done')
    this.props.createSurvey({
      variables: {
        name: this.state.inputName,
        formId: this.props.form.id
      },
    }).then((response) => {
      console.log('response', response);
    });
  }

  static getDerivedStateFromProps(props, state) {
    if (!state.pristine) {
      return state;
    }
    // setting survey name to form name by default
    if (!!props.form && props.form.title ) {
      return {
        inputName: props.form.title
      }
    }
    return state;
  }

  render() {
    const formId = this.props.isLoading ? '-' : this.props.form.id;
    const formTitle = this.props.isLoading ? '' : this.props.form.title;
    const linkFormPath = this.props.isLoading ? '' : '/admin/forms/' + formId;
    return (
      <adminPageContext.Consumer>
        {({ setOpenPopulateSurvey }) => (
          <form onSubmit={(event) => this.handleSubmit(event, setOpenPopulateSurvey)}>
            <Paper style={styles.paper}>
              <Typography>
                Form Id: {formId},
                <span> </span>
                <Link component={RouterLink} to={linkFormPath}>
                  {formTitle}
                </Link>
              </Typography>
              <h1>Create Survey</h1>
              <TextField
                style={styles.texfield}
                id="standard-name"
                label="Name"
                value={this.state.inputName}
                onChange={this.handleChange}
                margin="normal"
              />
              <div>
                <Button
                  disabled={!this.state.isFormValid}
                  style={styles.submit}
                  type="submit"
                  variant='contained'
                  color='primary'>
                  Create
                </Button>
              </div>
            </Paper>
          </form>
      )}
      </adminPageContext.Consumer>
    )
  }
};

CreateSurveySubPage.propTypes = {
  isLoading: PropTypes.bool.isRequired,
  form: PropTypes.object
};

export default graphql(createSurvey, {name: 'createSurvey'})(CreateSurveySubPage);
