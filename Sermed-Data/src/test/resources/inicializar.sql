DROP TABLE IF EXISTS tipodocumento;
DROP TABLE IF EXISTS sangre;
DROP TABLE IF EXISTS afeccion;
DROP TABLE IF EXISTS obrasocial;
DROP TABLE IF EXISTS periodopago;

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

CREATE TABLE periodopago(
  idperiodopago integer,
  mes integer,
  anio integer
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

/*Insert de periodos de pago*/
INSERT INTO periodopago values (1, 1, 2018);
INSERT INTO periodopago values (2, 2, 2018);
INSERT INTO periodopago values (3, 3, 2018);
INSERT INTO periodopago values (4, 4, 2018);
INSERT INTO periodopago values (5, 5, 2018);
INSERT INTO periodopago values (6, 6, 2018);
INSERT INTO periodopago values (7, 7, 2018);
INSERT INTO periodopago values (8, 8, 2018);
INSERT INTO periodopago values (9, 9, 2018);
INSERT INTO periodopago values (10, 10, 2018);
INSERT INTO periodopago values (11, 11, 2018);
INSERT INTO periodopago values (12, 12, 2018);
INSERT INTO periodopago values (13, 1, 2017);
INSERT INTO periodopago values (14, 2, 2017);
INSERT INTO periodopago values (15, 3, 2017);
