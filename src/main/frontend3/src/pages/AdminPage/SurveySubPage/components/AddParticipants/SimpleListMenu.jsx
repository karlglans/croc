import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import { List, ListItem, ListItemText, MenuItem, Menu } from '@material-ui/core';

const styles = theme => ({
  root: {
    width: '100%',
    maxWidth: 360,
    backgroundColor: theme.palette.background.paper,
  },
});

class SimpleListMenu extends React.Component {
  state = {
    anchorEl: null,
    selectedIndex: 1,
  };

  handleClickListItem = event => {
    this.setState({ anchorEl: event.currentTarget });
  };

  handleMenuItemClick = (event, index) => {
    this.props.setSelectedGroupId(this.props.userGroups[index].id);
    this.setState({ selectedIndex: index, anchorEl: null });
  };

  handleClose = () => {
    this.setState({ anchorEl: null });
  };

  render() {
    const { classes } = this.props;
    const { anchorEl } = this.state;

    return (
      <div className={classes.root}>
        <List component="nav">
          <ListItem
            button
            aria-haspopup="true"
            aria-controls="group-select-menu"
            aria-label="Select group"
            onClick={this.handleClickListItem}
          >
            <ListItemText
              primary="Select group"
              secondary={this.props.userGroups[this.state.selectedIndex].name}
            />
          </ListItem>
        </List>
        <Menu
          id="group-select-menu"
          anchorEl={anchorEl}
          open={Boolean(anchorEl)}
          onClose={this.handleClose}
        >
          {this.props.userGroups.map((userGroup, index) => (
            <MenuItem
              key={userGroup.id}
              selected={index === this.state.selectedIndex}
              onClick={event => this.handleMenuItemClick(event, index)}
            >
              {userGroup.name}
            </MenuItem>
          ))}
        </Menu>
      </div>
    );
  }
}

SimpleListMenu.propTypes = {
  classes: PropTypes.object.isRequired,
  userGroups: PropTypes.array.isRequired
};

export default withStyles(styles)(SimpleListMenu);