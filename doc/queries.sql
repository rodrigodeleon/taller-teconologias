SELECT ST_AsGeoJSON(location) FROM points as p
  WHERE ST_DWithin(
    p.location,
    ST_GeomFromText('POINT(-34.9055189 -54.956316)', 4326),
    100
  ) = true;

  

  