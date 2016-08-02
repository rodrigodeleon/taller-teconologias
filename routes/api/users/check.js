var express = require('express');
var pg = require("pg");

var pgClient = require('../../../utils/database_connection');
var router = express.Router();


router.post('/', function (req, res, next) {
   
    var client = pgClient.connect();
    var queryString = ("SELECT ST_AsGeoJSON(location) FROM points as p WHERE ST_DWithin(p.location,ST_GeomFromText('POINT(-71.060316 48.432044)', 4326),100) = true;");
    var query = client.query(queryString , [req.query.lng, req.query.lat]);
      
      


    query.on('row', function(row,result)
     {
        console.log(row);
        var point = row;
        point.location = JSON.parse(point['st_asgeojson']);
        var a = 
        {
             "coordinates": {
                 lat: point.location.coordinates[0],
                 lng: point.location.coordinates[1] 
                      }
        }

        
        result.addRow(a );
        
        
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

