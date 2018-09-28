package ar.com.koodi.sermeddata.RepositorioData;


import Modelo.Afiliado;
import ar.com.koodi.sermeddata.ModeloData.ComprobanteEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Collection;

public interface IComprobanteRepositorioCRUD extends CrudRepository<ComprobanteEntity, Integer> {

    ComprobanteEntity save(ComprobanteEntity comprobanteEntity);

    ComprobanteEntity findByIdComprobante(Integer id);

    ComprobanteEntity findByNumeroComprobanteEquals(String numeroComprobante);

    Collection<ComprobanteEntity> findAll();

    Collection<ComprobanteEntity> findByFechaCreacionBetween(LocalDate fechasDesde, LocalDate fechaHasta);

    Collection<ComprobanteEntity> findAllByAfiliado_NumeroAfiliado(String numeroAfiliado);
}
