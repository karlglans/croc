import React, { Component } from 'react';
import { Redirect} from "react-router-dom";

import { Paper, Card, CardActionArea, Typography  } from '@material-ui/core';

const styles = {
  container: {
    display: 'flex',
    width: 'calc(100vw)',
    height: 'calc(100vh)',
    backgroundColor: 'red',
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
  constructor() {
    super();
    this.state = {
      redirect: undefined
    }
    this.clickSupervicor = this.clickSupervicor.bind(this);
    this.clickUser = this.clickUser.bind(this);
  }

  clickSupervicor() {
    localStorage.setItem('apollotoken', 3);
    localStorage.setItem('crocClient', 'supervicor');
    this.setState({redirect: '/admin/surveys'});
  }
  clickUser() {
    localStorage.setItem('apollotoken', 4);
    localStorage.setItem('crocClient', 'user');
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
