package Factorys;

import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaExisteException;
import Excepciones.PersonaIncompletaException;
import Modelo.Persona;
import ModeloApi.PersonaDTO;

public class PersonaFactory {

    private PersonaFactory() {
    }

    public static Persona mapeoDTOCore(PersonaDTO persona) throws DniConPuntosException, PersonaIncompletaException, NumeroAfiliadoIncorrectoException {
        return Persona.instancia(persona.idPersona, persona.apellidos, persona.nombres, persona.fechaNacimiento, persona.domicilio, TipoDocumentoFactory.mapeoDTOCore(persona.tipoDocumento),
                persona.documento, SangreFactory.mapeoDTOCore(persona.sangre), persona.telefono, ObraSocialFactory.mapeoDTOCore(persona.obraSocial), persona.nroAfiliado,
                AntecedenteMedicoFactory.mapeoDTOCore(persona.antecedentesMedico), persona.nroOrden);

    }
}
