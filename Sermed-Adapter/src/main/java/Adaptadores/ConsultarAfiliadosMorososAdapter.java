package Adaptadores;

import Factorys.AfiliadoFactory;
import Inputs.ConsultarAfiliadosMorososInput;
import ModeloApi.AfiliadoDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsultarAfiliadosMorososAdapter {
    private ConsultarAfiliadosMorososInput consultarAfiliadosMorososInput;

    public ConsultarAfiliadosMorososAdapter(ConsultarAfiliadosMorososInput consultarAfiliadosMorososInput) {
        this.consultarAfiliadosMorososInput = consultarAfiliadosMorososInput;
    }

    public List<AfiliadoDTO> consultarAfiliadosMorosos(LocalDate fecha) {
        List<AfiliadoDTO> afiliadoDTOS = new ArrayList<>();
        consultarAfiliadosMorososInput.consultarAfiliadosMorosos(fecha).forEach(afiliado -> afiliadoDTOS.add(AfiliadoFactory.mapeoCoreDTO(afiliado)));
        return afiliadoDTOS;
    }
}
