package ar.com.koodi.sermeddata.IntegrationTest;

import Modelo.*;
import ar.com.koodi.sermeddata.ModeloData.AfeccionEntity;
import ar.com.koodi.sermeddata.ModeloData.AntecedenteMedicoEntity;
import ar.com.koodi.sermeddata.ModeloData.ObraSocialEntity;
import ar.com.koodi.sermeddata.ModeloData.PersonaEntity;
import ar.com.koodi.sermeddata.ModeloData.SangreEntity;
import ar.com.koodi.sermeddata.ModeloData.TipoDocumentoEntity;
import ar.com.koodi.sermeddata.RepositorioImplementacion.*;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:inicializar.sql")
})
public class PersonaRepositorioIntegrationTest {

    @Autowired
    PersonaRepositorioImplementacion personaRepositorioImplementacion;

    @Autowired
    TipoDocumentoRepositorioImplementacion tipoDocumentoRepositorioImplementacion;

    @Autowired
    SangreRepositorioImplementacion sangreRepositorioImplementacion;

    @Autowired
    ObraSocialRepositorioImplementacion obraSocialRepositorioImplementacion;

    @Autowired
    AfeccionRepositorioImplementacion afeccionRepositorioImplementacion;


    @Test
    public void personaPersist_SeGuardaCorrectamente_DevuelveTrue() {
        Persona persona = new Persona(null, "Perez", "Juan", LocalDate.of(2011, 9, 3), "julian amatte 21",
                new TipoDocumento(1, "DNI"), "12332123", new Sangre(1, "B", "RH+"), "423220",
                new ObraSocial(1, "pami"), "123", factoryAntecedentesMedicos(), 1);
        boolean resultado = personaRepositorioImplementacion.persist(persona);
        Persona personaBD= personaRepositorioImplementacion.findById(1);
        Assert.assertTrue(resultado);
        Assert.assertEquals(3, personaBD.getAntecedentesMedico().size());
    }

    @Test
    public void findById_ExisteId_DevuelvePersona(){
        factoryPersonas();
        Persona personaBuscada = personaRepositorioImplementacion.findById(1);
        Assert.assertNotNull(personaBuscada);
    }

    @Test
    public void findByDocumentoAndTipoDocumento_ExistePersona_DevuelvePersona(){
        factoryPersonas();
        Persona personaBuscada = personaRepositorioImplementacion.findByDocumentoAndTipoDocumento("12332123", "DNI");
        Assert.assertEquals("Juan", personaBuscada.getNombres());
        Assert.assertEquals("Perez", personaBuscada.getApellidos());
    }

    @Test
    public void findByDocumentoAndTipoDocumento_NoExistePersona_DevuelveNull(){
        factoryPersonas();
        Persona personaBuscada = personaRepositorioImplementacion.findByDocumentoAndTipoDocumento("12332123", "Libreta Civica");
        Assert.assertNull(personaBuscada);
    }

    @Test
    public void findAll_ExistenDatos_DevuelveListaDePersonas(){
        factoryPersonas();
        List<Persona> personas = (List<Persona>) personaRepositorioImplementacion.findAll();
        Assert.assertEquals(4, personas.size());
    }

    @Test
    public void findAll_NoExistenDatos_DevuelveListaVacia(){
        List<Persona> personas = (List<Persona>) personaRepositorioImplementacion.findAll();
        Assert.assertEquals(0, personas.size());
    }

    @Test
    public void findByApellido_ExisteApellido_DevuelveListaDePersonas(){
        factoryPersonas();
        List<Persona> persona = (List<Persona>) personaRepositorioImplementacion.findByApellido("perez");
        Assert.assertEquals(2, persona.size());
    }

    @Test
    public void findByApellido_NoExisteApellido_DevuelveListaVacia(){
        factoryPersonas();
        List<Persona> persona = (List<Persona>) personaRepositorioImplementacion.findByApellido("Ponzio");
        Assert.assertEquals(0, persona.size());
    }

