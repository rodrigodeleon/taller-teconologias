var express = require('express');
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
    var query = client.query('SELECT * FROM points as p WHERE ST_DWithin(p.location,Geography(ST_MakePoint(-72.060316, 48.432044)),100)');
    var geojson = postGISQueryToFeatureCollection(query);
    
      query.on('row', function(row) {
        console.log(row);
      });
    query.on('end', function () {
   //console.log(arguments);
     client.end();
     res.send(
       geojson
       );
    });
  });




module.exports = router;

