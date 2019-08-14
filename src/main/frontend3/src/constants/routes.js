import * as Roles from './domain/roleTypes'; 

const toLoginPage = '/login';
const adminPage = '/admin';
const userPage = '/';

const startPages = {
  [Roles.SUPERVISOR]: '/admin/surveys',
  [Roles.USER]: '/surveys',
  [Roles.PENDING]: '/pending'
};

export default {
  toLoginPage, adminPage, userPage, startPages
}