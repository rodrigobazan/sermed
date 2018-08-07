package Interactor;

import Modelo.Visita;
import Repositorio.IVisitaRepositorio;

public class CrearVisitaUseCase {
    private IVisitaRepositorio repositorioVisita;

    public CrearVisitaUseCase(IVisitaRepositorio repositorioVisita) {
        this.repositorioVisita = repositorioVisita;
    }

    public boolean crearVisita(Visita visita) {
        if(visita.getAfiliado().getActivo())
            return repositorioVisita.persist(visita);
        else
            return false;
    }
}
