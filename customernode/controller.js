var request = require('request');
var async = require('async');
var express = require('express');
const browser = require('./clientService.js');

var app = express();
app.get('/getById',function(req,res){
  browser.handler(req,res);
});
app.listen(8090,function(){});
