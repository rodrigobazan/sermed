package Adaptadores;

import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Factorys.PersonaFactory;
import Inputs.BuscarPersonaEntreAfiliadosDeBajaInput;
import ModeloApi.PersonaDTO;

public class BuscarPersonaEntreAfiliadosDeBajaAdapter {
    private BuscarPersonaEntreAfiliadosDeBajaInput buscarPersonaEntreAfiliadosDeBajaInput;

    public BuscarPersonaEntreAfiliadosDeBajaAdapter(BuscarPersonaEntreAfiliadosDeBajaInput buscarPersonaEntreAfiliadosDeBajaInput) {
        this.buscarPersonaEntreAfiliadosDeBajaInput = buscarPersonaEntreAfiliadosDeBajaInput;
    }

    public boolean existePersonaPorDNI(PersonaDTO persona) throws NumeroAfiliadoIncorrectoException, PersonaIncompletaException, DniConPuntosException {
        return buscarPersonaEntreAfiliadosDeBajaInput.existePersona(PersonaFactory.mapeoDTOCore(persona));
    }
}
