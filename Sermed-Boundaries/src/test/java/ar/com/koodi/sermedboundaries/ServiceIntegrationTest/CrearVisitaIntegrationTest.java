package ar.com.koodi.sermedboundaries.ServiceIntegrationTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class CrearVisitaIntegrationTest {
	
	private String url="http://localhost:8080";
	
	@Test
	public void crearVisita_CreaCorrectamente_Devuelve200() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.registerModule(new ParameterNamesModule());
		mapper.registerModule(new Jdk8Module());
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		JSONObject visitaJSON = new JSONObject();
		visitaJSON.put("idVisita", null);		
		visitaJSON.put("numeroVisita", 10);		
		JSONObject personaJSON = new JSONObject();
		personaJSON.put("idPersona", 16);
		personaJSON.put("apellidos", "Simpson");
		personaJSON.put("nombres", "Homero");
		personaJSON.put("fechaNacimiento", LocalDate.of(2000, 3, 30));
		personaJSON.put("domicilio", "Av. Siempre Viva 900");
		JSONObject tipoDocumento = new JSONObject();
		tipoDocumento.put("idTipoDocumento", 1);
		tipoDocumento.put("nombre", "DNI");
		personaJSON.put("tipoDocumento", tipoDocumento);
		personaJSON.put("documento", "11111111");
		JSONObject sangre = new JSONObject();
		sangre.put("idSangre", 1);
		sangre.put("grupo", "A");
		sangre.put("factor", "RH+");
		personaJSON.put("sangre", sangre);
		personaJSON.put("telefono","9999");
		JSONObject obraSocial= new JSONObject();
		obraSocial.put("idObraSocial", 1);
		obraSocial.put("obraSocial", "OSFATUN");
		personaJSON.put("obraSocial", obraSocial);
		personaJSON.put("nroAfiliado", "190001");
		personaJSON.put("nroOrden", 1);
		personaJSON.put("antecedentesMedico", null);
		visitaJSON.put("elPaciente", personaJSON);
		visitaJSON.put("fechaHoraVisita", LocalDateTime.now());
		visitaJSON.put("motivoConsulta", "Presion Alta");
		visitaJSON.put("antecedentesPatologicos", "Hipertension");
		visitaJSON.put("tensionArterial", "14");
		visitaJSON.put("temperatura", 36f);
		visitaJSON.put("frecuenciaCardiaca", 2);
		visitaJSON.put("saturacionOxigeno", 2);
		visitaJSON.put("medicacionHabitual", "");
		visitaJSON.put("examenClinico", "Normal");
		visitaJSON.put("diagnosticoPresuntivo", "Estres");
		visitaJSON.put("tratamiento", "Vacaciones");
		visitaJSON.put("observaciones", "");
		JSONObject medico = new JSONObject();
		medico.put("idMedico", 1);
		medico.put("apellido", "Torres");
		medico.put("nombre", "German");
		medico.put("matricula", 1234);
		medico.put("telefono", "0000");
		visitaJSON.put("medico", medico);
		JSONObject enfermero = new JSONObject();
		enfermero.put("idEnfermero", 1);
		enfermero.put("apellido", "Bazan");
		enfermero.put("nombre", "Rodrigo");
		enfermero.put("matricula", 1235);
		enfermero.put("telefono", "1111");
		visitaJSON.put("enfermero", enfermero);
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpPost post = new HttpPost(url+"/sermed/visita/nueva");        
        StringEntity se = new StringEntity(visitaJSON.toString());
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        post.setEntity(se);
        post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpUriRequest request = post;
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
	}

}
