localhost:3000/api/points/getPuntos?lat=-34.9055189&lng=-54.956316  con esto prueban en postman los getpuntos

localhost:3000/api/points/check?idUsuario=1&idPunto=4 con esto prueban hacer checks

query para scoreboard
select Id, nombre, count(IdPunto)  from checks inner join usuarios on usuarios.Id = checks.IdUsuario group by usuarios.id order by count(IdPunto) desc



CREATE TABLE public.checks
(
  idusuario integer NOT NULL,
  idpunto integer NOT NULL,
  CONSTRAINT checkusuario_primary_key PRIMARY KEY (idusuario, idpunto),
  CONSTRAINT checks_idpunto_fkey FOREIGN KEY (idpunto)
      REFERENCES public.points (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT checks_idusuario_fkey FOREIGN KEY (idusuario)
      REFERENCES public.usuarios (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);



CREATE TABLE public.usuarios
(
  id integer NOT NULL,
  nombre character varying(255) NOT NULL,
  CONSTRAINT usuario_primary_key PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

