package Modelo;

import Excepciones.PersonaIncompletaException;

import java.time.LocalDate;
import java.util.Collection;

public class Persona {


    private Integer idPersona;
    private String apellidos;
    private String nombres;
    private LocalDate fechaNacimiento;
    private String domicilio;
    private TipoDocumento tipoDocumento;
    private String documento;
    private Sangre sangre;
    private String telefono;
    private ObraSocial obraSocial;
    private String nroAfiliado;
    private Collection<AntecedenteMedico> antecedentesMedico;

    private Persona(Integer idPersona, String apellidos, String nombres, LocalDate fechaNacimiento, String domicilio, TipoDocumento tipoDocumento, String documento, Sangre sangre,
                   String telefono, ObraSocial obraSocial, String nroAfiliado, Collection<AntecedenteMedico> antecedentesMedico) {
        this.idPersona = idPersona;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.fechaNacimiento = fechaNacimiento;
        this.domicilio = domicilio;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.sangre = sangre;
        this.telefono = telefono;
        this.obraSocial = obraSocial;
        this.nroAfiliado = nroAfiliado;
        this.antecedentesMedico = antecedentesMedico;
    }

    public static Persona instancia(Integer idPersona, String apellidos, String nombres, LocalDate fechaNacimiento, String domicilio, TipoDocumento tipoDocumento, String documento, Sangre sangre,
                                    String telefono, ObraSocial obraSocial, String nroAfiliado, Collection<AntecedenteMedico> antecedentesMedico) throws PersonaIncompletaException {

        if(apellidos==null || nombres ==null || fechaNacimiento==null || tipoDocumento==null || documento==null || nroAfiliado==null || sangre==null){
            throw new PersonaIncompletaException();
        }

        return new Persona(idPersona,apellidos,nombres,fechaNacimiento,domicilio,tipoDocumento,documento,sangre,telefono,obraSocial,nroAfiliado,antecedentesMedico);
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public String getDocumento() {
        return documento;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }
}
