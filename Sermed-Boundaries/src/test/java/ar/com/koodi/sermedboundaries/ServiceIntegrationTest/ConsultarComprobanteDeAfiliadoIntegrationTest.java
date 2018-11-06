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
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import ModeloApi.ComprobanteDTO;

import java.time.LocalDate;

public class ConsultarComprobanteDeAfiliadoIntegrationTest {

	private String url = "http://localhost:8080";

	@Test
	public void consultarTodosLosComprobantes_ExistenComprobantes_Devuelve200() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/comprobantes/afiliado/numeroafiliado/999993");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
	}

	/*@Test
	public void consultarTodosLosComprobantes_NoExistenComprobantes_Devuelve204() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/comprobantes/afiliado/numeroafiliado/190700");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
	}*/

	@Test
	public void consultarTodosLosComprobantes_NoExisteAfiliado_Devuelve412() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/comprobantes/afiliado/numeroafiliado/000000");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_PRECONDITION_FAILED));
	}

	@Test
	public void consultarTodosLosComprobantes_ExistenComprobantes_DevuelveJsonCorrecto() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/comprobantes/afiliado/numeroafiliado/999993");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		String response = EntityUtils.toString(httpResponse.getEntity());
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.registerModule(new ParameterNamesModule());
		objectMapper.registerModule(new Jdk8Module());
		ComprobanteDTO[] comprobanteDTOS = objectMapper.readValue(response, ComprobanteDTO[].class);
		assertNotNull(comprobanteDTOS[0].idComprobante);
	}

	@Test
	public void consultarComprobantesAfiliadoPorFechas_ExistenComprobantes_Devuelve200() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url
				+ "/sermed/comprobantes/afiliado/numeroafiliado/999993/fechadesde/2018-10-01/fechahasta/"+ LocalDate.now());
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
	}

	@Test
	public void consultarComprobantesAfiliadoPorFechas_NoExistenComprobantes_Devuelve204() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url
				+ "/sermed/comprobantes/afiliado/numeroafiliado/999993/fechadesde/2018-01-01/fechahasta/2018-01-31");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
	}

	@Test
	public void consultarComprobantesAfiliadoPorFechas_NoExisteAfiliado_Devuelve412() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url
				+ "/sermed/comprobantes/afiliado/numeroafiliado/000000/fechadesde/2018-10-01/fechahasta/2018-10-31");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_PRECONDITION_FAILED));
	}

	@Test
	public void consultarComprobantesAfiliadoPorFechas_ExistenComprobantes_DevuelveJsonCorrecto() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url
				+ "/sermed/comprobantes/afiliado/numeroafiliado/999993/fechadesde/2018-10-01/fechahasta/"+ LocalDate.now());
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		String response = EntityUtils.toString(httpResponse.getEntity());
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.registerModule(new ParameterNamesModule());
		objectMapper.registerModule(new Jdk8Module());
		ComprobanteDTO[] comprobanteDTOS = objectMapper.readValue(response, ComprobanteDTO[].class);
		assertNotNull(comprobanteDTOS[0].idComprobante);
	}
}
