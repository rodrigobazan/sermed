package Repositorio;

import Modelo.Comprobante;

public interface IComprobanteRepositorio {

    Comprobante findByNumero(String numero);

    boolean persist(Comprobante recibo);
}
