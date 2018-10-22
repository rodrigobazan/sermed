DROP TABLE IF EXISTS afeccion CASCADE;
DROP TABLE IF EXISTS afiliado CASCADE;
DROP TABLE IF EXISTS antecedentemedico CASCADE;
DROP TABLE IF EXISTS afiliado_miembros CASCADE;
DROP TABLE IF EXISTS plan CASCADE;
DROP TABLE IF EXISTS obrasocial CASCADE;
DROP TABLE IF EXISTS sangre CASCADE;
DROP TABLE IF EXISTS tipodocumento CASCADE;
DROP TABLE IF EXISTS plan_lista_precios CASCADE;
DROP TABLE IF EXISTS persona CASCADE;
DROP TABLE IF EXISTS persona_antecedente_medico_collection CASCADE;
DROP TABLE IF EXISTS periodopago CASCADE;

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


create table afiliado_miembros (
       afiliado_idafiliado integer not null,
        miembros_idpersona integer not null
);

create table antecedentemedico (
       idantedecentemedico integer not null,
        observacion varchar(255),
        idafeccion integer,
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
        obrasocial integer,
        sangre integer,
        tipodocumento integer,
        primary key (idpersona)
);

create table persona_antecedente_medico_collection (
       persona_idpersona integer not null,
        antecedente_medico_collection_idantedecentemedico integer not null
    );

create table plan (
       idplan integer not null,
        nombreplan varchar(255),
        primary key (idplan)
);

create table plan_lista_precios (
       plan_idplan integer not null,
        listaprecios decimal,
        lista_precios_key varchar(255) not null,
        primary key (plan_idplan, lista_precios_key)
);

create table sangre (
       idsangre integer not null,
       grupo varchar(255),
        factor varchar(255),
        primary key (idsangre)
);

create table tipodocumento (
       idtipodocumento integer not null,
        nombre varchar(255),
        primary key (idtipodocumento)
);

CREATE TABLE periodopago(
  idperiodopago integer,
  mes integer,
  anio integer
);

alter table afiliado_miembros
       add constraint UK_rkpgp9at8w04m76coqdc4ntq4 unique (miembros_idpersona);

alter table persona_antecedente_medico_collection
       add constraint UK_mmluino1qnlora1g3tmwn1mhw unique (antecedente_medico_collection_idantedecentemedico);

alter table afiliado
       add constraint FKqlmkgvnlqposw5nffurhnfdnr
       foreign key (idplan)
       references plan;

alter table afiliado
       add constraint FKk860x7w4mb7u2to0rq3iyfmir
       foreign key (idpersona)
       references persona;

alter table afiliado_miembros
       add constraint FK2521iifrwajq04bygv92l9qak
       foreign key (miembros_idpersona)
       references persona;

alter table afiliado_miembros
       add constraint FKrxasd9iw0v74msljaun7ocxrf
       foreign key (afiliado_idafiliado)
       references afiliado;

alter table antecedentemedico
       add constraint FKp00up5lkt8107opj38frwpkc9
       foreign key (idafeccion)
       references afeccion;

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


alter table persona_antecedente_medico_collection
       add constraint FKcfxlfh1gqjex7cic21h8dew2u
       foreign key (antecedente_medico_collection_idantedecentemedico)
       references antecedentemedico;


alter table persona_antecedente_medico_collection
       add constraint FKfnj2lqm09pb1hwwo0afejhlyc
       foreign key (persona_idpersona)
       references persona;

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
INSERT INTO plan values(2, 'Plan Basico Nuevo');

/*Insert lista precios plan*/
INSERT INTO plan_lista_precios values (1,400,'1');
INSERT INTO plan_lista_precios values (1,480,'2');
INSERT INTO plan_lista_precios values (1,550,'3');
INSERT INTO plan_lista_precios values (1,600,'4');
INSERT INTO plan_lista_precios values (1,650,'5');
INSERT INTO plan_lista_precios values (1,700,'6');
INSERT INTO plan_lista_precios values (1,750,'7');

INSERT INTO plan_lista_precios values (2,405,'1');
INSERT INTO plan_lista_precios values (2,485,'2');
INSERT INTO plan_lista_precios values (2,555,'3');
INSERT INTO plan_lista_precios values (2,605,'4');
INSERT INTO plan_lista_precios values (2,655,'5');
INSERT INTO plan_lista_precios values (2,705,'6');
INSERT INTO plan_lista_precios values (2,755,'7');

/*Insert persona*/
INSERT INTO persona values (nextval('persona_idpersona_seq'),'Tompson', '1234567', 'Julian Amatte 21', '2010-09-21', 'Homero', '190000', 0, '2020', 1, 1, 1);
INSERT INTO persona values (nextval('persona_idpersona_seq'),'Brown', '7654321', '9 de julio 456', '2011-09-21', 'Charly', '150000', 0, '2120', 1, 1, 1);
INSERT INTO persona values (nextval('persona_idpersona_seq'),'Gomez', '7539516', '25 de mayo 1000', '2011-09-21', 'Droopy', '130000', 0, '5050', 1, 1, 1);

/*Insert Afiliado*/
INSERT INTO afiliado values (nextval('afiliado_idafiliado_seq'), true, 15, '2018-05-02', null, '190001', 1, 1);
INSERT INTO afiliado values (nextval('afiliado_idafiliado_seq'), false, 15, '2018-05-02', null, '150000', 1, 2);
INSERT INTO afiliado values (nextval('afiliado_idafiliado_seq'), false, 15, '2018-05-02', null, '130000', 1, 3);

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