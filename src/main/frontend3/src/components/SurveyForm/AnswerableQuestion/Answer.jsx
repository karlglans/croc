import React from 'react';
import PropTypes from 'prop-types';
import { RadioGroup, FormControlLabel, Radio, FormControl } from '@material-ui/core';

import { Mutation } from 'react-apollo';
import gql from 'graphql-tag';

import getUserId from '../../../temp/getUserId';

const UPDATE_ANSWER = gql`
  mutation($surveyId: ID!, $userId: ID!, $questionId: ID!, $value: Int!) {
    updateAnswer(surveyId: $surveyId, userId: $userId, questionId: $questionId, value: $value) {
      value
    }
  }
`;

const GET_SURVEYS_DATA = gql`
  query($surveyId: ID!, $userId: ID!) {
    answers(surveyId: $surveyId, userId: $userId) {
      questionId
      value
    }
  }
`;

class Answer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      valueFromStore: undefined, // value from backend
      valueFromClick: undefined, // value from clickaction, will have higher display priority
      clickTime: undefined
    }
    this.handleSelect = this.handleSelect.bind(this);
  }

  static getDerivedStateFromProps(props) {
    if (!!props.storedAnswer) {
      return { valueFromStore: props.storedAnswer.value}
    }
    return null;
  }

  /**
   * presenting the selected value, before it has been received form backend, to make experience
   * more smoth.
   * */ 
  handleSelect(value) {
    this.setState({valueFromClick: value})
  }

  render() {
    // the value from click will be displayed if it exists
    const value = this.state.valueFromClick || this.state.valueFromStore;
    return (
      <Mutation 
        mutation={UPDATE_ANSWER}
        // update the cash with the result
        update={(cache, { data: { updateAnswer } }) => {
          const { questionId, surveyId }  = this.props;
          const userId = getUserId();
          // reading stored answers from cache and then modifying a stored answer
          const { answers } = cache.readQuery({ query: GET_SURVEYS_DATA, variables: { surveyId, userId } });
          let alreadyStoredAanswer = answers.find(answer => answer.questionId === questionId )
          if (!!alreadyStoredAanswer) {
            alreadyStoredAanswer.value = updateAnswer.value; // copy from graphql response
            cache.writeQuery({
              query: GET_SURVEYS_DATA,
              variables: { surveyId, userId },
              data: { answers },
            });
          }
        }}
      >
        {(updateAnswer, { data }) => (
            <FormControl component="fieldset">
              <RadioGroup
                aria-label="position"
                name="position"
                value={String(value)}
                onChange={ event => {
                  const { questionId, surveyId }  = this.props;
                  const value = event.target.value;
                  const userId = getUserId();
                  this.handleSelect(value);
                  updateAnswer({ variables: { questionId, surveyId, value, userId } })
                }}
                row
              >
                <FormControlLabel
                  value="1"
                  control={<Radio color="primary" />}
                  label="1"
                  labelPlacement="top"
                />
                <FormControlLabel
                  value="2"
                  control={<Radio color="primary" />}
                  label="2"
                  labelPlacement="top"
                />
                <FormControlLabel
                  value="3"
                  control={<Radio color="primary" />}
                  label="3"
                  labelPlacement="top"
                />
                <FormControlLabel
                  value="4"
                  control={<Radio color="primary" />}
                  label="4"
                  labelPlacement="top"
                />
                <FormControlLabel
                  value="5"
                  control={<Radio color="primary" />}
                  label="5"
                  labelPlacement="top"
                />
                <FormControlLabel
                  value="6"
                  control={<Radio color="primary" />}
                  label="6"
                  labelPlacement="top"
                />
              </RadioGroup>
            </FormControl>
        )}
      </Mutation>
    );
  }
}

Answer.propTypes = {
  storedAnswer: PropTypes.object,
  questionId: PropTypes.string.isRequired,
  surveyId: PropTypes.string.isRequired,
};

export default Answer;
