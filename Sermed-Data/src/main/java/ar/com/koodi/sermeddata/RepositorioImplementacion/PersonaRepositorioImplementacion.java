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

    @Override
    @Transactional
    public boolean persist(Persona any) {
        return this.iPersonaRepositorioCRUD.save(mapeoCoreData(any)) != null;
    }

    @Override
    @Transactional(readOnly = true)
    public Persona findById(int idPersona) {
        return maperoDataCore(this.iPersonaRepositorioCRUD.findByIdPersona(idPersona));
    }

    @Override
    @Transactional(readOnly = true)
    public Persona findByDocumentoAndTipoDocumento(String documento, String tipoDocumento) {
        return maperoDataCore(this.iPersonaRepositorioCRUD.findByDocumentoandAndTipoDocumento(documento,tipoDocumento));
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
        this.iPersonaRepositorioCRUD.findAll().forEach(e -> personaEntities.add(maperoDataCore(e)));
        return personaEntities;
    }

    @Override
    @Transactional
    public Collection<Persona> findByApellido(String apellido) {
        List<Persona> personas = new ArrayList<>();
        this.iPersonaRepositorioCRUD.findByApellidosContains(apellido).forEach( e -> personas.add(maperoDataCore(e)));
        return personas;
    }

    @Override
    public Persona findByNumeroAfiliado(String numeroAfiliado, Integer orden) {
        return null;
    }

    public PersonaEntity mapeoCoreData(Persona persona){
        Collection <AntecedenteMedicoEntity> antecedenteMedicoEntities = null;
        for (AntecedenteMedico antecedente: persona.getAntecedentesMedico()){
            AntecedenteMedicoEntity antecedenteMedicoEntity = new AntecedenteMedicoEntity();
            AfeccionEntity afeccion = new AfeccionEntity(antecedente.getAfeccion().getNombreAfeccion());
            antecedenteMedicoEntity.setAfeccion(afeccion);
            antecedenteMedicoEntity.setObservacion(antecedente.getObservacion());
            antecedenteMedicoEntities.add(antecedenteMedicoEntity);
        }
        return new PersonaEntity(persona.getApellidos(), persona.getNombres(), persona.getFechaNacimiento(), persona.getDomicilio(), new TipoDocumentoEntity(persona.getTipoDocumento().getNombre()), persona.getDocumento(), new SangreEntity(persona.getSangre().getGrupo(), persona.getSangre().getFactor()), persona.getTelefono(), new ObraSocialEntity(persona.getObraSocial().getObraSocial()), persona.getNroAfiliado() , antecedenteMedicoEntities,persona.getNroOrden());
    }

    public Persona maperoDataCore(PersonaEntity personaEntity){
        Collection <AntecedenteMedico> antecedenteMedicos = null;
        for (AntecedenteMedicoEntity antecedente : personaEntity.getAntecedenteMedicoCollection()){
            AntecedenteMedico antecedenteMedico = new AntecedenteMedico();
            Afeccion afeccion = new Afeccion(antecedente.getAfeccion().getIdAfeccion(),antecedente.getAfeccion().getNombreAfeccion());
            antecedenteMedico.setAfeccion(afeccion);
            antecedenteMedico.setIdAntecedenteMedico(antecedente.getIdAntecedenteMedico());
            antecedenteMedico.setObservacion(antecedente.getObservacion());
        }
        return new Persona(personaEntity.getIdPersona(),personaEntity.getApellidos(),personaEntity.getNombres(),personaEntity.getFechaNacimiento(),personaEntity.getDomicilio(),new TipoDocumento(personaEntity.getTipoDocumento().getIdTipoDocumento(),personaEntity.getTipoDocumento().getNombre()),personaEntity.getDocumento(),new Sangre(personaEntity.getSangre().getIdSangre(),personaEntity.getSangre().getGrupo(),personaEntity.getSangre().getFactor()),personaEntity.getTelefono(),new ObraSocial(personaEntity.getObraSocial().getIdObraSocial(),personaEntity.getObraSocial().getObraSocial()),personaEntity.getNroAfiliado(),antecedenteMedicos,personaEntity.getNroOrden());
    }
}
