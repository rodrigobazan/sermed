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

import ModeloApi.AfeccionDTO;
import ModeloApi.VisitaDTO;

import java.time.LocalDate;

public class ConsultarVisitaIntegrationTest {

	private String url = "http://localhost:8080";

	@Test
	public void consultarVisitas_ExistenVisitas_Devuelve200() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/visitas");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
	}

	/*@Test
	public void consultarVisitas_NoExistenVisitas_Devuelve204() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/visitas");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
	}*/

	@Test
	public void consultarVisitas_ExistenVisitas_DevuelveJsonCorrecto() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/visitas");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		String response = EntityUtils.toString(httpResponse.getEntity());
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.registerModule(new JavaTimeModule());
		objectMapper.registerModule(new ParameterNamesModule());
		objectMapper.registerModule(new Jdk8Module());
		VisitaDTO[] visitaDTOs = objectMapper.readValue(response, VisitaDTO[].class);
		assertNotNull(visitaDTOs[0].numeroVisita);
	}
	
	@Test
	public void consultarVisitasEntreFechas_ExistenVisitas_Devuelve200() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/visitas/fechadesde/2018-10-01/fechahasta/"+LocalDate.now());
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
	}
	
	@Test
	public void consultarVisitasEntreFechas_NoExistenVisitas_Devuelve204() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/visitas/fechadesde/2018-01-01/fechahasta/2018-01-02");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
	}
	
	@Test
	public void consultarVisitasEntreFechas_ExistenVisitas_DevuelveJsonCorrecto() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/visitas/fechadesde/2018-10-01/fechahasta/"+ LocalDate.now());
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		String response = EntityUtils.toString(httpResponse.getEntity());
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.registerModule(new JavaTimeModule());
		objectMapper.registerModule(new ParameterNamesModule());
		objectMapper.registerModule(new Jdk8Module());
		VisitaDTO[] visitaDTOs = objectMapper.readValue(response, VisitaDTO[].class);
		assertNotNull(visitaDTOs[0].numeroVisita);
	}
	
	@Test
	public void consultarVisitaPorNumero_ExisteVisita_Devuelve200() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/visita/numero/10");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
	}
	
	@Test
	public void consultarVisitaPorNumero_NoExisteVisita_Devuelve204() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/visita/numero/0");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
	}
	
	@Test
	public void consultarVisitaPorNumero_ExisteVisita_DevuelveJsonCorrecto() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/visita/numero/10");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		String response = EntityUtils.toString(httpResponse.getEntity());
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.registerModule(new JavaTimeModule());
		objectMapper.registerModule(new ParameterNamesModule());
		objectMapper.registerModule(new Jdk8Module());
		VisitaDTO visitaDTOs = objectMapper.readValue(response, VisitaDTO.class);
		assertNotNull(visitaDTOs.numeroVisita);
	}
}
