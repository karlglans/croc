import React from 'react';
import PropTypes from 'prop-types';
import { RadioGroup, FormControlLabel, Radio, FormControl } from '@material-ui/core';

class Answer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      value: undefined
    }
    this.handleSelect = this.handleSelect.bind(this)
  }

  static getDerivedStateFromProps(props) {
    console.log('getDerivedStateFromProps', props.answer.value);
    return { value: props.answer.value}
  }

  handleSelect(event) {
    console.log('handleClick', event.target.value, this.props.answer);
    // this.setState({value: event.target.value})
  }

  render() {
    return (
      <FormControl component="fieldset">
        <RadioGroup
          aria-label="position"
          name="position"
          value={String(this.state.value)}
          onChange={this.handleSelect}
          row
        >
          <FormControlLabel
            value="1"
            control={<Radio color="primary" />}
            label="1"
            labelPlacement="top"
          />
          <FormControlLabel
            value="2"
            control={<Radio color="primary" />}
            label="2"
            labelPlacement="top"
          />
          <FormControlLabel
            value="3"
            control={<Radio color="primary" />}
            label="3"
            labelPlacement="top"
          />
          <FormControlLabel
            value="4"
            control={<Radio color="primary" />}
            label="4"
            labelPlacement="top"
          />
          <FormControlLabel
            value="5"
            control={<Radio color="primary" />}
            label="5"
            labelPlacement="top"
          />
          <FormControlLabel
            value="6"
            control={<Radio color="primary" />}
            label="6"
            labelPlacement="top"
          />
        </RadioGroup>
      </FormControl>
    );
  }
}

Answer.propTypes = {
  answer: PropTypes.object,
};

export default Answer;