import React from 'react';
import PropTypes from 'prop-types';
import { RadioGroup, FormControlLabel, Radio, FormControl } from '@material-ui/core';

import { Mutation } from 'react-apollo';
import gql from 'graphql-tag';

import getUserId from '../../../temp/getUserId';

// retruns Answer: { participantId, questionId, value }
const UPDATE_ANSWER = gql`
  mutation($surveyId: ID!, $userId: ID!, $questionId: ID!, $value: Int!) {
    updateAnswer(surveyId: $surveyId, userId: $userId, questionId: $questionId, value: $value) {
      value
    }
  }
`;

const UPDATE_SURVEY = gql`
  query($surveyId: ID!) {
    survey(id: $surveyId) {
      id
      ownStatus {
        completedAnswers
      }
    }
  }
`;

// returns array of answers [{ participantId, questionId, value }]
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
    // const { querySurveyAfterUpdate } = this.props;
    // the value from click will be displayed if it exists
    const value = this.state.valueFromClick || this.state.valueFromStore;
    return (
      <Mutation 
        mutation={UPDATE_ANSWER}
        // update the cash with the result
        update={(cache, { data: { updateAnswer } }) => {
          const { questionId, surveyId, survey }  = this.props;
          const userId = getUserId();
          // reading stored answers from cache and then modifying a stored answer
          const { answers: cachAnswers } = cache.readQuery({ query: GET_SURVEYS_DATA, variables: { surveyId, userId } });
          const alreadyStoredAanswer = cachAnswers.find(answer => answer.questionId === questionId )
          if (!!alreadyStoredAanswer) {
            // This block will update the graphql-cash with the changed answer
            alreadyStoredAanswer.value = updateAnswer.value;
            cache.writeQuery({
              query: GET_SURVEYS_DATA,
              variables: { surveyId, userId },
              data: { answers: cachAnswers },
            });
          } else {
            // This block will add the answer to the survey in graphql-cash.
            cachAnswers.push({questionId, value: updateAnswer.value, __typename:"Answer"})
            cache.writeQuery({
              query: GET_SURVEYS_DATA,
              variables: { surveyId, userId },
              data: { answers: cachAnswers },
            });
          }
          // update survey status isComplete inCash, if needed 
          if (!this.props.hasOtherMissingAnswers(questionId)) {
            survey.ownStatus.completedAnswers = true;
            cache.writeQuery({
              query: UPDATE_SURVEY,
              variables: { surveyId },
              data: { survey },
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
                  key={'yes'}
                  value={String('1')}
                  control={<Radio color="primary" />}
                  label={'Yes'}
                  labelPlacement="top"
                />
                <FormControlLabel
                  key={'no'}
                  value={String('2')}
                  control={<Radio color="primary" />}
                  label={'No'}
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
  hasOtherMissingAnswers: PropTypes.func.isRequired,
};

export default Answer;
