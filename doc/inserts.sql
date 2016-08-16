
INSERT INTO usuarios(id, nombre) VALUES(1,'rodrigo');
INSERT INTO usuarios(id, nombre) VALUES(2,'franco');
INSERT INTO usuarios(id, nombre) VALUES(3,'mauricio');


INSERT INTO points(id, description, location) VALUES(1, 'primero', ST_GeomFromText('POINT(-34.9055189 -54.956316)', 4326));
INSERT INTO points(id, description, location) VALUES(2, 'segundo', ST_GeomFromText('POINT(-34.9055182 -54.9559879)', 4326));
INSERT INTO points(id, description, location) VALUES(3, 'tercero', ST_GeomFromText('POINT(-34.9053963 -54.9555349)', 4326));
INSERT INTO points(id, description, location) VALUES(4, 'cuarto', ST_GeomFromText('POINT(-34.9059112 -54.9587966)', 4326));
INSERT INTO points(id, description, location) VALUES(5, 'quinto', ST_GeomFromText('POINT(-34.9055177 -54.9557411)', 4326));
INSERT INTO points(id, description, location) VALUES(6, 'sexto', ST_GeomFromText('POINT(-34.905524 -54.956747)', 4326));
INSERT INTO points(id, description, location) VALUES(7, 'septimo', ST_GeomFromText('POINT(-34.905406 -54.956640)', 4326));
INSERT INTO points(id, description, location) VALUES(8, 'octavo', ST_GeomFromText('POINT(-34.905635 -54.956640)', 4326));
INSERT INTO points(id, description, location) VALUES(9, 'noveno', ST_GeomFromText('POINT(--34.904922 -54.956629)', 4326));
INSERT INTO points(id, description, location) VALUES(10, 'decimo', ST_GeomFromText('POINT(-34.905089 -54.956103)', 4326));
