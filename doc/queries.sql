SELECT ST_AsGeoJSON(location) FROM points as p
  WHERE ST_DWithin(
    p.location,
    ST_GeomFromText('POINT(-71.060316 48.432044)', 4326),
    100
  ) = true;

  

  