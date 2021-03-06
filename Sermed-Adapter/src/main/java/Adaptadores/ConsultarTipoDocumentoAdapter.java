package Adaptadores;

import java.util.ArrayList;
import java.util.List;

import Excepciones.TipoDocumentoNoExisteException;
import Factorys.TipoDocumentoFactory;
import Inputs.ConsultarTipoDocumentoInput;
import Modelo.TipoDocumento;
import ModeloApi.TipoDocumentoDTO;

public class ConsultarTipoDocumentoAdapter {
	
	private ConsultarTipoDocumentoInput consultarTipoDocumentoInput;

	public ConsultarTipoDocumentoAdapter(ConsultarTipoDocumentoInput consultarTipoDocumentoInput) {
		super();
		this.consultarTipoDocumentoInput = consultarTipoDocumentoInput;
	}

	public List<TipoDocumentoDTO> consultarTiposDocumentos() {
		List<TipoDocumento> tipos = (List<TipoDocumento>) consultarTipoDocumentoInput.consultarTodosLosTiposDeDocumento();
		List<TipoDocumentoDTO> tiposDTO = new ArrayList<>();
		if(!tipos.isEmpty()) {
			tipos.stream().forEach(tipo -> {
				tiposDTO.add(TipoDocumentoFactory.mapeoCoreDTO(tipo));
			});
		}
		return tiposDTO;
	}
	
	public List<TipoDocumentoDTO> consultarTiposDocumentoPorNombre(String nombre) {
		List<TipoDocumento> tipos = (List<TipoDocumento>) consultarTipoDocumentoInput.consultarTiposDocumentosPorNombre(nombre);
		List<TipoDocumentoDTO> tiposDTO = new ArrayList<>();
		if(!tipos.isEmpty()) {
			tipos.stream().forEach(tipo->{
				tiposDTO.add(TipoDocumentoFactory.mapeoCoreDTO(tipo));
			});
		}
		return tiposDTO;
	}
	
	public TipoDocumentoDTO consultarTiposDocumentoUnicoPorNombre(String nombre) throws TipoDocumentoNoExisteException {
		return TipoDocumentoFactory.mapeoCoreDTO(consultarTipoDocumentoInput.consultarTiposDocumentoUnicoPorNombre(nombre));
	}


}
