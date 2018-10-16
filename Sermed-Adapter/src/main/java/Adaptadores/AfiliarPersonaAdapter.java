package Adaptadores;

import Excepciones.*;
import Factorys.AfiliadoFactory;
import Factorys.PersonaFactory;
import Inputs.AfiliarPersonaInput;
import ModeloApi.AfiliadoDTO;
import ModeloApi.PersonaDTO;

public class AfiliarPersonaAdapter {
    private AfiliarPersonaInput afiliarPersonaInput;

    public AfiliarPersonaAdapter(AfiliarPersonaInput afiliarPersonaInput) {
        this.afiliarPersonaInput = afiliarPersonaInput;
    }

    public boolean afiliadPersona(PersonaDTO persona, AfiliadoDTO afiliado) throws PersonaIncompletaException, PlanIncompletoException, AfiliadoSinPlanException, NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException, DniConPuntosException, PersonaAfiliadaException {
        return afiliarPersonaInput.afiliarPersona(PersonaFactory.mapeoDTOCore(persona), AfiliadoFactory.mapeoDTOCore(afiliado));
    }
}
