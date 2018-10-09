package Interactor;

import Excepciones.ObraSocialNoExisteException;
import Inputs.ConsultarObrasSocialesInput;
import Modelo.ObraSocial;
import Repositorio.IObraSocialRepositorio;

import java.util.List;

public class ConsultarObrasSocialesUseCase implements ConsultarObrasSocialesInput {
    private IObraSocialRepositorio repositorioObraSocial;

    public ConsultarObrasSocialesUseCase(IObraSocialRepositorio repositorioObraSocial) {
        this.repositorioObraSocial = repositorioObraSocial;
    }

    public List<ObraSocial> consultarObrasSociales() {
        return (List<ObraSocial>) repositorioObraSocial.findAll();
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
