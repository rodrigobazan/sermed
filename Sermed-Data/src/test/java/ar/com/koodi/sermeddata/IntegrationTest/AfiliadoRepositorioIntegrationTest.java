package ar.com.koodi.sermeddata.IntegrationTest;

import java.time.LocalDate;
import java.util.*;

import Excepciones.AfiliadoSinPlanException;
import Excepciones.AfiliadoSinTitularException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Modelo.*;
import ar.com.koodi.sermeddata.ModeloData.*;
import ar.com.koodi.sermeddata.RepositorioImplementacion.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import Excepciones.PlanIncompletoException;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:inicializarAfiliado.sql")
})
public class AfiliadoRepositorioIntegrationTest {

	@Autowired
	AfiliadoRepositorioImplementacion afiliadoRepositorioImplementacion;

	@Autowired
	PersonaRepositorioImplementacion personaRepositorioImplementacion;

	@Autowired
	PlanRepositorioImplementacion planRepositorioImplementacion;

	@Autowired
	SangreRepositorioImplementacion sangreRepositorioImplementacion;

	@Autowired
	TipoDocumentoRepositorioImplementacion tipoDocumentoRepositorioImplementacion;

	@Autowired
	ObraSocialRepositorioImplementacion obraSocialRepositorioImplementacion;

	@Test
	public void persistAfiliado_TitularNoExisteSeAlmacenaCorrectamente_DevuelveTrue() throws AfiliadoSinTitularException, NumeroAfiliadoIncorrectoException, AfiliadoSinPlanException {
		Afiliado afiliado = Afiliado.instancia(1,LocalDate.of(2010,9,9), "190000",
				factoryPersonaNuevaEnLaBD(), new ArrayList<>(), true, null, 15, factoryPlan());
		boolean resultado = afiliadoRepositorioImplementacion.persist(afiliado);
		Assert.assertTrue(resultado);
	}
	
	@Test
	public void persistAfiliado_TitularExisteSeAlmacenaCorrectamente_DevuelveTrue() throws AfiliadoSinTitularException, NumeroAfiliadoIncorrectoException, AfiliadoSinPlanException {
		Persona titular = factoryPersonaTitularExistenteEnBD();
		Afiliado afiliado = Afiliado.instancia(1,LocalDate.of(2010,9,9), "190000",
				titular, new ArrayList<>(), true, null, 15, factoryPlan());
		boolean resultado = afiliadoRepositorioImplementacion.persist(afiliado);
		Assert.assertTrue(resultado);
	}

	@Test
	public void findById_ExisteId_DevuelveAfiliado(){
		Afiliado afiliado = afiliadoRepositorioImplementacion.findById(1);
		Assert.assertEquals("190001", afiliado.getNumeroAfiliado());
	}

	@Test
	public void findAll_ExistenDatos_DevuelveListaAfiliados(){
		List<Afiliado> afiliados = new ArrayList<>();
		afiliados = (List<Afiliado>) afiliadoRepositorioImplementacion.findAll();
		Assert.assertEquals(3, afiliados.size());
	}

	@Test
	@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:limpiarbase.sql")
	public void findAll_NoExistenDatos_DevuelveListaAfiliados(){
		List<Afiliado> afiliados = new ArrayList<>();
		afiliados = (List<Afiliado>) afiliadoRepositorioImplementacion.findAll();
		Assert.assertEquals(0, afiliados.size());
	}

	@Test
	public void findByNumero_ExistenAfiliadon_DevuelveListaAfiliados(){
		List<Afiliado> afiliados = new ArrayList<>();
		afiliados = (List<Afiliado>) afiliadoRepositorioImplementacion.findByNumero("190");
		Assert.assertEquals(1, afiliados.size());

	}

	@Test
	public void findByNumero_NoExistenAfiliadon_DevuelveListaVacia(){
		List<Afiliado> afiliados = new ArrayList<>();
		afiliados = (List<Afiliado>) afiliadoRepositorioImplementacion.findByNumero("999");
		Assert.assertEquals(0, afiliados.size());
	}

