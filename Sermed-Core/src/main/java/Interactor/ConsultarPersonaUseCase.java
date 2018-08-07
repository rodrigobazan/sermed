package Interactor;

import Excepciones.PersonaNoExisteException;
import Modelo.Persona;
import Repositorio.IPersonaRepositorio;

import java.util.List;

public class ConsultarPersonaUseCase {
    private IPersonaRepositorio repositorioPersona;

    public ConsultarPersonaUseCase(IPersonaRepositorio repositorioPersona) {

        this.repositorioPersona = repositorioPersona;
    }

    public List<Persona> consultarPersonas() {
        return (List<Persona>) this.repositorioPersona.findAll();
    }

    public List<Persona> consultarPersonasPorApellido(String apellido) {
        return (List<Persona>) repositorioPersona.findByApellido(apellido);
    }

    public Persona consultarPersonaPorNumeroAfiliado(String numeroAfiliado, Integer orden) throws PersonaNoExisteException {
        Persona personaBuscada=repositorioPersona.findByNumeroAfiliado(numeroAfiliado,orden);
        if(personaBuscada!=null) {
            return personaBuscada;
        }
        else
            throw new PersonaNoExisteException();
    }
}
