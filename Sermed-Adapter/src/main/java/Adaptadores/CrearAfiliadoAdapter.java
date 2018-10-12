package Adaptadores;

import Excepciones.*;
import Factorys.AfiliadoFactory;
import Inputs.CrearAfiliadoInput;
import ModeloApi.AfiliadoDTO;

public class CrearAfiliadoAdapter {
    private CrearAfiliadoInput crearAfiliadoInput;

    public CrearAfiliadoAdapter(CrearAfiliadoInput crearAfiliadoInput) {
        this.crearAfiliadoInput = crearAfiliadoInput;
    }

    public boolean crearAfiliado(AfiliadoDTO afiliadoDTO) throws PersonaIncompletaException, PlanIncompletoException, AfiliadoSinPlanException, NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException, DniConPuntosException, AfiliadoExisteException, TitularEnAfiliadoActivoException {
        return crearAfiliadoInput.crearAfiliado(AfiliadoFactory.mapeoDTOCore(afiliadoDTO));
    }
}
