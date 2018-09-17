package Interactor;

import Excepciones.NombreObraSocialExisteException;
import Excepciones.UpdateObraSocialException;
import Modelo.ObraSocial;
import Repositorio.IObraSocialRepositorio;

public class ModificarObraSocialUseCase {
    private IObraSocialRepositorio repositorioObraSocial;

    public ModificarObraSocialUseCase(IObraSocialRepositorio repositorioObraSocial) {

        this.repositorioObraSocial = repositorioObraSocial;
    }

    public ObraSocial modificarObraSocial(ObraSocial nuevosDatos) throws NombreObraSocialExisteException, UpdateObraSocialException {
        ObraSocial obraSocialAModificar= repositorioObraSocial.findById(nuevosDatos.getIdObraSocial());
        if(obraSocialAModificar.getNombre().equals(nuevosDatos.getNombre()) || repositorioObraSocial.findByNombreUnico(nuevosDatos.getNombre())==null){
            obraSocialAModificar.modificarDatos(nuevosDatos);
            if(repositorioObraSocial.update(obraSocialAModificar))
                return obraSocialAModificar;
            throw new UpdateObraSocialException();
        }else
        {
            throw new NombreObraSocialExisteException();
        }
    }
}
