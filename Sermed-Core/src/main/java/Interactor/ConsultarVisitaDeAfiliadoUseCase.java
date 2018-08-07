package Interactor;

import Modelo.Afiliado;
import Modelo.Visita;
import Repositorio.IVisitaRepositorio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultarVisitaDeAfiliadoUseCase {
    private IVisitaRepositorio repositorioVisita;

    public ConsultarVisitaDeAfiliadoUseCase(IVisitaRepositorio repositorioVisita) {

        this.repositorioVisita = repositorioVisita;
    }

    public List<Visita> consultarVisitasAfiliado(Afiliado afiliado) {
        return (List<Visita>) this.repositorioVisita.findByAfiliado(afiliado);
    }

    public List<Visita> consultarVisitasAfiliadoPorFechas(Afiliado afiliado, LocalDate fechaDesde, LocalDate fechaHasta) {
       if(this.repositorioVisita.findByAfiliado(afiliado)!=null){
        return  this.repositorioVisita.findByAfiliado(afiliado).stream().filter(visita -> (visita.getFecha().isAfter(fechaDesde) || visita.getFecha().isEqual(fechaDesde)) && (visita.getFecha().isBefore(fechaHasta) || visita.getFecha().isEqual(fechaHasta))).collect(Collectors.toList());
       }
       return new ArrayList<>();
    }
}
