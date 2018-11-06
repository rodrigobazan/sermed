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
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import ModeloApi.AfiliadoDTO;

public class ConsultarAfiliadoIntegrationTest {

	private String url="http://localhost:8080";
	
	@Test
	public void consultarAfiliados_ExistenDatos_Devuelve200() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/afiliados");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
	}

	
	/*@Test
	public void consultarAfiliados_NoExistenDatos_Devuelve204() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/afiliados");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
	}*/
	
	@Test
	public void consultarAfiliados_ExistenDatos_DevuelveJsonCorrecto() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/afiliados");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        String response = EntityUtils.toString(httpResponse.getEntity());
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.registerModule(new JavaTimeModule());
		objectMapper.registerModule(new ParameterNamesModule());
		objectMapper.registerModule(new Jdk8Module());
        AfiliadoDTO[] afiliadoDTOs = objectMapper.readValue(response, AfiliadoDTO[].class);
        assertNotNull(afiliadoDTOs[0].titular.apellidos);
    }
	
	@Test
	public void consultarAfiliadosPorNumero_ExistenDatos_Devuelve200() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/afiliados/numero/190600");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
	}
	
	@Test
	public void consultarAfiliadosPorNumero_NoExistenDatos_Devuelve204() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/afiliados/numero/000000");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
	}
	
	@Test
	public void consultarAfiliadosPorNumero_ExistenDatos_DevuelveJsonCorrecto() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url + "/sermed/afiliados/numero/190600");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        String response = EntityUtils.toString(httpResponse.getEntity());
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.registerModule(new JavaTimeModule());
		objectMapper.registerModule(new ParameterNamesModule());
		objectMapper.registerModule(new Jdk8Module());
        AfiliadoDTO[] afiliadoDTOs = objectMapper.readValue(response, AfiliadoDTO[].class);
        assertNotNull(afiliadoDTOs[0].titular.apellidos);
    }
	
	@Test
	public void consultarAfiliadoPorNumero_ExistenDatos_Devuelve200() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/afiliado/numero/190600");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
	}
	
	@Test
	public void consultarAfiliadoPorNumero_NoExistenDatos_Devuelve204() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
		Header header = new BasicHeader("Authorization", "Bearer " + token);
		HttpUriRequest request = new HttpGet(url + "/sermed/afiliado/numero/000000");
		request.setHeader(header);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
	}
	
	@Test
	public void consultarAfiliadoPorNumero_ExistenDatos_DevuelveJsonCorrecto() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url + "/sermed/afiliado/numero/190600");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        String response = EntityUtils.toString(httpResponse.getEntity());
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        objectMapper.registerModule(new JavaTimeModule());
		objectMapper.registerModule(new ParameterNamesModule());
		objectMapper.registerModule(new Jdk8Module());
        AfiliadoDTO afiliadoDTOs = objectMapper.readValue(response, AfiliadoDTO.class);
        assertNotNull(afiliadoDTOs.titular.apellidos);
    }	
	
	
}
