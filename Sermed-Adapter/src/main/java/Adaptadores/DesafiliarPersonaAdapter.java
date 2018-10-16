package Adaptadores;

import Excepciones.*;
import Factorys.AfiliadoFactory;
import Factorys.PersonaFactory;
import Inputs.DesafiliarPersonaInput;
import ModeloApi.AfiliadoDTO;
import ModeloApi.PersonaDTO;

public class DesafiliarPersonaAdapter {
    private DesafiliarPersonaInput desafiliarPersonaInput;

    public DesafiliarPersonaAdapter(DesafiliarPersonaInput desafiliarPersonaInput) {
        this.desafiliarPersonaInput = desafiliarPersonaInput;
    }

    public boolean desafiliarPersona(PersonaDTO persona, AfiliadoDTO afiliado) throws PersonaIncompletaException, PlanIncompletoException, AfiliadoSinPlanException, NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException, DniConPuntosException, PersonaNoAfiliadaException {
        return desafiliarPersonaInput.desafiliarPersona(PersonaFactory.mapeoDTOCore(persona), AfiliadoFactory.mapeoDTOCore(afiliado));
    }
}
