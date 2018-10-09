package Interactor;

import Excepciones.ObraSocialExisteException;
import Inputs.CrearObraSocialInput;
import Modelo.ObraSocial;
import Repositorio.IObraSocialRepositorio;

public class CrearObraSocialUseCase implements CrearObraSocialInput {

    private IObraSocialRepositorio repositorioObraSocial;

    public CrearObraSocialUseCase(IObraSocialRepositorio repositorioObraSocial) {
        this.repositorioObraSocial = repositorioObraSocial;
    }

    public boolean crearObraSocial(ObraSocial obraSocial) throws ObraSocialExisteException {
        if(repositorioObraSocial.findByNombreUnico(obraSocial.getNombre())==null) {
            repositorioObraSocial.persist(obraSocial);
            return true;
        }
        throw new ObraSocialExisteException();
    }
}
