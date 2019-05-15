import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Drawer from '@material-ui/core/Drawer';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import MailIcon from '@material-ui/icons/Mail';

const drawerWidth = 240;

const styles = theme => ({
  root: {
    display: 'flex',
  },
  drawer: {
    width: drawerWidth,
    flexShrink: 0,
  },
  drawerPaper: {
    width: drawerWidth,
  },
  halfScreen: {
    flex: 1
  },
  content: {
    flexGrow: 1,
    padding: theme.spacing.unit * 3,
  },
  toolbar: theme.mixins.toolbar,
});


class UsersPage extends React.Component {
  componentDidMount() {
    console.log('componentDidMount FormsPage2')
  }
  render() {
    const { classes } = this.props;
    return (
      <React.Fragment>
      <Drawer
        className={classes.drawer}
        variant="permanent"
        classes={{
          paper: classes.drawerPaper,
        }}
      >
        <div className={classes.toolbar} />
        <List>
          <ListItem button key={'list'}>
            <ListItemIcon> <MailIcon /> </ListItemIcon>
            <ListItemText primary={'List'} />
          </ListItem>
          <ListItem button key={'create'}>
            <ListItemIcon> <MailIcon /> </ListItemIcon>
            <ListItemText primary={'Create'} />
          </ListItem>
          <ListItem button key={'edit'}>
            <ListItemIcon> <MailIcon /> </ListItemIcon>
            <ListItemText primary={'Edit'} />
          </ListItem>
        </List>
      </Drawer>
      <div className={classes.content} style={{marginLeft: drawerWidth }}>
        <div className={classes.toolbar} />
        <div style={{display: 'flex', flexWrap: 'wrap', justifyContent: 'flex-start', flexDirection: 'row' }}>
            <div className={classes.halfScreen} >
              <Paper style={{width: 800, backgroundColor: 'blue'}}>
                <p>ssss2 ssss2 ssss2 ssss2 ssss2</p>
                <p>ssss3</p>
                <p>ssss4</p>
                <p>ssss1</p>
                <p>ssss2</p>
                <p>ssss3</p>
                <p>ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4</p>
              </Paper>
            </div>
            <div className={classes.halfScreen} style={{}}>
              <Paper style={{width: 800, backgroundColor: 'yellow'}}>
                <p>ssss2 ssss2 ssss2 ssss2 ssss2</p>
                <p>ssss3</p>
                <p>ssss4</p>
                <p>ssss1</p>
                <p>ssss2</p>
                <p>ssss3</p>
                <p>ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4</p>
              </Paper>
            </div> 
          </div>
        </div>
      </React.Fragment>
    )
  };

}
export default withStyles(styles)(UsersPage);
