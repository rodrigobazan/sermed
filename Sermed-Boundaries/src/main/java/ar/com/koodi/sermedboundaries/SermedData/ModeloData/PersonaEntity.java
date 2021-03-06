package ar.com.koodi.sermedboundaries.SermedData.ModeloData;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Entity(name = "persona")
@SequenceGenerator(name ="persona_idpersona_seq", initialValue = 1, sequenceName = "persona_idpersona_seq", allocationSize = 1)
public class PersonaEntity {

    @Id
    @Column(name = "idpersona", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persona_idpersona_seq")
    private Integer idPersona;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "fechanacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "domicilio")
    private String domicilio;

    @ManyToOne
    @JoinColumn(name = "tipodocumento", referencedColumnName = "idtipodocumento")
    private TipoDocumentoEntity tipoDocumento;

    @Column(name = "documento")
    private String documento;

    @ManyToOne
    @JoinColumn(name = "sangre", referencedColumnName = "idsangre")
    private SangreEntity sangre;

    @Column(name = "telefono")
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "obrasocial", referencedColumnName = "idobrasocial")
    private ObraSocialEntity obraSocial;

    @Column(name = "nroafiliado")
    private String nroAfiliado;

    @Column(name = "nroorden")
    private Integer nroOrden;
    
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<AntecedenteMedicoEntity> antecedenteMedicoCollection;

    public PersonaEntity() {
    }

    public PersonaEntity(String apellidos, String nombres, LocalDate fechaNacimiento, String domicilio, TipoDocumentoEntity tipoDocumento, String documento, SangreEntity sangre, String telefono, ObraSocialEntity obraSocial, String nroAfiliado, Collection<AntecedenteMedicoEntity> antecedenteMedicoCollection, Integer nroOrden) {
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
        this.antecedenteMedicoCollection = antecedenteMedicoCollection;
        this.nroOrden = nroOrden;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public TipoDocumentoEntity getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumentoEntity tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public SangreEntity getSangre() {
        return sangre;
    }

    public void setSangre(SangreEntity sangre) {
        this.sangre = sangre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ObraSocialEntity getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(ObraSocialEntity obraSocial) {
        this.obraSocial = obraSocial;
    }

    public Integer getNroOrden() {
        return nroOrden;
    }

    public void setNroOrden(Integer nroOrden) {
        this.nroOrden = nroOrden;
    }

    public Collection<AntecedenteMedicoEntity> getAntecedenteMedicoCollection() {
    	if(antecedenteMedicoCollection == null) {
    		return new ArrayList<>();
    	}
        return antecedenteMedicoCollection;
    }

    public void setAntecedenteMedicoCollection(Collection<AntecedenteMedicoEntity> antecedenteMedicoCollection) {
        this.antecedenteMedicoCollection = antecedenteMedicoCollection;
    }

    public String getNroAfiliado() {
        return nroAfiliado;
    }

    public void setNroAfiliado(String nroAfiliado) {
        this.nroAfiliado = nroAfiliado;
    }
    
    
}
