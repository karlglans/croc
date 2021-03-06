#!/usr/bin/env node

// ideas from https://developer.atlassian.com/blog/2015/11/scripting-with-node/

// TEMP: will generate tokens form example users
// npm run tools -- token --secret cjbevb5mjfst9 --roles supervisor --email supervisor2@purple.com --sub 3
// npm run tools -- token --secret cjbevb5mjfst9 --roles user --email employee1@purple.com --sub 4


const program = require('commander');

program
  .option('-x, --secret <secret>', 'Secret')
  .option('--email <email>', 'Email')
  .option('--roles <roles>', 'Roles. If multiple then use \"')
  .option('--sub <sub>', 'Id, user database id.')
  .action(function(action) {
    if (action === "token") {
      require('./src/token-maker').Main(program.secret, program.roles, program.email, program.sub);
    } else {
      console.log("no action")
    }
  })
  .parse(process.argv);
