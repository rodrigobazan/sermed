package Interactor;

import Excepciones.VisitaNoExisteException;
import Modelo.Visita;
import Repositorio.IVisitaRepositorio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultarVisitaUseCase {
    private IVisitaRepositorio repositorioVisita;

    public ConsultarVisitaUseCase(IVisitaRepositorio repositorioVisita) {

        this.repositorioVisita = repositorioVisita;
    }

    public List<Visita> consultarVisitas() {
        return (List<Visita>) this.repositorioVisita.findAll();
    }

    public List<Visita> consultarVisitasEntreFechas(LocalDate desde, LocalDate hasta) {

        return this.repositorioVisita.findAll().stream().filter(visita -> (visita.getFecha().isAfter(desde) || visita.getFecha().isEqual(desde)) && (visita.getFecha().isBefore(hasta) || visita.getFecha().isEqual(hasta))).collect(Collectors.toList());
    }

    public Visita consultarVisitaPorNumero(int numeroVisita) throws VisitaNoExisteException {
        Visita visitaBuscada=this.repositorioVisita.findbyNumero(numeroVisita);
        if(visitaBuscada!=null){
            return visitaBuscada;
        }
        else
            throw new VisitaNoExisteException();
    }
}
