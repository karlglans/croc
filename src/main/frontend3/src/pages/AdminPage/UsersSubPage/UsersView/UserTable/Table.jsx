import React from 'react';
import PropTypes from 'prop-types';

const Table = ({ users, isLoading }) => {
    return (
      <div>
        Table 
      </div>
    )
};

Table.propTypes = {
  users: PropTypes.array,
  isLoading: PropTypes.bool.isRequired
};

export default Table;