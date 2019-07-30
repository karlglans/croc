import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import CircularProgress from '@material-ui/core/CircularProgress';

const useStyles = makeStyles(theme => ({
  root: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    width: '100%',
  },
  progress: {
    flex: -1,
  },
}));

const CircularIndeterminate = ({ minHeight }) => {
  const classes = useStyles();
  const style = { minHeight }
  return (
    <div className={classes.root} style={style}>
      <CircularProgress className={classes.progress} />
    </div>
  );
}

export default function withLoadingIndicator(Component) {
  return function WihLoadingComponent({ isLoading, loadingErrors, minHeight, ...props }) {
    if (!isLoading) return (<Component {...props} />);
    if (loadingErrors) {
      console.log('loading errors', loadingErrors.message);
      return (<p>`Error! ${loadingErrors.message}`</p>);
    }
    return (<CircularIndeterminate minHeight={minHeight} />);
  }
}