	@Test
	public void findByNumeroUnico_ExisteNumeroAfiliado_DevuelveAfiliado(){
		Afiliado afiliado = afiliadoRepositorioImplementacion.findUnicoByNumero("190001");
		Assert.assertEquals("Homero", afiliado.getTitular().getNombres());
	}

	@Test
	public void findByNumeroUnico_NoExisteNumeroAfiliado_DevuelveNull(){
		Afiliado afiliado = afiliadoRepositorioImplementacion.findUnicoByNumero("170000");
		Assert.assertNull(afiliado);
	}

	@Test
	public void findAllActivos_ExistenAfiliados_DevuelveListaAfiliadosActivos(){
		List<Afiliado> afiliados = new ArrayList<>();
		afiliados = (List<Afiliado>) afiliadoRepositorioImplementacion.findAllActivos();
		Assert.assertEquals(1,afiliados.size());
	}

	@Test
	public void findAllInactivos_ExistenAfiliados_DevuelveListaAfiliadosInactivos(){
		List<Afiliado> afiliados = new ArrayList<>();
		afiliados = (List<Afiliado>) afiliadoRepositorioImplementacion.findAllInactivos();
		Assert.assertEquals(2,afiliados.size());
	}

	@Test
	public void update_PersonaNuevaPlanExistenteEnBD_ActualizaTitularYPlan(){
		Persona nuevoTitular = new Persona(null, "Morales", "Cachete", LocalDate.of(2000,5,5), "La plata 500",
				new TipoDocumento(2, "Libreta Civica"), "37415281", new Sangre(2, "A", "RH-"), "423030",
				new ObraSocial(2, "OSDE"), "1234", new ArrayList<>(), 2);
		Afiliado afiliado = afiliadoRepositorioImplementacion.findById(3);
		afiliado.asignarTitular(nuevoTitular);
		afiliado.cambiarPlan(factoryPlanModificado());
		boolean resultado = afiliadoRepositorioImplementacion.update(afiliado);
		Afiliado afiliadoModificado = afiliadoRepositorioImplementacion.findById(3);
		Assert.assertTrue(resultado);
		Assert.assertEquals("Morales", afiliadoModificado.getTitular().getApellidos());
		Assert.assertEquals("Plan Basico Nuevo", afiliadoModificado.getPlan().getNombre());
	}

	@Test
	public void update_PersonaExistenteEnBDPlanExistenteEnBD_ActualizaTitularYPlan(){
		Persona nuevoTitular = new Persona(2, "Brown", "Charly", LocalDate.of(2011,9,21), "9 de julio 456",
				new TipoDocumento(2, "Libreta Civica"), "7654321", new Sangre(2, "A", "RH-"), "2120",
				new ObraSocial(2, "OSDE"), "150000", new ArrayList<>(), 0);
		Afiliado afiliado = afiliadoRepositorioImplementacion.findById(3);
		afiliado.asignarTitular(nuevoTitular);
		afiliado.cambiarPlan(factoryPlanModificado());
		boolean resultado = afiliadoRepositorioImplementacion.update(afiliado);
		Afiliado afiliadoModificado = afiliadoRepositorioImplementacion.findById(3);
		Assert.assertTrue(resultado);
		Assert.assertEquals("Brown", afiliadoModificado.getTitular().getApellidos());
		Assert.assertEquals("Plan Basico Nuevo", afiliadoModificado.getPlan().getNombre());
	}

	@Test
	public void mapeoDataCore_MapeaCorrectamente_AfiliadoConId(){
		AfiliadoEntity afiliadoEntity = new AfiliadoEntity(LocalDate.of(2000, 5, 5), "140000", new ArrayList<>(), factoryPersonaEntity(),
				true, null, 15, factoryPlanEntity());
		afiliadoEntity.setIdAfiliado(1);
		Afiliado afiliado = afiliadoRepositorioImplementacion.mapeoDataCore(afiliadoEntity);
		boolean atributosMapeados = afiliado.getActivo() == afiliadoEntity.isActivo() && afiliado.getMiembros().size() == afiliadoEntity.getMiembros().size() &&
				afiliado.getTitular().getNroAfiliado().equals(afiliadoEntity.getTitular().getNroAfiliado()) && afiliado.getFechaAfiliacion() == afiliadoEntity.getFechaAfiliacion()
				&& afiliado.getFechaDeBaja() == afiliadoEntity.getFechaDeBaja();
		Assert.assertTrue(atributosMapeados);
		Assert.assertEquals(1, afiliadoEntity.getIdAfiliado().intValue());
	}

