var express = require('express');
var pg = require("pg");

var pgClient = require('../../../utils/database_connection');
var router = express.Router();


router.post('/', function (req, res, next) {
   
    var client = pgClient.connect();
    var queryString = 'INSERT INTO checks(idUsuario,idPunto) VALUES($1, $2); ';

    var query = client.query(queryString, [req.query.idUsuario, req.query.idPunto]);

    query.on('end', function () 
    {
     client.end();
     res.send(
          "ok"
       );
    });  
  });

module.exports = router;

