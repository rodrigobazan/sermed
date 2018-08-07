package Interactor;

import Excepciones.PersonaNoExisteException;
import Modelo.Persona;
import Modelo.Visita;
import ModeloReporte.HistoriaClinicaPersonaDTO;
import Repositorio.IPersonaRepositorio;
import Repositorio.IVisitaRepositorio;

import java.util.List;

public class GenerarHistoriaClinicaPersonaUseCase {
    private IPersonaRepositorio repositorioPersona;
    private IVisitaRepositorio repositorioVisita;

    public GenerarHistoriaClinicaPersonaUseCase(IPersonaRepositorio repositorioPersona, IVisitaRepositorio repositorioVisita) {
        this.repositorioPersona = repositorioPersona;
        this.repositorioVisita = repositorioVisita;
    }

    public HistoriaClinicaPersonaDTO generarHistoriaClinicaPersona(String numeroAfiliado, Integer orden) throws PersonaNoExisteException {

        ConsultarPersonaUseCase consultarPersonaUseCase=new ConsultarPersonaUseCase(repositorioPersona);
        Persona personaBuscada=consultarPersonaUseCase.consultarPersonaPorNumeroAfiliado(numeroAfiliado,orden);
        ConsultarVisitasDePersonaUseCase consultarVisitaUseCase=new ConsultarVisitasDePersonaUseCase(repositorioVisita);
        List<Visita> visitasDeLaPersona= consultarVisitaUseCase.consultarVisitasPersona(personaBuscada);

        HistoriaClinicaPersonaDTO historiaClinica=new HistoriaClinicaPersonaDTO(personaBuscada, personaBuscada.devolverAntecedentes(), visitasDeLaPersona);
        return historiaClinica;
    }
}
