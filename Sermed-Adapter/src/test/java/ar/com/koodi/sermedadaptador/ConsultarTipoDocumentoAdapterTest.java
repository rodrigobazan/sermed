package ar.com.koodi.sermedadaptador;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import Adaptadores.ConsultarTipoDocumentoAdapter;
import Excepciones.TipoDocumentoNoExisteException;
import Inputs.ConsultarTipoDocumentoInput;
import Mockito.MockitoExtension;
import Modelo.TipoDocumento;
import ModeloApi.TipoDocumentoDTO;

@ExtendWith(MockitoExtension.class)
public class ConsultarTipoDocumentoAdapterTest {

	@Mock
	private ConsultarTipoDocumentoInput consultarTipoDocumentoInput;

	@Test
	public void consultarTodosLosTiposDeDocumento_ExistenTipoDocumento_ReturnListaTipoDocumento() {
		ConsultarTipoDocumentoAdapter consultarTipoDocumentoAdapter = new ConsultarTipoDocumentoAdapter(consultarTipoDocumentoInput);
		when(consultarTipoDocumentoInput.consultarTodosLosTiposDeDocumento()).thenReturn(crearTiposDocumento());
		List<TipoDocumentoDTO> tipos = consultarTipoDocumentoAdapter.consultarTiposDocumentos();
		Assertions.assertEquals(4,tipos.size());		
	}
	
	@Test
	public void consultarTiposDocumentosPorNombre_ExistenTipoDocumento_ReturnListaTipoDocumento() {
		ConsultarTipoDocumentoAdapter consultarTipoDocumentoAdapter = new ConsultarTipoDocumentoAdapter(consultarTipoDocumentoInput);
		when(consultarTipoDocumentoInput.consultarTiposDocumentosPorNombre("Libreta")).thenReturn(crearFiltroArray());
		List<TipoDocumentoDTO>tipos = consultarTipoDocumentoAdapter.consultarTiposDocumentoPorNombre("Libreta");
		Assertions.assertEquals(2, tipos.size());
	}
	
	@Test
	public void consultarTipoDocumentoUnicoPorNombre_ExisteTipoDocumento_ReturnTipoDocumento() throws TipoDocumentoNoExisteException {
		ConsultarTipoDocumentoAdapter consultarTipoDocumentoAdapter = new ConsultarTipoDocumentoAdapter(consultarTipoDocumentoInput);
		when(consultarTipoDocumentoInput.consultarTiposDocumentoUnicoPorNombre("DNI")).thenReturn(new TipoDocumento(1, "DNI"));
		TipoDocumentoDTO tipo = consultarTipoDocumentoAdapter.consultarTiposDocumentoUnicoPorNombre("DNI");
		Assertions.assertEquals("DNI", tipo.nombre);
	}

	private List<TipoDocumento> crearFiltroArray() {
		try {
			List<TipoDocumento> lista = new ArrayList<>();
			lista.add(new TipoDocumento(2, "Libreta Civica"));
			lista.add(new TipoDocumento(4, "Libreta de Enrolamiento"));
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	private List<TipoDocumento> crearTiposDocumento() {
		try {
			List<TipoDocumento> lista = new ArrayList<>();
			lista.add(new TipoDocumento(1, "DNI"));
			lista.add(new TipoDocumento(2, "Libreta Civica"));
			lista.add(new TipoDocumento(3, "Pasaporte"));
			lista.add(new TipoDocumento(4, "Libreta de Enrolamiento"));
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
}
