package Adaptadores;

import Excepciones.*;
import Factorys.CrearComprobanteFactory;
import Inputs.CrearComprobanteDePagoInput;
import ModeloApi.ComprobanteDTO;

public class CrearComprobantePagoAdapter {
    private CrearComprobanteDePagoInput crearComprobanteDePagoInput;

    public CrearComprobantePagoAdapter(CrearComprobanteDePagoInput crearComprobanteDePagoInput) {
        this.crearComprobanteDePagoInput = crearComprobanteDePagoInput;
    }

    public boolean crearComprobantePago(ComprobanteDTO comprobanteDTO) throws ComprobanteIncompletoException, FechaIncorrectaException, NumeroComprobanteIncorrectoException, AfiliadoDeBajaException, ComprobanteExisteException {
        return crearComprobanteDePagoInput.crearComprobante(CrearComprobanteFactory.mapeoCoreDTO(comprobanteDTO));
    }
}
