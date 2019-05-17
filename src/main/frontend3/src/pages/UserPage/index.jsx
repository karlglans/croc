import React from 'react';
import { Redirect } from 'react-router-dom';
import Layout from './Layout';

class UserPage extends React.Component {
  constructor(props) {
    super(props);
    const redirectPath = localStorage.getItem('crocClient') === 'user' ? undefined : '/login';
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