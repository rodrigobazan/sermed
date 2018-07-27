package Interactor;

import Modelo.Enfermero;
import Repositorio.IEnfermeroRepositorio;

public class ModificarEnfermeroUseCase {


    private IEnfermeroRepositorio repositorioEnfermero;

    public ModificarEnfermeroUseCase(IEnfermeroRepositorio repositorioEnfermero) {
        this.repositorioEnfermero = repositorioEnfermero;
    }

    public boolean modificarEnfermero(Enfermero nuevosDatos) {
        try {
            Enfermero elEnfermeroOriginal = repositorioEnfermero.findById(nuevosDatos.getIdEnfermero());
            if (elEnfermeroOriginal.getMatricula() == nuevosDatos.getMatricula() || repositorioEnfermero.findByMatricula(nuevosDatos.getMatricula()) == null) {
                elEnfermeroOriginal.modificarDatos(nuevosDatos);
                return this.repositorioEnfermero.update(elEnfermeroOriginal);
            } else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
