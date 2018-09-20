package ar.com.koodi.sermeddata.RepositorioImplementacion;

import Modelo.*;
import Repositorio.IPersonaRepositorio;
import ar.com.koodi.sermeddata.ModeloData.*;
import ar.com.koodi.sermeddata.RepositorioData.IPersonaRepositorioCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PersonaRepositorioImplementacion implements IPersonaRepositorio {

	@Autowired
	IPersonaRepositorioCRUD iPersonaRepositorioCRUD;

	@Autowired
	TipoDocumentoRepositorioImplementacion tipoDocumentoRepositorioImplementacion;

	@Autowired
	SangreRepositorioImplementacion sangreRepositorioImplementacion;

	@Autowired
	ObraSocialRepositorioImplementacion obraSocialRepositorioImplementacion;

	@Autowired
    AfeccionRepositorioImplementacion afeccionRepositorioImplementacion;

	@Override
	@Transactional
	public boolean persist(Persona persona) {
		return this.iPersonaRepositorioCRUD.save(mapeoCoreData(persona)) != null;
	}

	@Override
	@Transactional(readOnly = true)
	public Persona findById(int idPersona) {
		return mapeoDataCore(this.iPersonaRepositorioCRUD.findByIdPersona(idPersona));
	}

	@Override
	@Transactional(readOnly = true)
	public Persona findByDocumentoAndTipoDocumento(String documento, String tipoDocumento) {
		// return
		// maperoDataCore(this.iPersonaRepositorioCRUD.findByDocumentoandAndTipoDocumento(documento,tipoDocumento));
		return null;
	}

	@Override
	@Transactional
	public boolean update(Persona personaDatosNuevos) {
		PersonaEntity personaEntity = mapeoCoreData(personaDatosNuevos);
		personaEntity.setIdPersona(personaDatosNuevos.getIdPersona());
		return iPersonaRepositorioCRUD.save(personaEntity) != null;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Persona> findAll() {
		List<Persona> personaEntities = new ArrayList<>();
		this.iPersonaRepositorioCRUD.findAll().forEach(e -> personaEntities.add(mapeoDataCore(e)));
		return personaEntities;
	}

	@Override
	@Transactional
	public Collection<Persona> findByApellido(String apellido) {
		List<Persona> personas = new ArrayList<>();
		this.iPersonaRepositorioCRUD.findByApellidosContains(apellido).forEach(e -> personas.add(mapeoDataCore(e)));
		return personas;
	}

	@Override
	public Persona findByNumeroAfiliado(String numeroAfiliado, Integer orden) {
		return null;
	}

	public PersonaEntity mapeoCoreData(Persona persona) {
		TipoDocumentoEntity tipoDocumento = tipoDocumentoRepositorioImplementacion.mapeoCoreData(persona.getTipoDocumento());
		SangreEntity sangre = sangreRepositorioImplementacion.mapeoCoreData(persona.getSangre());
		ObraSocialEntity obraSocial = obraSocialRepositorioImplementacion.mapeoCoreData(persona.getObraSocial());
		Collection<AntecedenteMedicoEntity> antecedentesMedicos = antecedentesModelo_AntecedentesEntity(persona.getAntecedentesMedico());
		return new PersonaEntity(persona.getApellidos(), persona.getNombres(), persona.getFechaNacimiento(),
				persona.getDomicilio(), tipoDocumento, persona.getDocumento(), sangre, persona.getTelefono(),
				obraSocial, persona.getNroAfiliado(), antecedentesMedicos, persona.getNroOrden());
	}

	public Persona mapeoDataCore(PersonaEntity personaEntity) {
		Collection<AntecedenteMedico> antecedentes = antecedentesEntity_antecedentesModelo(personaEntity);
		return new Persona(personaEntity.getIdPersona(), personaEntity.getApellidos(), personaEntity.getNombres(),
				personaEntity.getFechaNacimiento(), personaEntity.getDomicilio(),
				new TipoDocumento(personaEntity.getTipoDocumento().getIdTipoDocumento(),
						personaEntity.getTipoDocumento().getNombre()),
				personaEntity.getDocumento(), new Sangre(personaEntity.getSangre().getIdSangre(),
						personaEntity.getSangre().getGrupo(), personaEntity.getSangre().getFactor()),
				personaEntity.getTelefono(),
				new ObraSocial(personaEntity.getObraSocial().getIdObraSocial(),
						personaEntity.getObraSocial().getObraSocial()),
				personaEntity.getNroAfiliado(), antecedentes, personaEntity.getNroOrden());
	}

	private Collection<AntecedenteMedicoEntity> antecedentesModelo_AntecedentesEntity(Collection<AntecedenteMedico> antecedentePersona) {
		Collection<AntecedenteMedicoEntity> antecedenteMedicoEntities = new ArrayList<>();
        antecedentePersona.forEach(antecedente -> {
			AntecedenteMedicoEntity antecedenteMedicoEntity = new AntecedenteMedicoEntity();
			AfeccionEntity afeccion = afeccionRepositorioImplementacion.mapeoCoreData(antecedente.getAfeccion());
			if(antecedente.getIdAntecedenteMedico()!=null){
			    antecedenteMedicoEntity.setIdAntecedenteMedico(antecedente.getIdAntecedenteMedico());
            }
			antecedenteMedicoEntity.setAfeccion(afeccion);
			antecedenteMedicoEntity.setObservacion(antecedente.getObservacion());
			antecedenteMedicoEntities.add(antecedenteMedicoEntity);
		});
		return antecedenteMedicoEntities;
	}

	private Collection<AntecedenteMedico> antecedentesEntity_antecedentesModelo(PersonaEntity persona) {
		Collection<AntecedenteMedico> antecedenteMedicos = new ArrayList<>();
		persona.getAntecedenteMedicoCollection().stream().forEach(antecedente -> {
			AntecedenteMedico antecedenteMedico = new AntecedenteMedico();
			Afeccion afeccion = new Afeccion(antecedente.getAfeccion().getIdAfeccion(),
					antecedente.getAfeccion().getNombreAfeccion());
			antecedenteMedico.setAfeccion(afeccion);
			antecedenteMedico.setIdAntecedenteMedico(antecedente.getIdAntecedenteMedico());
			antecedenteMedico.setObservacion(antecedente.getObservacion());
		});
		return antecedenteMedicos;
	}
}
