package ModeloApi;

import Modelo.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TipoDocumentoDTO {
	
	@JsonProperty("idTipoDocumento")
	public final Integer idTipoDocumento;
	
	@JsonProperty("nombre")
	public final String nombre;

	public TipoDocumentoDTO(@JsonProperty("idTipoDocumento") Integer idTipoDocumento, @JsonProperty("nombre") String nombre) {
		super();
		this.idTipoDocumento = idTipoDocumento;
		this.nombre = nombre;
	}
	
	
}
