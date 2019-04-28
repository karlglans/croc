import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Drawer from '@material-ui/core/Drawer';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import MailIcon from '@material-ui/icons/Mail';

import FormsList from '../../FormsList';

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


class FormsPage extends React.Component {

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
        <Grid container>
          <Grid item sm={12} md={6} lg={5}>
            <FormsList/>
          </Grid>
          <Grid item sm={12} md={6} lg={5}>
            <p>ssss2 ssss2 ssss2 ssss2 ssss2</p>
            <p>ssss3</p>
            <p>ssss4</p>
            <p>ssss1</p>
            <p>ssss2</p>
            <p>ssss3</p>
            <p>ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4 ssss4</p>
          </Grid>
        </Grid>
        </div>
      </React.Fragment>
    )
  };

}

export default withStyles(styles)(FormsPage);
