package Modelo;

import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
//import com.sun.deploy.util.StringUtils;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


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
    private Integer nroOrden;
    private Collection<AntecedenteMedico> antecedentesMedico;

    public Persona(Integer idPersona, String apellidos, String nombres, LocalDate fechaNacimiento, String domicilio, TipoDocumento tipoDocumento, String documento, Sangre sangre,
                   String telefono, ObraSocial obraSocial, String nroAfiliado, Collection<AntecedenteMedico> antecedentesMedico, Integer nroOrden) {
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
        this.nroOrden=nroOrden;
    }

    public static Persona instancia(Integer idPersona, String apellidos, String nombres, LocalDate fechaNacimiento, String domicilio, TipoDocumento tipoDocumento, String documento, Sangre sangre,
                                    String telefono, ObraSocial obraSocial, String nroAfiliado, Collection<AntecedenteMedico> antecedentesMedico, Integer nroOrden) throws PersonaIncompletaException, DniConPuntosException {

        if((apellidos==null || apellidos.equals("")) || (nombres ==null || nombres.equals("")) || fechaNacimiento==null || tipoDocumento==null || (documento==null || documento.equals("")) || sangre==null || (domicilio == null || domicilio.equals(""))){
            throw new PersonaIncompletaException();
        }
        if(documento.contains(".")){
            throw new DniConPuntosException();
        }

        return new Persona(idPersona,apellidos,nombres,fechaNacimiento,domicilio,tipoDocumento,documento,sangre,telefono,obraSocial,nroAfiliado,antecedentesMedico,nroOrden);
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

    public String getNumeroAfiliado() {
        if(this.nroAfiliado.equals("")){
            return this.nroAfiliado;
        }
        return this.nroAfiliado + "-" + String.format("%02d", this.nroOrden);
    }

    public String mostrarPersona() {
        return this.apellidos +", "+this.nombres+" ("+this.getNumeroAfiliado()+").";
    }

    public void setNroOrden(int nroDeOrden) {
        this.nroOrden=nroDeOrden;
    }

    public int getNroOrden() {
        return this.nroOrden;
    }

    public void setNumeroAfiliado(String numeroAfiliado) {
        this.nroAfiliado=numeroAfiliado;
    }

    public void modificarDatos(Persona personaDatosNuevos) throws PersonaIncompletaException {
        if((personaDatosNuevos.apellidos==null || personaDatosNuevos.apellidos.equals("")) ||
                (personaDatosNuevos.nombres ==null || personaDatosNuevos.nombres.equals("")) ||
                personaDatosNuevos.fechaNacimiento==null ||
                (personaDatosNuevos.domicilio == null || personaDatosNuevos.domicilio.equals(""))
                || personaDatosNuevos.tipoDocumento==null ||
                (personaDatosNuevos.documento==null || personaDatosNuevos.documento.equals("")) || personaDatosNuevos.sangre==null){
            throw new PersonaIncompletaException();
        }
        this.apellidos = personaDatosNuevos.apellidos;
        this.nombres = personaDatosNuevos.nombres;
        this.nroAfiliado = personaDatosNuevos.nroAfiliado;
        this.antecedentesMedico = personaDatosNuevos.antecedentesMedico;
        this.documento = personaDatosNuevos.documento;
        this.domicilio = personaDatosNuevos.domicilio;
        this.fechaNacimiento = personaDatosNuevos.fechaNacimiento;
        this.obraSocial = personaDatosNuevos.obraSocial;
        this.sangre= personaDatosNuevos.sangre;
        this.telefono = personaDatosNuevos.telefono;
        this.tipoDocumento = personaDatosNuevos.tipoDocumento;

    }

    public String obtenerDocumentoCompleto(){
        return this.getTipoDocumento().getNombre() + this.getDocumento();
    }

    public String nombreApellido() {
        return this.nombres +", "+this.apellidos;
    }

    public String mostrarDomicilio() {
        return this.domicilio;
    }

    public List<AntecedenteMedico> devolverAntecedentes() {
        return (List<AntecedenteMedico>) this.antecedentesMedico;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public Sangre getSangre() {
        return sangre;
    }

    public String getTelefono() {
        return telefono;
    }

    public ObraSocial getObraSocial() {
        return obraSocial;
    }

    public String getNroAfiliado() {
        return nroAfiliado;
    }

    public Collection<AntecedenteMedico> getAntecedentesMedico() {
        return antecedentesMedico;
    }
}
