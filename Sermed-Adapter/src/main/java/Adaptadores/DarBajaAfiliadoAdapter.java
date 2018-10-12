package Adaptadores;

import Excepciones.*;
import Factorys.AfiliadoFactory;
import Inputs.DarBajaAfiliadoInput;
import ModeloApi.AfiliadoDTO;

import java.time.LocalDate;

public class DarBajaAfiliadoAdapter {
    private DarBajaAfiliadoInput darBajaAfiliadoInput;

    public DarBajaAfiliadoAdapter(DarBajaAfiliadoInput darBajaAfiliadoInput) {
        this.darBajaAfiliadoInput = darBajaAfiliadoInput;
    }

    public boolean darBajaAfiliado(AfiliadoDTO afiliado, LocalDate fecha) throws PersonaIncompletaException, PlanIncompletoException, AfiliadoSinPlanException, NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException, DniConPuntosException, AfiliadoDeBajaException {
        return darBajaAfiliadoInput.darBajaAfiliado(AfiliadoFactory.mapeoDTOCore(afiliado), fecha);
    }
}
