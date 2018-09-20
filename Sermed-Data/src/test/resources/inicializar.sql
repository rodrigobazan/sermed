DROP TABLE IF EXISTS tipodocumento;
DROP TABLE IF EXISTS sangre;
DROP TABLE IF EXISTS afeccion;
DROP TABLE IF EXISTS obrasocial;

CREATE TABLE tipodocumento (
    idtipodocumento integer,
    nombre varchar(255)
);

CREATE TABLE sangre (
  idsangre integer,
  grupo varchar(255),
  factor varchar(255)
);

CREATE TABLE afeccion(
  idafeccion integer,
  nombreafeccion varchar(255)
);

CREATE TABLE obrasocial(
  idobrasocial integer,
  obrasocial varchar(255)
);

/*Insert de Tipo Documento*/
INSERT INTO tipodocumento values(1, 'DNI');
INSERT INTO tipodocumento values(2, 'Libreta Civica');
INSERT INTO tipodocumento values(3, 'Pasaporte');
INSERT INTO tipodocumento values(4, 'Libreta de Enrolamiento');

/*Insert de Sangre*/
INSERT INTO sangre values(1, 'A', 'RH+');
INSERT INTO sangre values(2, 'A', 'RH-');
INSERT INTO sangre values(3, 'B', 'RH+');
INSERT INTO sangre values(4, 'B', 'RH-');
INSERT INTO sangre values(5, '0', 'RH+');
INSERT INTO sangre values(6, '0', 'RH-');
INSERT INTO sangre values(7, 'AB', 'RH+');
INSERT INTO sangre values(8, 'AB', 'RH-');

/*Insert de Afecciones*/
INSERT INTO afeccion values(1, 'Migraña Cronica');
INSERT INTO afeccion values(2, 'Gripe');
INSERT INTO afeccion values(3, 'Apendicitis');
INSERT INTO afeccion values(4, 'Infección');
INSERT INTO afeccion values(5, 'Infección Generalizada');

/*Insert de obra social*/
INSERT INTO obrasocial values (1, 'OSFATUN');
INSERT INTO obrasocial values (2, 'OSDE');
INSERT INTO obrasocial values (3, 'APOS');
INSERT INTO obrasocial values (4, 'PAMI');
INSERT INTO obrasocial values (5, 'SANCOR SALUD');