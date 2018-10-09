package Interactor;

import Inputs.CrearVisitaInput;
import Modelo.Visita;
import Repositorio.IVisitaRepositorio;

public class CrearVisitaUseCase implements CrearVisitaInput {
    private IVisitaRepositorio repositorioVisita;

    public CrearVisitaUseCase(IVisitaRepositorio repositorioVisita) {
        this.repositorioVisita = repositorioVisita;
    }

    public boolean crearVisita(Visita visita) {
        return repositorioVisita.persist(visita);
    }
}
