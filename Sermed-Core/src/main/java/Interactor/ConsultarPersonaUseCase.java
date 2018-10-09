package Interactor;

import Excepciones.PersonaNoExisteException;
import Inputs.ConsultarPersonaInput;
import Modelo.Persona;
import Repositorio.IPersonaRepositorio;

import java.util.List;

public class ConsultarPersonaUseCase implements ConsultarPersonaInput {
    private IPersonaRepositorio repositorioPersona;

    public ConsultarPersonaUseCase(IPersonaRepositorio repositorioPersona) {

        this.repositorioPersona = repositorioPersona;
    }

    @Override
    public List<Persona> consultarPersonas() {
        return (List<Persona>) this.repositorioPersona.findAll();
    }

    @Override
    public List<Persona> consultarPersonasPorApellido(String apellido) {
        return (List<Persona>) repositorioPersona.findByApellido(apellido);
    }

    @Override
    public Persona consultarPersonaPorNumeroAfiliado(String numeroAfiliado, Integer orden) throws PersonaNoExisteException {
        Persona personaBuscada=repositorioPersona.findByNumeroAfiliado(numeroAfiliado,orden);
        if(personaBuscada!=null) {
            return personaBuscada;
        }
        else
            throw new PersonaNoExisteException();
    }
}
