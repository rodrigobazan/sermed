package ar.com.koodi.sermedboundaries.ServiceIntegrationTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import ModeloApi.PersonaDTO;

public class BuscarPersonasEntreAfiliadosDeBajaIntegrationTest {

	private String url ="http://localhost:8080";
	
	@Test
	public void existePersonaPorDNI_NoExistePersona_Devuelve204() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/persona/afiliados/baja/documento/1234567/tipodocumento/DNI");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
	}
	
	@Test
	public void existePersonaPorDNI_ExistePersona_Devuelve200() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/persona/afiliados/baja/documento/7654321/tipodocumento/DNI");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
	}
	
	@Test
	public void existePersonaEntreAfiliados_ExistenDatos_DevuelveJsonCorrecto() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/persona/afiliados/baja/documento/7654321/tipodocumento/DNI");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		String response = EntityUtils.toString(httpResponse.getEntity());
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.registerModule(new ParameterNamesModule());
		objectMapper.registerModule(new Jdk8Module());
		PersonaDTO personaDTOs = objectMapper.readValue(response, PersonaDTO.class);
		assertNotNull(personaDTOs.apellidos);
	}
}
