DELETE FROM afiliado CASCADE;
DELETE FROM persona_antecedente_medico_collection CASCADE;
DELETE FROM plan_lista_precios CASCADE;
DELETE FROM antecedentemedico CASCADE;
DELETE FROM persona CASCADE;
DELETE FROM tipodocumento CASCADE;
DELETE FROM sangre CASCADE;
DELETE FROM afeccion CASCADE;
DELETE FROM periodopago CASCADE;
DELETE FROM comprobante_periodos_abonados CASCADE;
DELETE FROM plan CASCADE;
DELETE FROM comprobante CASCADE;
DELETE FROM visita CASCADE;
DELETE FROM enfermero CASCADE;
DELETE FROM medico CASCADE;
DELETE FROM obrasocial CASCADE;

/*--------------------DESCOMENTAR PARA CORRER SOBRE POSTGRESQL ------------------------------*/
/*ALTER SEQUENCE "afeccion_idafeccion_seq" RESTART WITH 1;
ALTER SEQUENCE "afiliado_idafiliado_seq" RESTART WITH 1;
ALTER SEQUENCE "antecedentemedico_idantecedentemedico_seq" RESTART WITH 1;
ALTER SEQUENCE "comprobante_idcomprobante_seq" RESTART WITH 1;
ALTER SEQUENCE "enfermero_idenfermero_seq" RESTART WITH 1;
ALTER SEQUENCE "medico_idmedico_seq" RESTART WITH 1;
ALTER SEQUENCE "obrasocial_idobrasocial_seq" RESTART WITH 1;
ALTER SEQUENCE "periodopago_idperiodopago_seq" RESTART WITH 1;
ALTER SEQUENCE "persona_idpersona_seq" RESTART WITH 1;
ALTER SEQUENCE "plan_idplan_seq" RESTART WITH 1;
ALTER SEQUENCE "sangre_idsangre_seq" RESTART WITH 1;
ALTER SEQUENCE "tipodocumento_idtipodocumento_seq" RESTART WITH 1;
ALTER SEQUENCE "visita_idvisita_seq" RESTART WITH 1;*/