    @Test
    public void findByNumeroAfiliado_ExisteNumero_DevuelvePersona(){
        factoryPersonas();
        Persona persona = personaRepositorioImplementacion.findByNumeroAfiliado("1234",2);
        Assert.assertEquals("Cosme", persona.getApellidos());
        Assert.assertEquals("Fulanito", persona.getNombres());
    }

    @Test
    public void findByNumeroAfiliado_NoExisteNumero_DevuelveNull(){
        factoryPersonas();
        Persona persona = personaRepositorioImplementacion.findByNumeroAfiliado("0000",1);
        Assert.assertNull(persona);
    }

    @Test
    public void update_ActualizaCorrectamente_DevuelveTrue(){
        factoryPersonas();
        Persona personaModificada = new Persona(2, "Tompson", "Homero", LocalDate.of(2012, 10, 4), "Av. Siempre Viva",
                new TipoDocumento(2, "Libreta Civica"), "987654321", new Sangre(2, "A", "RH-"), "423030",
                new ObraSocial(2, "OSDE"), "1234", factoryAntecedentesMedicos(), 2);
        boolean resultado = personaRepositorioImplementacion.update(personaModificada);
        Persona personaActualizada = personaRepositorioImplementacion.findById(2);
        Assert.assertTrue(resultado);
        Assert.assertEquals("Tompson", personaActualizada.getApellidos());
        Assert.assertEquals("Homero", personaActualizada.getNombres());
    }

    @Test
    public void mapeoDataCore_MapeaCorrectamente_PersonaConId(){
        PersonaEntity personaEntity = crearPersonaEntity();
        personaEntity.setIdPersona(1);       
        Persona persona = personaRepositorioImplementacion.mapeoDataCore(personaEntity);
        boolean atributosMapeados = persona.getApellidos().equals(personaEntity.getApellidos()) && persona.getNombres().equals(personaEntity.getNombres())
                && persona.getDocumento().equals(personaEntity.getDocumento()) && persona.getNroOrden() == personaEntity.getNroOrden().intValue()
                && persona.getNroAfiliado().equals(personaEntity.getNroAfiliado()) && persona.getDomicilio().equals(personaEntity.getDomicilio())
                && persona.getFechaNacimiento() == personaEntity.getFechaNacimiento() && persona.getSangre().getGrupo().equals(personaEntity.getSangre().getGrupo())
                && persona.getObraSocial().getNombre().equals(personaEntity.getObraSocial().getNombre()) && persona.getTelefono().equals(personaEntity.getTelefono());
        Assert.assertTrue(atributosMapeados);
        Assert.assertEquals(1, personaEntity.getIdPersona().intValue());
    }

    @Test
    public void mapeoCoreData_MapeaCorrectamente_PersonaSinId(){
        Persona persona = new Persona(null, "Perez", "Juan", LocalDate.of(2011, 9, 3), "julian amatte 21",
                new TipoDocumento(1, "DNI"), "12332123", new Sangre(1, "A", "RH+"), "423220",
                new ObraSocial(1, "OSFATUN"), "123", factoryAntecedentesMedicos(), 1);
        PersonaEntity personaEntity = personaRepositorioImplementacion.mapeoCoreData(persona);
        boolean atributosMapeados = persona.getApellidos().equals(personaEntity.getApellidos()) && persona.getNombres().equals(personaEntity.getNombres())
                && persona.getDocumento().equals(personaEntity.getDocumento()) && persona.getNroOrden() == personaEntity.getNroOrden().intValue()
                && persona.getNroAfiliado().equals(personaEntity.getNroAfiliado()) && persona.getDomicilio().equals(personaEntity.getDomicilio())
                && persona.getFechaNacimiento() == personaEntity.getFechaNacimiento() && persona.getSangre().getGrupo().equals(personaEntity.getSangre().getGrupo())
                && persona.getObraSocial().getNombre().equals(personaEntity.getObraSocial().getNombre()) && persona.getTelefono().equals(personaEntity.getTelefono());
        Assert.assertTrue(atributosMapeados);
        Assert.assertNull(personaEntity.getIdPersona());
    }

