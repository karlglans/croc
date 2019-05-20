import React from 'react';
import PropTypes from 'prop-types';
import { graphql } from 'react-apollo';
import gql from 'graphql-tag';
import { Button  } from '@material-ui/core';

import SimpleListMenu from './SimpleListMenu';

const ADD_USERGROUP_TO_SURVEY = gql`
  mutation($userGroupId: ID!, $surveyId: ID!) {
    addUserGroupToSurvey(userGroupId: $userGroupId, survey: $surveyId)
  }
`;

const GET_PARTICPANT_DATA = gql`
  query($surveyId: ID!) {
    survey(id: $surveyId) {
      participants {
        id
        email
      }
    }
  }
`;

class AddParticipants extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      selectedGroupId: undefined,
    }
    this.setSelectedGroupId = this.setSelectedGroupId.bind(this);
    this.handleAddUsersToSurvey = this.handleAddUsersToSurvey.bind(this);
  }

  setSelectedGroupId(groupId) {
    this.setState({ selectedGroupId: groupId});
  }

  handleAddUsersToSurvey() {
    if (this.state.selectedGroupId) {
      this.setState({selectedGroupId: undefined});
      this.props.addUserGroupToSurvey({
        variables: {
          userGroupId: this.state.selectedGroupId,
          surveyId: this.props.surveyId,
        },
        refetchQueries: [{ query: GET_PARTICPANT_DATA, variables: {surveyId : this.props.surveyId}}]
      });
    }
  }

  render() {
    return (
      <div>
        <SimpleListMenu key={this.props.surveyId} userGroups={this.props.userGroups} setSelectedGroupId={this.setSelectedGroupId} />
        <Button variant="outlined" onClick={this.handleAddUsersToSurvey}>Add users</Button>
      </div>
    );
  }
};

AddParticipants.propTypes = {
  surveyId: PropTypes.string.isRequired,
  userGroups: PropTypes.array.isRequired
};

export default graphql(ADD_USERGROUP_TO_SURVEY, {name: 'addUserGroupToSurvey'})(AddParticipants);
