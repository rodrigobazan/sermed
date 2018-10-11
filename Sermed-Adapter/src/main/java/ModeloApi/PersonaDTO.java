package ModeloApi;

import Modelo.ObraSocial;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Collection;

public class PersonaDTO {

    @JsonProperty("idPersona")
    public final Integer idPersona;

    @JsonProperty("apellidos")
    public final String apellidos;

    @JsonProperty("nombres")
    public final String nombres;

    @JsonProperty("fechaNacimiento")
    @JsonFormat(pattern = "dd/MM/yyyy")
    public final LocalDate fechaNacimiento;

    @JsonProperty("domicilio")
    public final String domicilio;

    @JsonProperty("tipoDocumento")
    public final TipoDocumentoDTO tipoDocumento;

    @JsonProperty("documento")
    public final String documento;

    @JsonProperty("sangre")
    public final SangreDTO sangre;

    @JsonProperty("telefono")
    public final String telefono;

    @JsonProperty("obraSocial")
    public final ObraSocialDTO obraSocial;

    @JsonProperty("nroAfiliado")
    public final String nroAfiliado;

    @JsonProperty("nroOrden")
    public final Integer nroOrden;

    @JsonProperty("antecedentesMedico")
    public final Collection<AntecedenteMedicoDTO> antecedentesMedico;

    public PersonaDTO(@JsonProperty("idPersona") Integer idPersona,
                      @JsonProperty("apellidos") String apellidos,
                      @JsonProperty("nombres") String nombres,
                      @JsonProperty("fechaNacimiento") LocalDate fechaNacimiento,
                      @JsonProperty("domicilio") String domicilio,
                      @JsonProperty("tipoDocumento") TipoDocumentoDTO tipoDocumento,
                      @JsonProperty("documento") String documento,
                      @JsonProperty("sangre") SangreDTO sangre,
                      @JsonProperty("telefono") String telefono,
                      @JsonProperty("obraSocial") ObraSocialDTO obraSocial,
                      @JsonProperty("nroAfiliado") String nroAfiliado,
                      @JsonProperty("nroOrden") Integer nroOrden,
                      @JsonProperty("antecedentesMedico") Collection<AntecedenteMedicoDTO> antecedentesMedico) {
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
        this.nroOrden = nroOrden;
        this.antecedentesMedico = antecedentesMedico;
    }
}
