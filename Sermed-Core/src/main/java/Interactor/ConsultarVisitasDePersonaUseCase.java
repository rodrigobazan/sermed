package Interactor;

import Modelo.Afiliado;
import Modelo.Persona;
import Modelo.Visita;
import Repositorio.IVisitaRepositorio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Inputs.ConsultarVisitasDePersonaInput;

public class ConsultarVisitasDePersonaUseCase implements ConsultarVisitasDePersonaInput{
    private IVisitaRepositorio repositorioVisita;

    public ConsultarVisitasDePersonaUseCase(IVisitaRepositorio repositorioVisita) {

        this.repositorioVisita = repositorioVisita;
    }

    public List<Visita> consultarVisitasPersona(Persona laPersona) {
        return (List<Visita>) this.repositorioVisita.findByPersona(laPersona);
    }

    public List<Visita> consultarVisitasPersonaPorFechas(Persona laPersona, LocalDate fechaDesde, LocalDate fechaHasta) {
        List<Visita> visitasPersona = (List<Visita>) this.repositorioVisita.findByPersona(laPersona);
        if (visitasPersona != null) {
            return visitasPersona.stream().filter(visita -> (visita.getFecha().isAfter(fechaDesde) || visita.getFecha().isEqual(fechaDesde)) && (visita.getFecha().isBefore(fechaHasta) || visita.getFecha().isEqual(fechaHasta))).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
