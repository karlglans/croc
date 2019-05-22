import React from 'react';
import PropTypes from 'prop-types';
import { IconButton  } from '@material-ui/core';
import { ArrowDownward, ArrowUpward } from '@material-ui/icons';
import gql from 'graphql-tag';
import { graphql } from 'react-apollo';

const SWAP_QUESTIONS_ON_FORM = gql`
  mutation($formId: ID!, $questionId: ID!, $destSpotNumber: Int!) {
    swapQuestionOnForm(formId: $formId, questionId: $questionId, destSpotNumber: $destSpotNumber) {
      id
      questions {
        id
        text
      }
    }
  }
`;

const styles = {
  container: {
    display: 'flex',
    justifyContent: 'space-between',
    flexDirection: 'column',
    height: '100%',
    width: '100%',
    alignItems: 'center'
  },
  arrowUp: {
    flex: 0
  },
  number: {
    flex: 0
  },
  arrowDown: {
    flex: 0
  }
}

class QuestionOrdering extends React.Component {
  constructor(props) {
    super(props);
    this.handleClickMove = this.handleClickMove.bind(this);
  }
  handleClickMove(destSpotNumber) {
    const { questionId, formId, isLastQuestion, number } = this.props;
    if (questionId && destSpotNumber !== 0 && !(isLastQuestion && destSpotNumber > number) ) {
      this.props.swapQuestion({
        variables: {
          formId, 
          questionId, 
          destSpotNumber
        }
      });
    } else {
      console.error('illigal action');
    }
  }
  render() {
    const { number, isLastQuestion } = this.props;
    return (
      <div style={styles.container} >
        { number === 1 && (<div aria-label="Up" style={styles.arrowUp}>{'\u00A0'}</div>)}
        { number !== 1 && (
          <IconButton aria-label="Up" style={styles.arrowUp} onClick={() => this.handleClickMove(number - 1)}>
            <ArrowUpward />
          </IconButton>
        )}
        <div style={styles.number}>{number}</div>
        { isLastQuestion && (<div aria-label="Down" style={styles.arrowUp}>{'\u00A0'}</div>)}
        { !isLastQuestion && (
          <IconButton aria-label="Down" style={styles.arrowDown} onClick={() => this.handleClickMove(number + 1)}>
            <ArrowDownward />
          </IconButton>
        )}
      </div>
    )
  }
};

QuestionOrdering.propTypes = {
  questionId: PropTypes.string.isRequired, 
  number: PropTypes.number.isRequired,
  formId: PropTypes.string.isRequired
};

export default graphql(SWAP_QUESTIONS_ON_FORM, {name: 'swapQuestion'})(QuestionOrdering);
