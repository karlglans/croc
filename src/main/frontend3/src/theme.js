import { red } from '@material-ui/core/colors';
import { createMuiTheme } from '@material-ui/core/styles';

const theme = createMuiTheme({
  spacing: 16,
  palette: {
    primary: {
      main: '#556cd6', // #556cd6
    },
    secondary: {
      main: '#19857b', // #19857b
    },
    error: {
      main: red.A400,
    },
    background: {
      default: '#fff',
    },
  },
});

export default theme;