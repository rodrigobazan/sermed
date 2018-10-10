package Adaptadores;

import Excepciones.ObraSocialExisteException;
import Inputs.CrearObraSocialInput;
import Modelo.ObraSocial;
import ModeloApi.ObraSocialDTO;

public class CrearObraSocialAdapter {
    private CrearObraSocialInput crearObraSocialInput;

    public CrearObraSocialAdapter(CrearObraSocialInput crearObraSocialInput) {
        this.crearObraSocialInput = crearObraSocialInput;
    }


    public boolean crearObraSocial(ObraSocialDTO obraSocialDTO) throws ObraSocialExisteException {
        return crearObraSocialInput.crearObraSocial(mapeoAdapterCore(obraSocialDTO));
    }

    private ObraSocial mapeoAdapterCore(ObraSocialDTO obraSocialDTO) {
        return new ObraSocial(obraSocialDTO.idObraSocial, obraSocialDTO.obraSocial);
    }
}
