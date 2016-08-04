CREATE EXTENSION postgis;
CREATE EXTENSION fuzzystrmatch;

CREATE TABLE public.points
(
  id integer NOT NULL,
  description character varying(255) NOT NULL,
  location GEOGRAPHY(POINT,4326),
  CONSTRAINT points_primary_key PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

INSERT INTO points(id, description, location) VALUES(1, 'llllll', ST_GeomFromText('POINT(-71.060316 48.432044)', 4326));
commit;

INSERT INTO points(id, description, location) VALUES(4, 'primero', ST_GeomFromText('POINT(-34.9055189 -54.956316)', 4326));
INSERT INTO points(id, description, location) VALUES(5, 'segundo', ST_GeomFromText('POINT(-34.9055182 -54.9559879)', 4326));
INSERT INTO points(id, description, location) VALUES(6, 'tercero', ST_GeomFromText('POINT(-34.9053963 -54.9555349)', 4326));
INSERT INTO points(id, description, location) VALUES(7, 'cuarto', ST_GeomFromText('POINT(-34.9059112 -54.9587966)', 4326));
INSERT INTO points(id, description, location) VALUES(8, 'quinto', ST_GeomFromText('POINT(-34.9055177 -54.9557411)', 4326));
