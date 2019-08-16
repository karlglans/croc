import React, { Component } from 'react';
import * as serviceWorker from '../../serviceWorker';
import { Redirect} from "react-router-dom";

import { Paper, Card, CardActionArea, Typography  } from '@material-ui/core';

import * as Role from '../../constants/domain/roleTypes';

const now = new Date();
const time = now.getTime();
const API_BASE_URL = 'http://localhost:8080';
const OAUTH2_REDIRECT_URI = process.env.REACT_APP_OAUTH2_REDIRECT || 'http://localhost:3000/oauth2/redirect';
const GOOGLE_AUTH_URL = API_BASE_URL + '/oauth2/authorize/google?redirect_uri=' + OAUTH2_REDIRECT_URI + '&t=' + time;

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
    const overideRedirectURL = process.env.REACT_APP_OAUTH2_REDIRECT;
    console.log('base url', overideRedirectURL, API_BASE_URL);
    console.log('selected url', overideRedirectURL ? overideRedirectURL : API_BASE_URL );
    console.log('selected url', overideRedirectURL || API_BASE_URL );
    console.log('DATAAA', process.env.DATAAA );
    this.props.client.resetStore()
    // serviceWorker.unregister();
  }

  clickSupervicor() {
    // user 3, a supervisor
    const token = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJjcm9jIiwic3ViIjozLCJlbWFpbCI6I' +
    'nN1cGVydmlzb3IyQHB1cnBsZS5jb20iLCJyb2xlcyI6InN1cGVydmlzb3IiLCJpYXQiOjE1NjU3NTE0NzN9.IrzYskNc' +
    'QlnueaRUZAiSj22k7HilskBaG_Iu9KOYHII';
    localStorage.setItem('apollotoken', token);
    localStorage.setItem('crocClient', Role.SUPERVISOR);
    this.setState({redirect: '/admin/surveys'});
  }
  clickUser() {
    // user 4, an ordinary user
    const token = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJjcm9jIiwic3ViIjo0LCJlbWFpbCI' +
      '6ImVtcGxveWVlMUBwdXJwbGUuY29tIiwicm9sZXMiOiJ1c2VyIiwiaWF0IjoxNTY1NzUwNzc4fQ.iXFK40_M6Qhq' +
      'GADKKc6fp6CIUgR8ALMHhA02k4gTxUY';
    localStorage.setItem('apollotoken', token);
    localStorage.setItem('crocClient', Role.USER);
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
          <a className="btn btn-block social-btn google" href={GOOGLE_AUTH_URL}>
            Log in with Google Account </a>
          <p>REACT_APP_OAUTH2_REDIRECT: {process.env.REACT_APP_OAUTH2_REDIRECT}</p>
          <p>OAUTH2_REDIRECT_URI: {OAUTH2_REDIRECT_URI}</p>
          <p>ummber: 11</p>
        </Paper>
      </div>
    );
  }
}

export default LoginPage;