	@Test
	public void mapeoCoreData_MapeaCorrectamente_AfiliadoSinId() throws AfiliadoSinTitularException, NumeroAfiliadoIncorrectoException, AfiliadoSinPlanException {
		Afiliado afiliado = Afiliado.instancia(null, LocalDate.of(2015,5,5),"140000", factoryPersonaNuevaEnLaBD(), new ArrayList<>(), true,
				null, 15, factoryPlan());
		AfiliadoEntity afiliadoEntity = afiliadoRepositorioImplementacion.mapeoCoreData(afiliado);
		boolean atributosMapeados = afiliado.getActivo() == afiliadoEntity.isActivo() && afiliado.getMiembros().size() == afiliadoEntity.getMiembros().size() &&
				afiliado.getTitular().getNroAfiliado().equals(afiliadoEntity.getTitular().getNroAfiliado()) && afiliado.getFechaAfiliacion() == afiliadoEntity.getFechaAfiliacion()
				&& afiliado.getFechaDeBaja() == afiliadoEntity.getFechaDeBaja();
		Assert.assertTrue(atributosMapeados);
		Assert.assertNull(afiliadoEntity.getIdAfiliado());
	}

	private PersonaEntity factoryPersonaEntity() {
		return new PersonaEntity("Tompson", "Homero", LocalDate.of(1993, 5 ,31), "25 de mayo 503",factoryTipoDocumentoEntity(),
				"37415281", factorySangreEntity(), "3825674678", factoryObraSocialEntity(), "140000", new ArrayList<>(),0);
	}

	private PlanEntity factoryPlanEntity() {
		return new PlanEntity("Plan Basico",factoryListaPrecios());
	}

	private TipoDocumentoEntity factoryTipoDocumentoEntity() {
		return tipoDocumentoRepositorioImplementacion.mapeoCoreData(tipoDocumentoRepositorioImplementacion.findByNombreUnico("DNI"));
	}

	private SangreEntity factorySangreEntity() {
		return sangreRepositorioImplementacion.mapeoCoreData(sangreRepositorioImplementacion.findByGrupoFactor("A", "RH+"));
	}

	private ObraSocialEntity factoryObraSocialEntity() {
		return obraSocialRepositorioImplementacion.mapeoCoreData(obraSocialRepositorioImplementacion.findByNombreUnico("OSDE"));
	}

	private Persona factoryPersonaNuevaEnLaBD(){
		return new Persona(null, "Perez", "Juan", LocalDate.of(2011, 9, 3), "julian amatte 21",
				new TipoDocumento(1, "DNI"), "12332123", new Sangre(1, "A", "RH+"), "423220",
				new ObraSocial(1, "OSFATUN"), "180000", new ArrayList<>(), 0);
	}


	private Persona factoryPersonaTitularExistenteEnBD() {
		return new Persona(1, "Tompson", "Homero", LocalDate.of(2010, 9, 21), "Julian Amatte 21",
				new TipoDocumento(1, "DNI"), "1234567", new Sangre(1, "A", "RH+"), "2020",
				new ObraSocial(1, "OSFATUN"), "190000", new ArrayList<>(), 0);
	}

	private Plan factoryPlan(){
		return planRepositorioImplementacion.findById(1);
	}
	private Plan factoryPlanModificado(){
		return planRepositorioImplementacion.findById(2);
	}

	private HashMap<String, Double> factoryListaPrecios() {
		try {
			HashMap<String, Double> listaPrecios = new HashMap<>();
			listaPrecios.put("1", (double) 500);
			listaPrecios.put("2", (double) 580);
			listaPrecios.put("3", (double) 650);
			listaPrecios.put("4", (double) 700);
			listaPrecios.put("5", (double) 850);
			listaPrecios.put("6", (double) 900);
			listaPrecios.put("7", (double) 1050);
			return listaPrecios;
		} catch (Exception e) {
			e.printStackTrace();
			return new HashMap<>();
		}
	}

}


