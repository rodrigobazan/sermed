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

public class ConsultarPersonaIntegrationTest {

	private String url = "http://localhost:8080";

	@Test
	public void consultarPersonas_ExistenPersonas_Devuelve200() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/personas");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
	}

	@Test
	public void consultarPersonas_NoExistenPersonas_Devuelve204() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/personas");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
	}

	@Test
	public void consultarPersonas_ExistenDatos_DevuelveJsonCorrecto() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/personas");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		String response = EntityUtils.toString(httpResponse.getEntity());
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.registerModule(new ParameterNamesModule());
		objectMapper.registerModule(new Jdk8Module());
		PersonaDTO[] personaDTOs = objectMapper.readValue(response, PersonaDTO[].class);
		assertNotNull(personaDTOs[0].apellidos);
	}
	
	@Test
	public void consultarPersonasPorApellido_ExistenDatos_Devuelve200() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/personas/apellido/Tompson");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
	}
	
	@Test
	public void consultarPersonasPorApellido_NoExistenDatos_Devuelve204() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/personas/apellido/qwe");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
	}
	
	@Test
	public void consultarPersonasPorApellido_ExistenDatos_DevuelveJsonCorrecto() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/personas/apellido/Tompson");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		String response = EntityUtils.toString(httpResponse.getEntity());
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.registerModule(new ParameterNamesModule());
		objectMapper.registerModule(new Jdk8Module());
		PersonaDTO[] personaDTOs = objectMapper.readValue(response, PersonaDTO[].class);
		assertNotNull(personaDTOs[0].apellidos);
	}
	
	@Test
	public void consultarPersonaPorNumeroAfiliado_ExistePersona_Devuelve200() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/persona/nroafiliado/190000/nroorden/0");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
	}
	
	@Test
	public void consultarPersonaPorNumeroAfiliado_NoExistePersona_Devuelve204() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/persona/nroafiliado/000000/nroorden/0");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
	}
	
	@Test
	public void consultarPersonaPorNumeroAfiliado_ExistenDatos_DevuelveJsonCorrecto() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/persona/nroafiliado/190000/nroorden/0");
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
