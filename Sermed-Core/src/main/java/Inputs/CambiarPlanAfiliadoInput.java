package Inputs;

import Excepciones.PlanesIgualesException;
import Excepciones.UpdateAfiliadoException;
import Modelo.Afiliado;
import Modelo.Plan;

public interface CambiarPlanAfiliadoInput {

    Afiliado modificarPlanAfiliado(Afiliado afiliado, Plan plan) throws PlanesIgualesException, UpdateAfiliadoException;
}
