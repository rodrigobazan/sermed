package Adaptadores;

import Excepciones.*;
import Factorys.ComprobanteFactory;
import Inputs.AnularComprobanteInput;
import ModeloApi.ComprobanteDTO;

public class AnularComprobanteAdapter {
    private AnularComprobanteInput anularComprobanteInput;

    public AnularComprobanteAdapter(AnularComprobanteInput anularComprobanteInput) {
        this.anularComprobanteInput = anularComprobanteInput;
    }

    public boolean anularComprobante(ComprobanteDTO comprobanteAAnular) throws PersonaIncompletaException, PlanIncompletoException, DniConPuntosException, NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException, AfiliadoSinPlanException, ComprobanteAnuladoException {
        return anularComprobanteInput.anularComprobante(ComprobanteFactory.mapeoDTOCore(comprobanteAAnular));
    }
}
