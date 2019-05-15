import React from 'react';
import PropTypes from 'prop-types';
import { graphql } from 'react-apollo';
import gql from 'graphql-tag';
import { Button, Card, CardContent, Typography } from '@material-ui/core';

const ADD_USERGROUP_TO_SURVEY = gql`
  mutation($surveyId: ID!) {
    startSurvey(surveyId: $surveyId)
  }
`;

const style = {
  padding: 30,
  paddingTop: 25,
  marginTop: 20,
  marginBottom: 0,
  width: '100%',
}

class AddParticipants extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      buttonClicked: false
    }
    this.handleStartSurvey = this.handleStartSurvey.bind(this);
  }

  handleStartSurvey() {
    this.setState({buttonClicked: true})
    this.props.startSurvey({
      variables: {
        surveyId: this.props.surveyId,
      },
      // refetchQueries: [{ query: GET_PARTICPANT_DATA, variables: {surveyId : this.props.surveyId}}]
    }).then( ()=> {
      console.log('start survey done');
    });
  }

  render() {
    return (
      <Card style={style}>
        <CardContent>
          <Typography color="textSecondary" gutterBottom>
            Start Survey
          </Typography>
          <Button variant="outlined" onClick={this.handleStartSurvey} disabled={this.state.buttonClicked}>Start Survey</Button>
        </CardContent>
      </Card>
    );
  }
};

AddParticipants.propTypes = {
  surveyId: PropTypes.number.isRequired
};

export default graphql(ADD_USERGROUP_TO_SURVEY, {name: 'startSurvey'})(AddParticipants);
