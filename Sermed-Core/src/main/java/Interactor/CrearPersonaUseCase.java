package Interactor;

import Modelo.Persona;
import Repositorio.IPersonaRepositorio;

public class CrearPersonaUseCase {

    private IPersonaRepositorio repositorioPersona;

    public CrearPersonaUseCase(IPersonaRepositorio repositorioPersona) {
        this.repositorioPersona = repositorioPersona;
    }

    public boolean crearPersona(Persona persona) {
        if(!validarPersonaExiste(persona)){
            return repositorioPersona.persist(persona);
        }
        return false;
    }

    private boolean validarPersonaExiste(Persona persona) {
        if(repositorioPersona.findById(persona.getIdPersona())!= null){
            return true;
        }
        if(repositorioPersona.findByDocumentoAndTipoDocumento(persona.getDocumento(),persona.getTipoDocumento().getNombre())!=null){
            return true;
        }
        return false;
    }
}
