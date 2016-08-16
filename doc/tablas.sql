

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


CREATE TABLE public.usuarios
(
  id integer NOT NULL,
  nombre character varying(255) NOT NULL,
  CONSTRAINT usuario_primary_key PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

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

