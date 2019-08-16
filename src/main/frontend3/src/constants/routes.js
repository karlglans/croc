import * as Roles from './domain/roleTypes'; 

export const toLoginPage = '/login-portal';
export const toPendingUserPage = '/pending';
export const toAdminPage = '/admin';
export const userPage = '/';

export const startPages = {
  [Roles.SUPERVISOR]: '/admin/surveys',
  [Roles.USER]: '/surveys',
  [Roles.PENDING]: '/pending'
};

export default {
  toLoginPage, toAdminPage, userPage, startPages
}