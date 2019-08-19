import React from 'react';
import { withStyles, lighten } from '@material-ui/core/styles';
import { Card, CardContent } from '@material-ui/core';
import { compose } from 'recompose';

const styles = theme => ({
  root: {
    display: 'flex',
    backgroundColor: lighten(theme.palette.primary.light, 0.85),
    height: '100vh',
    justifyContent: 'space-around',
    alignItems: 'center',
  },
  card: {
    flex: -1,
    height: 200,
    width: 375,
  }
});

class AccountSubPage extends React.Component {
  render() {
    const { classes } = this.props;
    return (
      <div className={classes.root} >
        <Card className={classes.card} >
          <CardContent>
            <div className={classes.content}>
                Your account still needs to be activated by an admin.
            </div>
          </CardContent>
        </Card >
      </div>
    )
  };
}

const enhance = compose(
  withStyles(styles),
)

export default enhance(AccountSubPage);
