package Adaptadores;

import Excepciones.*;
import Factorys.AfiliadoFactory;
import Factorys.PlanFactory;
import Inputs.CambiarPlanAfiliadoInput;
import ModeloApi.AfiliadoDTO;
import ModeloApi.PlanDTO;

public class CambiarPlanAfiliadoAdapter {
    private CambiarPlanAfiliadoInput cambiarPlanAfiliadoInput;

    public CambiarPlanAfiliadoAdapter(CambiarPlanAfiliadoInput cambiarPlanAfiliadoInput) {
        this.cambiarPlanAfiliadoInput = cambiarPlanAfiliadoInput;
    }

    public AfiliadoDTO modificarPlanAfiliado(AfiliadoDTO afiliadoAModificar, PlanDTO planNuevo) throws PlanIncompletoException, DniConPuntosException, PersonaIncompletaException, AfiliadoSinTitularException, NumeroAfiliadoIncorrectoException, AfiliadoSinPlanException, PlanesIgualesException, UpdateAfiliadoException {
        return AfiliadoFactory.mapeoCoreDTO(cambiarPlanAfiliadoInput.modificarPlanAfiliado(AfiliadoFactory.mapeoDTOCore(afiliadoAModificar), PlanFactory.mapeoDTOCore(planNuevo)));
    }
}
