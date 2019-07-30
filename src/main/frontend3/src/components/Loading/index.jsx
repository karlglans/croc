import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import CircularProgress from '@material-ui/core/CircularProgress';

const useStyles = makeStyles(theme => ({
  root: {
    display: 'flex',
    justifyContent: 'center',
    width: '100%',
  },
  progress: {
    flex: -1,
  },
}));

const CircularIndeterminate = () => {
  const classes = useStyles();
  return (
    <div className={classes.root}>
      <CircularProgress className={classes.progress} />
    </div>
  );
}

export default function WithLoading(Component) {
  return function WihLoadingComponent({ isLoading, ...props }) {
    if (!isLoading) return (<Component {...props} />);
    return (<CircularIndeterminate />);
  }
}