    private PersonaEntity crearPersonaEntity() {
        TipoDocumentoEntity tipoDocumentoEntity = new TipoDocumentoEntity("DNI");
        tipoDocumentoEntity.setIdTipoDocumento(1);
        SangreEntity sangreEntity = new SangreEntity("B", "RH-");
        sangreEntity.setIdSangre(4);
        ObraSocialEntity obraSocialEntity = new ObraSocialEntity("OSDE");
        obraSocialEntity.setIdObraSocial(2);
        return new PersonaEntity("Perez", "Juan", LocalDate.of(2011, 9, 3), "julian amatte 21",
                tipoDocumentoEntity, "12332123", sangreEntity, "423220",
                obraSocialEntity, "123", factoryAntecedentesMedicosEntity(), 1);
    }

    public Collection<AntecedenteMedico> factoryAntecedentesMedicos() {
        AntecedenteMedico dislexia = new AntecedenteMedico(null, new Afeccion(2, "Gripe"), "Cronica");
        AntecedenteMedico gonorrea = new AntecedenteMedico(null, new Afeccion(4, "Infección"), "Cronica Tambien");
        AntecedenteMedico diabetes = new AntecedenteMedico(null, new Afeccion(3, "Apendicitis"), "nerviosa");

        Collection<AntecedenteMedico> listaAntecedentes = Arrays.asList(dislexia, gonorrea, diabetes);
        System.out.println(listaAntecedentes.size());
        return listaAntecedentes;
    }
    
    public Collection<AntecedenteMedicoEntity> factoryAntecedentesMedicosEntity() {
    	AntecedenteMedicoEntity dislexia = new AntecedenteMedicoEntity(afeccionRepositorioImplementacion.mapeoCoreData(afeccionRepositorioImplementacion.findByNombreUnico("Gripe")), "");
    	AntecedenteMedicoEntity gonorrea = new AntecedenteMedicoEntity(afeccionRepositorioImplementacion.mapeoCoreData(afeccionRepositorioImplementacion.findByNombreUnico("Infección")), "Cronica");
    	AntecedenteMedicoEntity diabetes = new AntecedenteMedicoEntity(afeccionRepositorioImplementacion.mapeoCoreData(afeccionRepositorioImplementacion.findByNombreUnico("Apendicitis")), "");

        Collection<AntecedenteMedicoEntity> listaAntecedentes = Arrays.asList(dislexia, gonorrea, diabetes);

        return listaAntecedentes;
    }


    public void factoryPersonas(){
        try {
            personaRepositorioImplementacion.persist(new Persona(null, "Perez", "Juan", LocalDate.of(2011, 9, 3), "julian amatte 21",
                    new TipoDocumento(1, "DNI"), "12332123", new Sangre(1, "A", "RH+"), "423220",
                    new ObraSocial(1, "pami"), "123", factoryAntecedentesMedicos(), 1));
            personaRepositorioImplementacion.persist(new Persona(null, "Cosme", "Fulanito", LocalDate.of(2012, 10, 4), "julian amatte 210",
                    new TipoDocumento(2, "Libreta Civica"), "987654321", new Sangre(2, "A", "RH-"), "423030",
                    new ObraSocial(2, "OSDE"), "1234", factoryAntecedentesMedicos(), 2));
            personaRepositorioImplementacion.persist(new Persona(null, "Green", "Grass", LocalDate.of(2013, 11, 5), "San Martin 30",
                    new TipoDocumento(3, "Pasaporte"), "123789654", new Sangre(3, "B", "RH+"), "425050",
                    new ObraSocial(3, "APOS"), "12345", factoryAntecedentesMedicos(), 3));
            personaRepositorioImplementacion.persist(new Persona(null, "Perez", "Pablo", LocalDate.of(2010, 8, 2), "Julian amatte 21",
                    new TipoDocumento(1, "DNI"), "7539514268", new Sangre(4, "B", "RH-"), "467896",
                    new ObraSocial(3, "APOS"), "123456", factoryAntecedentesMedicos(), 4));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}