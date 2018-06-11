package Interactor;

import Modelo.Enfermero;
import Repositorio.IEnfermeroRepositorio;

import java.util.ArrayList;
import java.util.List;

public class ConsultarEnfermeroUseCase {
    private IEnfermeroRepositorio repositorioEnfermero;

    public ConsultarEnfermeroUseCase(IEnfermeroRepositorio repositorioEnfermero) {
        this.repositorioEnfermero = repositorioEnfermero;
    }

    public List<Enfermero> consultarEnfermeros() {
        return this.repositorioEnfermero.findAll();
    }

    public List<Enfermero> consultarEnfermerosPorApellido(String apellido) {
        return this.repositorioEnfermero.findByApellido(apellido);
    }

    public Enfermero consultarEnfermeroPorMatricula(int matricula) {
        return this.repositorioEnfermero.findByMatricula(matricula);
    }
}
