var express = require('express');
var pg = require("pg");

var pgClient = require('../../../utils/database_connection');
var router = express.Router();


router.get('/', function (req, res, next) {
   
    var client = pgClient.connect();
    var queryString = 'SELECT id, description, ST_AsGeoJSON(location) AS location '+
    'FROM points  as p WHERE ST_DWithin(p.location,'+ 
    'Geography(ST_MakePoint($1 , $2)), '+
    '100) and id not in (select idPunto from checks where idUsuario = $3);';

    


    var query = client.query(queryString, [req.query.lat, req.query.lng, req.query.usr]);

     query.on('row', function(row,result)
     {
        console.log(row);
        var point = row;
        point.location = JSON.parse(point['location']);
        var a = 
        {
             "coordenadas": {
                 id: point.id,
                 descripcion : point.description,
                 lat: point.location.coordinates[0],
                 lng: point.location.coordinates[1] 
                      }
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

