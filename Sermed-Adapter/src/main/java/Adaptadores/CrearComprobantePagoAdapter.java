package Adaptadores;

import Excepciones.*;
import Factorys.ComprobanteFactory;
import Inputs.CrearComprobanteDePagoInput;
import ModeloApi.ComprobanteDTO;

public class CrearComprobantePagoAdapter {
    private CrearComprobanteDePagoInput crearComprobanteDePagoInput;

    public CrearComprobantePagoAdapter(CrearComprobanteDePagoInput crearComprobanteDePagoInput) {
        this.crearComprobanteDePagoInput = crearComprobanteDePagoInput;
    }

    public boolean crearComprobantePago(ComprobanteDTO comprobanteDTO) throws PersonaIncompletaException, PlanIncompletoException, DniConPuntosException, NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException, AfiliadoSinPlanException, ComprobanteExisteException {
        return crearComprobanteDePagoInput.crearComprobante(ComprobanteFactory.mapeoDTOCore(comprobanteDTO));
    }
}
