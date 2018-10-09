package Interactor;

import Excepciones.PersonaExisteException;
import Inputs.CrearPersonaInput;
import Modelo.Persona;
import Repositorio.IPersonaRepositorio;

public class CrearPersonaUseCase implements CrearPersonaInput {

    private IPersonaRepositorio repositorioPersona;

    public CrearPersonaUseCase(IPersonaRepositorio repositorioPersona) {
        this.repositorioPersona = repositorioPersona;
    }

    @Override
    public boolean crearPersona(Persona persona) throws PersonaExisteException {
        if (!validarPersonaExiste(persona)) {
            return repositorioPersona.persist(persona);
        }
        throw new PersonaExisteException();
    }

    private boolean validarPersonaExiste(Persona persona) {
        return repositorioPersona.findByDocumentoAndTipoDocumento(persona.getDocumento(), persona.getTipoDocumento().getNombre()) != null;
    }
}
