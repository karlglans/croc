import React from 'react';
import { Redirect } from 'react-router-dom';
import Layout from './Layout';
import * as Role from '../../constants/domain/roleTypes';
import * as routes from '../../constants/routes';

class UserPage extends React.Component {
  constructor(props) {
    super(props);
    const redirectPath = localStorage.getItem('crocClient') === Role.USER ? undefined : routes.toLoginPage;
    this.state = {
      redirectPath
    };
  }

  render() {
    if (this.state.redirectPath) {
      return(<Redirect to={this.state.redirectPath} />)
    }
    return (
    <div>
      <Layout/>
    </div>
    )
  };
}


export default UserPage;