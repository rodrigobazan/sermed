DROP TABLE IF EXISTS persona;
DROP TABLE IF EXISTS afeccion;
DROP TABLE IF EXISTS afiliado;
DROP TABLE IF EXISTS antecedentemedico;
DROP TABLE IF EXISTS plan;
DROP TABLE IF EXISTS obrasocial;
DROP TABLE IF EXISTS sangre;
DROP TABLE IF EXISTS tipodocumento;
DROP TABLE IF EXISTS plan_lista_precios;

create table afeccion (
      idafeccion integer not null,
      nombreafeccion varchar(255),
      primary key (idafeccion)
);

create table afiliado (
  idafiliado integer not null,
  activo boolean,
  diadelmespagoacordado integer,
  fechaafiliacion date,
  fechadebaja date,
  numeroafiliado varchar(255),
  idplan integer,
  idpersona integer,
  primary key (idafiliado)
);

create table antecedentemedico (
       idantedecentemedico integer not null,
        observacion varchar(255),
        idafeccion integer,
        idpersona integer,
        primary key (idantedecentemedico)
);

create table obrasocial (
  idobrasocial integer not null,
  obrasocial varchar(255),
  primary key (idobrasocial)
);

create table persona (
       idpersona integer not null,
        apellidos varchar(255),
        documento varchar(255),
        domicilio varchar(255),
        fechanacimiento date,
        nombres varchar(255),
        nroafiliado varchar(255),
        nroorden integer,
        telefono varchar(255),
        idafiliado integer,
        obrasocial integer,
        sangre integer,
        tipodocumento integer,
        primary key (idpersona)
);

create table plan (
  idplan integer not null,
  nombreplan varchar(255),
  primary key (idplan)
);

create table plan_lista_precios (
  plan_idplan integer not null,
  listaprecios double,
  lista_precios_key varchar(255) not null,
  primary key (plan_idplan, lista_precios_key)
);

create table sangre (
       idsangre integer not null,
        factor varchar(255),
        grupo varchar(255),
        primary key (idsangre)
);

create table tipodocumento (
       idtipodocumento integer not null,
        nombre varchar(255),
        primary key (idtipodocumento)
);

alter table afiliado
       add constraint FKqlmkgvnlqposw5nffurhnfdnr
       foreign key (idplan)
       references plan;

alter table afiliado
       add constraint FKk860x7w4mb7u2to0rq3iyfmir
       foreign key (idpersona)
       references persona;

alter table antecedentemedico
       add constraint FKp00up5lkt8107opj38frwpkc9
       foreign key (idafeccion)
       references afeccion;

alter table antecedentemedico
       add constraint FKl9142jv1vqhwl1qdfi4gnvf4p
       foreign key (idpersona)
       references persona;

alter table persona
       add constraint FKwxx38wo44gv6w0wk4nsffucf
       foreign key (idafiliado)
       references afiliado;

alter table persona
       add constraint FKj3tdulaoo239ct2westihkot4
       foreign key (obrasocial)
       references obrasocial;

alter table persona
       add constraint FKojk841auaw3ge9yvjxlo0de0r
       foreign key (sangre)
       references sangre;

alter table persona
       add constraint FKqyqrxxoa76ce5ek72lk84qo2u
       foreign key (tipodocumento)
       references tipodocumento;

alter table plan_lista_precios
       add constraint FK2lyxvkqsbyubh0do6av94corn
       foreign key (plan_idplan)
       references plan;


/*Inserts ---------*/
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

/*Insert plan */
INSERT INTO plan values(1, 'Plan Basico');

/*Insert lista precios plan*/
INSERT INTO plan_lista_precios values (1,400,'1');
INSERT INTO plan_lista_precios values (1,480,'2');
INSERT INTO plan_lista_precios values (1,550,'3');
INSERT INTO plan_lista_precios values (1,600,'4');
INSERT INTO plan_lista_precios values (1,650,'5');
INSERT INTO plan_lista_precios values (1,700,'6');
INSERT INTO plan_lista_precios values (1,750,'7');

/*Insert persona*/
INSERT INTO persona values (1, 'Tompson', '1234567', 'Julian Amatte 21', '2010-09-21', 'Homero', null, null, '2020', null, 1, 1, 1);
;


