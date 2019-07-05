#!/usr/bin/env node

// ideas from https://developer.atlassian.com/blog/2015/11/scripting-with-node/

// TEMP: will generate tokens form example users
// npm run tools -- token --secret cjbevb5mjfst9 --roles "USER ADMIN" --email supervisor2@purple.com --id 3
// npm run tools -- token --secret cjbevb5mjfst9 --roles USER --email employee1@purple.com --id 4

const program = require('commander');

program
  .option('-x, --secret <secret>', 'Secret')
  .option('--email <email>', 'Email')
  .option('--roles <roles>', 'Roles. Caps. If multiple then use \"')
  .option('--id <id>', 'Id, user database id. Remove and replace with subject identifyer.')
  .action(function(action) {
    if (action === "token") {
      require('./src/token-maker').Main(program.secret, program.roles, program.email, program.id);
    } else {
      console.log("no action")
    }
  })
  .parse(process.argv);
