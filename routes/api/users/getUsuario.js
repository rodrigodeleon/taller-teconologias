var express = require('express');
var pg = require("pg");

var pgClient = require('../../../utils/database_connection');
var router = express.Router();


router.get('/', function (req, res, next) {
   
    var client = pgClient.connect();
    var queryString = 'SELECT * from usuarios WHERE Nombre = $1;';
    
    var query = client.query(queryString, [req.query.nombre]);

     query.on('row', function(row,result)
     {
        var a = 
        {
             "usuario": row
        }
        result.addRow(a);

      });
    query.on('end', function (result) 
    {
     client.end();
     res.send(
          result.rows       
       );
    });  
  });

module.exports = router;

