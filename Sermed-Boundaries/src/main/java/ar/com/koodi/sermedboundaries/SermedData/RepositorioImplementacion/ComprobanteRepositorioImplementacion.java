package ar.com.koodi.sermedboundaries.SermedData.RepositorioImplementacion;

import Modelo.Afiliado;
import Modelo.Comprobante;
import Modelo.PeriodoPago;
import Repositorio.IComprobanteRepositorio;
import ar.com.koodi.sermedboundaries.SermedData.ModeloData.AfiliadoEntity;
import ar.com.koodi.sermedboundaries.SermedData.ModeloData.ComprobanteEntity;
import ar.com.koodi.sermedboundaries.SermedData.ModeloData.PeriodoPagoEntity;
import ar.com.koodi.sermedboundaries.SermedData.RepositorioData.IComprobanteRepositorioCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ComprobanteRepositorioImplementacion implements IComprobanteRepositorio {

    @Autowired
    IComprobanteRepositorioCRUD iComprobanteRepositorioCRUD;

    @Autowired
    AfiliadoRepositorioImplementacion afiliadoRepositorioImplementacion;

    @Autowired
    PeriodoPagoRepositorioImplementacion periodoPagoRepositorioImplementacion;

    @Override
    @Transactional
    public boolean persist(Comprobante comprobante) {
        return this.iComprobanteRepositorioCRUD.save(mapeoCoreData(comprobante)) != null;
    }

    @Override
    @Transactional(readOnly = true)
    public Comprobante findByNumero(String numero) {
        ComprobanteEntity comprobanteEntity = this.iComprobanteRepositorioCRUD.findByNumeroComprobanteEquals(numero);
        if (comprobanteEntity != null) return mapeoDataCore(comprobanteEntity);
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Comprobante> findAll() {
        List<Comprobante> comprobantes = new ArrayList<>();
        this.iComprobanteRepositorioCRUD.findAll().forEach(comprobanteEntity -> comprobantes.add(mapeoDataCore(comprobanteEntity)));
        return comprobantes;
    }

    @Override
    @Transactional
    public boolean update(Comprobante comprobanteAAnular) {
        List<PeriodoPagoEntity> periodoPagoEntities = new ArrayList<>();
        AfiliadoEntity afiliadoEntity = afiliadoRepositorioImplementacion.mapeoCoreData(comprobanteAAnular.getAfiliado());
        afiliadoEntity.setIdAfiliado(comprobanteAAnular.getAfiliado().getIdAfiliado());
        comprobanteAAnular.getPeriodosAbonados().forEach(p -> {
            PeriodoPagoEntity periodo = periodoPagoRepositorioImplementacion.mapeoCoreData(p);
            periodo.setIdPeriodoPago(p.getIdPeriodoPago());
            periodoPagoEntities.add(periodo);
        });
        ComprobanteEntity comprobanteEntity = new ComprobanteEntity(comprobanteAAnular.getNumero(), afiliadoEntity, comprobanteAAnular.getTotal(),
                comprobanteAAnular.getFechaCreacion(), comprobanteAAnular.getModoPago(), false, periodoPagoEntities);
        comprobanteEntity.setIdComprobante(comprobanteAAnular.getIdComprobante());
        return iComprobanteRepositorioCRUD.save(comprobanteEntity) != null;

    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Comprobante> findByFechas(LocalDate fechaDesde, LocalDate fechaHasta) {
        List<Comprobante> comprobantes = new ArrayList<>();
        this.iComprobanteRepositorioCRUD.findByFechaCreacionBetween(fechaDesde, fechaHasta).forEach(comprobanteEntity -> comprobantes.add(mapeoDataCore(comprobanteEntity)));
        return comprobantes;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Comprobante> findByAfiliado(Afiliado afiliado) {
        List<Comprobante> comprobantes = new ArrayList<>();
        this.iComprobanteRepositorioCRUD.findAllByAfiliado_NumeroAfiliado(afiliado.getNumeroAfiliado()).forEach(comprobanteEntity -> comprobantes.add(mapeoDataCore(comprobanteEntity)));
        return comprobantes;
    }


    public ComprobanteEntity mapeoCoreData(Comprobante comprobante) {
        if (comprobante.getIdComprobante() == null) {
            List<PeriodoPagoEntity> periodoPagoEntities = new ArrayList<>();
            if (comprobante.getPeriodosAbonados() != null && !comprobante.getPeriodosAbonados().isEmpty()) {
                comprobante.getPeriodosAbonados().forEach(periodoPago -> periodoPagoEntities.add(periodoPagoRepositorioImplementacion.mapeoCoreData(periodoPago)));
            }

            return new ComprobanteEntity(comprobante.getNumero(), afiliadoRepositorioImplementacion.mapeoCoreData(comprobante.getAfiliado()),
                    comprobante.getTotal(), comprobante.getFechaCreacion(), comprobante.getModoPago(), comprobante.getActivo(), periodoPagoEntities);
        } else {
            return iComprobanteRepositorioCRUD.findByIdComprobante(comprobante.getIdComprobante());
        }
    }


    public Comprobante mapeoDataCore(ComprobanteEntity comprobanteEntity) {
        List<PeriodoPago> periodoPago = new ArrayList<>();
        Afiliado afiliado = afiliadoRepositorioImplementacion.mapeoDataCore(comprobanteEntity.getAfiliado());
        comprobanteEntity.getPeriodosAbonados().forEach(p -> periodoPago.add(periodoPagoRepositorioImplementacion.mapeoDataCore(p)));

        return new Comprobante(comprobanteEntity.getIdComprobante(), comprobanteEntity.getNumeroComprobante(), afiliado, comprobanteEntity.getTotal(),
                comprobanteEntity.getFechaCreacion(), comprobanteEntity.getModoDePago(), comprobanteEntity.isActivo(), periodoPago);
    }

}
