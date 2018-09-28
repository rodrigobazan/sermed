package ar.com.koodi.sermeddata.RepositorioImplementacion;

import Modelo.PeriodoPago;
import Repositorio.IPeriodoPagoRepositorio;
import ar.com.koodi.sermeddata.ModeloData.PeriodoPagoEntity;
import ar.com.koodi.sermeddata.RepositorioData.IPeriodoPagoRepositorioCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PeriodoPagoRepositorioImplementacion implements IPeriodoPagoRepositorio {

    @Autowired
    IPeriodoPagoRepositorioCRUD iPeriodoPagoRepositorioCRUD;

    @Override
    @Transactional(readOnly = true)
    public Collection<PeriodoPago> findAll() {
        List<PeriodoPago> periodoPagoList = new ArrayList<>();
        this.iPeriodoPagoRepositorioCRUD.findAll().forEach(p -> periodoPagoList.add(mapeoDataCore(p)));
        return periodoPagoList;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PeriodoPago> findByAnio(int anio) {
        List<PeriodoPago> periodoPagos = new ArrayList<>();
        this.iPeriodoPagoRepositorioCRUD.findByAnio(anio).forEach(p -> periodoPagos.add(mapeoDataCore(p)));
        return periodoPagos;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PeriodoPago> findByMes(int mes) {
        List<PeriodoPago> periodoPagos = new ArrayList<>();
        this.iPeriodoPagoRepositorioCRUD.findByMes(mes).forEach(p -> periodoPagos.add(mapeoDataCore(p)));
        return periodoPagos;
    }

    @Override
    @Transactional(readOnly = true)
    public PeriodoPago findByMesAnio(int mes, int anio) {
        PeriodoPagoEntity periodoPagoEntity = this.iPeriodoPagoRepositorioCRUD.findByMesAndAnio(mes, anio);
        if(periodoPagoEntity != null) return mapeoDataCore(periodoPagoEntity);
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PeriodoPago> findIntervalo(int mesDesde, int mesHasta, int anio) {
        List<PeriodoPago> periodoPagos = new ArrayList<>();
        this.iPeriodoPagoRepositorioCRUD.findByMesIsBetweenAndAnio(mesDesde, mesHasta, anio).forEach(p -> periodoPagos.add(mapeoDataCore(p)));
        return periodoPagos;

    }

    public PeriodoPago mapeoDataCore(PeriodoPagoEntity periodoPagoEntity) {
        return new PeriodoPago(periodoPagoEntity.getIdPeriodoPago(), periodoPagoEntity.getMes(), periodoPagoEntity.getAnio());
    }

    public PeriodoPagoEntity mapeoCoreData(PeriodoPago periodoPago){
        if(periodoPago.getIdPeriodoPago() == null){
            return new PeriodoPagoEntity(periodoPago.getMes(),periodoPago.getAnio());
        }else {
            return iPeriodoPagoRepositorioCRUD.findByMesAndAnio(periodoPago.getMes(), periodoPago.getAnio());
        }
    }
}
