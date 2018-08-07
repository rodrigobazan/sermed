package Repositorio;

import Modelo.Afiliado;
import Modelo.Comprobante;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface IComprobanteRepositorio {

    Comprobante findByNumero(String numero);

    boolean persist(Comprobante comprobante);

    Collection<Comprobante> findAll();

    boolean update(Comprobante comprobanteAAnular);

    Collection<Comprobante> findByFechas(LocalDate fechaDesde, LocalDate fechaHasta);

    Collection<Comprobante> findByAfiliado(Afiliado afiliado);

    /*List<Comprobante> findByNumero(String numeroComprobante);*/
}
