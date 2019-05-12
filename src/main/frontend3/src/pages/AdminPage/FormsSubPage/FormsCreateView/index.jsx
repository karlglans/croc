import React from 'react';
import { Grid } from '@material-ui/core';
import CreateForm from './CreateFrom2';
import adminPageContext from '../../adminPageContext';

const FormsCreateView = () => (
  <adminPageContext.Consumer>
    {({selectedFormId, setSelectedForm}) => (
      <Grid container>
      <Grid item sm={12} md={6} lg={5}>
        <CreateForm selectedFormId={selectedFormId} setSelectedForm={setSelectedForm} />
      </Grid>
      <Grid item sm={12} md={6} lg={5}>
      </Grid>
    </Grid>
    )}
  </adminPageContext.Consumer>
);

export default FormsCreateView;