package Factorys;

import Excepciones.*;
import Modelo.Comprobante;
import Modelo.PeriodoPago;
import ModeloApi.ComprobanteDTO;
import ModeloApi.PeriodoPagoDTO;

import java.util.ArrayList;
import java.util.List;

public class ComprobanteFactory {

    private ComprobanteFactory() {
    }

    public static ComprobanteDTO mapeoCoreDTO(Comprobante comprobante){
        List<PeriodoPagoDTO> periodoPagoDTOS = new ArrayList<>();
        if(comprobante.getPeriodosAbonados() != null && !comprobante.getPeriodosAbonados().isEmpty()){
            comprobante.getPeriodosAbonados().forEach(periodoPago -> periodoPagoDTOS.add(PeriodoPagoFactory.maperoCoreDTO(periodoPago)));
        }
        return new ComprobanteDTO(comprobante.getIdComprobante(), comprobante.getNumero(), AfiliadoFactory.mapeoCoreDTO(comprobante.getAfiliado()), comprobante.getTotal(), comprobante.getFechaCreacion(),
                comprobante.getModoPago(), comprobante.getActivo(), periodoPagoDTOS);
    }

    public static Comprobante mapeoDTOCore(ComprobanteDTO comprobante) throws PersonaIncompletaException, PlanIncompletoException, AfiliadoSinPlanException, NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException, DniConPuntosException {
        List<PeriodoPago> periodosDePagos = new ArrayList<>();
        if(comprobante.periodosAbonados != null && !comprobante.periodosAbonados.isEmpty()){
            comprobante.periodosAbonados.forEach(periodoPagoDTO -> periodosDePagos .add(PeriodoPagoFactory.mapeoDTOCore(periodoPagoDTO)));
        }
        return new Comprobante(comprobante.idComprobante, comprobante.numeroComprobante, AfiliadoFactory.mapeoDTOCore(comprobante.afiliado), comprobante.total, comprobante.fechaCreacion, comprobante.modoDePago, comprobante.activo,
                periodosDePagos );
    }
}
