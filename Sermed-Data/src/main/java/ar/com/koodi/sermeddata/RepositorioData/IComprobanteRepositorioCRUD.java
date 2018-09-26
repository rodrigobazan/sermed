package ar.com.koodi.sermeddata.RepositorioData;


import ar.com.koodi.sermeddata.ModeloData.ComprobanteEntity;
import org.springframework.data.repository.CrudRepository;

public interface IComprobanteRepositorioCRUD extends CrudRepository<ComprobanteEntity, Integer> {

    ComprobanteEntity save(ComprobanteEntity comprobanteEntity);
}
