package ar.com.koodi.sermedadaptador;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import Adaptadores.ConsultarObrasSocialesAdapter;
import Excepciones.ObraSocialNoExisteException;
import Inputs.ConsultarObrasSocialesInput;
import Mockito.MockitoExtension;
import Modelo.ObraSocial;
import ModeloApi.ObraSocialDTO;

@ExtendWith(MockitoExtension.class)
public class ConsultarObrasSocialesAdapterTest {

	@Mock
	private ConsultarObrasSocialesInput consultarObrasSocialesInput;

	@Test
	public void consultarObrasSociales_ExistenObrasSociales_ReturnListaObrasSociales() {
		ConsultarObrasSocialesAdapter consultarObrasSocialesAdapter = new ConsultarObrasSocialesAdapter(consultarObrasSocialesInput);
		when(consultarObrasSocialesInput.consultarObrasSociales()).thenReturn(factoryObrasSociales());
		List<ObraSocialDTO> obrasSocialesDTO = consultarObrasSocialesAdapter.consultarObrasSociales();
		Assertions.assertEquals(5, obrasSocialesDTO.size());
	}
	
	@Test
	public void consultarObrasSocialesPorNombre_ExistenObrasSociales_ReturnListaObrasSociales() {
		ConsultarObrasSocialesAdapter consultarObrasSocialesAdapter = new ConsultarObrasSocialesAdapter(consultarObrasSocialesInput);
		when(consultarObrasSocialesInput.consultarObrasSocialesPorNombre("os")).thenReturn(filtroObrasSociales());
		List<ObraSocialDTO> obrasSocialesDTO = consultarObrasSocialesAdapter.consultarObrasSocialesPorNombre("os");
		Assertions.assertEquals(3, obrasSocialesDTO.size());
	}

	@Test
	public void consultarObraSocialPorNombre_ExisteObraSocial_ReturnObraSocial() throws ObraSocialNoExisteException {
		ConsultarObrasSocialesAdapter consultarObrasSocialesAdapter = new ConsultarObrasSocialesAdapter(consultarObrasSocialesInput);
		when(consultarObrasSocialesInput.consultarObraSocialPorNombre("OSFATUN")).thenReturn(new ObraSocial(1, "OSFATUN"));
		ObraSocialDTO obraSocial = consultarObrasSocialesAdapter.consultarObraSocialPorNombre("OSFATUN");
		Assertions.assertEquals("OSFATUN", obraSocial.obraSocial);
	}
	
	private List<ObraSocial> factoryObrasSociales() {
		try {
			List<ObraSocial> lista = new ArrayList<>();
			lista.add(new ObraSocial(1, "OSFATUN"));
			lista.add(new ObraSocial(2, "OSDE"));
			lista.add(new ObraSocial(3, "APOS"));
			lista.add(new ObraSocial(4, "PAMI"));
			lista.add(new ObraSocial(5, "SANCOR SALUD"));
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	private List<ObraSocial> filtroObrasSociales() {
        try {
            List<ObraSocial> lista = new ArrayList<>();
            lista.add(new ObraSocial(1, "OSFATUN"));
            lista.add(new ObraSocial(2, "OSDE"));
            lista.add(new ObraSocial(3, "APOS"));
            return lista;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
