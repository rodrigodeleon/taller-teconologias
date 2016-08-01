var express = require('express');
var pg = require("pg");

var pgClient = require('../../../utils/database_connection');
var router = express.Router();

function postGISQueryToFeatureCollection(queryResult) {
  // Initalise variables.
  var i = 0,
      length = queryResult.length,
      prop = null,
      geojson = {
        "type": "FeatureCollection",
        "features": []
      };    // Set up the initial GeoJSON object.

  for(i = 0; i < length; i++) {  // For each result create a feature
    var feature = {
      "type": "Feature",
      "geometry": JSON.parse(queryResult[i].geojson)
    };
    // finally for each property/extra field, add it to the feature as properties as defined in the GeoJSON spec.
    for(prop in queryResult[i]) {
      if (prop !== "geojson" && queryResult[i].hasOwnProperty(prop)) {
        feature[prop] = queryResult[i][prop];
      }
    }
    // Push the feature into the features array in the geojson object.
    geojson.features.push(feature);
  }
  // return the FeatureCollection geojson object.
  return geojson;
}

router.post('/', function (req, res, next) {
   // console.log(req, res, next);
    var client = pgClient.connect();
    var query = client.query("SELECT ST_AsGeoJSON(location) FROM points as p WHERE ST_DWithin(p.location,ST_GeomFromText('POINT(-71.060316 48.432044)', 4326),100) = true;");
      
      query.on('row', function(row,result) {
        console.log(row);
        result.addRow(row);
        
      });
    query.on('end', function (result) {
   //console.log(arguments);
     client.end();
     res.send(
          result       
       );
    });
  });




module.exports = router;

