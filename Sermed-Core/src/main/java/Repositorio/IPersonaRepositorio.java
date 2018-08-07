package Repositorio;

import Modelo.Persona;
import Modelo.TipoDocumento;

import java.util.Collection;

public interface IPersonaRepositorio {
    boolean persist(Persona any);

    Persona findById(int idPersona);

    Persona findByDocumentoAndTipoDocumento(String documento, String tipoDocumento);

    boolean update(Persona personaDatosNuevos);

    Collection<Persona> findAll();

    Collection<Persona> findByApellido(String apellido);

    Persona findByNumeroAfiliado(String numeroAfiliado, Integer orden);
}
