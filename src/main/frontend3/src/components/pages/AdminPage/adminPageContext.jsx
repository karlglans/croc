import React from 'react';

export const adminPageContext = React.createContext({
  selectedFormId: undefined, // if set => user has created a new form and can add questiosn to it
  selectedFormIdForSurvey: undefined, // if set => user is creating a survey from this from
  selectedSurveyId: undefined // if set => user is editing a survey
});

export default adminPageContext;
