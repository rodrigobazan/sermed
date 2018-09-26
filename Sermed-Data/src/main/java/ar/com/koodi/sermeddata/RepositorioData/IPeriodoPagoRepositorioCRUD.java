package ar.com.koodi.sermeddata.RepositorioData;

import ar.com.koodi.sermeddata.ModeloData.PeriodoPagoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IPeriodoPagoRepositorioCRUD extends CrudRepository<PeriodoPagoEntity, Integer> {

    Collection<PeriodoPagoEntity> findAll();

    Collection<PeriodoPagoEntity> findByAnio(int anio);

    Collection<PeriodoPagoEntity> findByMes(int mes);

    PeriodoPagoEntity findByMesAndAnio(int mes, int anio);

    Collection<PeriodoPagoEntity> findByMesIsBetweenAndAnio(int mesDesde, int mesHasta, int anio);
}
