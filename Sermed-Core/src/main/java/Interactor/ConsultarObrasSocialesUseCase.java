package Interactor;

import Excepciones.ObraSocialNoExisteException;
import Modelo.ObraSocial;
import Repositorio.IObraSocialRepositorio;

import java.util.Collection;
import java.util.List;

public class ConsultarObrasSocialesUseCase {
    private IObraSocialRepositorio repositorioObraSocial;

    public ConsultarObrasSocialesUseCase(IObraSocialRepositorio repositorioObraSocial) {
        this.repositorioObraSocial = repositorioObraSocial;
    }

    public Collection<ObraSocial> consultarObrasSociales() {
        return repositorioObraSocial.findAll();
    }

    public List<ObraSocial> consultarObrasSocialesPorNombre(String nombre) {
        return (List<ObraSocial>) repositorioObraSocial.findByNombre(nombre);

    }

    public ObraSocial consultarObraSocialPorNombre(String nombre) throws ObraSocialNoExisteException {
        ObraSocial buscada=repositorioObraSocial.findByNombreUnico(nombre);
        if(buscada!=null)
            return buscada;
        else
        {
            throw new ObraSocialNoExisteException();
        }

    }
}
