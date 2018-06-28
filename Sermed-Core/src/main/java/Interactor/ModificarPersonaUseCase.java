package Interactor;

import Excepciones.PersonaIncompletaException;
import Modelo.Persona;
import Repositorio.IPersonaRepositorio;

public class ModificarPersonaUseCase {
    private IPersonaRepositorio repositorioPersona;

    public ModificarPersonaUseCase(IPersonaRepositorio repositorioPersona) {
        this.repositorioPersona = repositorioPersona;
    }

    public boolean modificarPersona(Persona personaDatosNuevos) {
        try {
            Persona persona = repositorioPersona.findById(personaDatosNuevos.getIdPersona());
            if ((persona.getDocumento() == personaDatosNuevos.getDocumento() && persona.getTipoDocumento() == personaDatosNuevos.getTipoDocumento())
                    || repositorioPersona.findByDocumentoAndTipoDocumento(personaDatosNuevos.getDocumento(), personaDatosNuevos.getTipoDocumento().getNombre()) == null) {
                persona.modificarDatos(personaDatosNuevos);
                return repositorioPersona.update(persona);
            }
            return false;
        } catch (PersonaIncompletaException e) {
            e.printStackTrace();
            return false;
        }
    }
}
