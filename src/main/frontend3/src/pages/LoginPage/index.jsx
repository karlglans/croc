import React, { Component } from 'react';
import { Redirect} from "react-router-dom";

import { Paper, Card, CardActionArea, Typography  } from '@material-ui/core';

import * as Role from '../../constants/domain/roleTypes'

const styles = {
  container: {
    display: 'flex',
    width: 'calc(100vw)',
    height: 'calc(100vh)',
    backgroundColor: 'rgb(207, 213, 243)',
    justifyContent: 'space-around',
    alignItems: 'center',
  },
  centerBox: {
    width: 300,
    minHeight: 100,
    flex: -1,
    padding: 20
  },
  card: {
    backgroundColor: '#eee',
    height: 100,
  }

}

class LoginPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      redirect: undefined
    }
    this.clickSupervicor = this.clickSupervicor.bind(this);
    this.clickUser = this.clickUser.bind(this);
  }

  componentDidMount() {
    this.props.client.resetStore()
  }

  clickSupervicor() {
    // user 3, a supervisor
    const token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJjcm9jIiwic3ViIjoiZ29vZ2xlL" +
      "TExMTExIiwiZW1haWwiOiJzdXBlcnZpc29yMkBwdXJwbGUuY29tIiwicm9sZXMiOiJVU0VSIFN" +
      "VUEVSIiwiaWQiOjMsImlhdCI6MTU2MjM0MzE3NH0.2AuO28w248jrkwQTu-CynybNSEvcPD-XQBIiJlX13qc";
    localStorage.setItem('apollotoken', token);
    localStorage.setItem('crocClient', Role.SUPERVISOR);
    // localStorage.setItem('crocClientId', '3');
    this.setState({redirect: '/admin/surveys'});
  }
  clickUser() {
    // user 4, an ordinary user
    const token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJjcm9jIiwic3ViIjoiZ29vZ2xlL" + 
      "TExMTExIiwiZW1haWwiOiJlbXBsb3llZTFAcHVycGxlLmNvbSIsInJvbGVzIjoiVVNFUiIsI" + 
      "mlkIjo0LCJpYXQiOjE1NjIyNzk2MTd9.wCk6xLNczwtPBN93vEtxnKty2WHgYjM9c_fMoMxpQi4";
    localStorage.setItem('apollotoken', token);
    localStorage.setItem('crocClient', Role.USER);
    // localStorage.setItem('crocClientId', '4');
    this.setState({redirect: '/surveys'});
  }

  render() {
    if (this.state.redirect) {
      return(<Redirect to={this.state.redirect} />)
    }
    
    return (
      <div style={styles.container}>
        <Paper style={styles.centerBox}>
          <Card style={styles.card}>
            <CardActionArea style={{width: '100%', height: '100%', padding: 20}} onClick={this.clickSupervicor}>
              <Typography gutterBottom variant="h5" component="h2">
                Supervisor
              </Typography>
            </CardActionArea>
          </Card>

          <Card style={{ ...styles.card, marginTop: 20}} onClick={this.clickUser}>
            <CardActionArea style={{width: '100%', height: '100%', padding: 20}}>
              <Typography gutterBottom variant="h5" component="h2">
                User
              </Typography>
            </CardActionArea>
          </Card>
        </Paper>
      </div>
    );
  }
}

export default LoginPage;
