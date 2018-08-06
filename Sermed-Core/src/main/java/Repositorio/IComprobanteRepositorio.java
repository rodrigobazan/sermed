package Repositorio;

import Modelo.Comprobante;

import java.time.LocalDate;
import java.util.List;

public interface IComprobanteRepositorio {

    Comprobante findByNumero(String numero);

    boolean persist(Comprobante comprobante);

    List<Comprobante> findAll();

    boolean update(Comprobante comprobanteAAnular);

    List<Comprobante> findByFechas(LocalDate fechaDesde, LocalDate fechaHasta);

    /*List<Comprobante> findByNumero(String numeroComprobante);*/
}
