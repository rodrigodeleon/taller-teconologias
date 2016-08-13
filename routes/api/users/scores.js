var express = require('express');
var pg = require("pg");

var pgClient = require('../../../utils/database_connection');
var router = express.Router();


router.get('/', function (req, res, next) {
   
    var client = pgClient.connect();
    var queryString = 'select Id, nombre, count(IdPunto)  from checks inner join usuarios on usuarios.Id = checks.IdUsuario group by usuarios.id order by count(IdPunto) desc'
    var query = client.query(queryString);

     query.on('row', function(row,result)
     {
        console.log(row);
       /* var point = row;
        point.location = JSON.parse(point['location']);
        var a = 
        {
             "coordenadas": {
                 descripcion : point.description,
                 lat: point.location.coordinates[0],
                 lng: point.location.coordinates[1] 
                      }
        }*/
        result.addRow(row);

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

