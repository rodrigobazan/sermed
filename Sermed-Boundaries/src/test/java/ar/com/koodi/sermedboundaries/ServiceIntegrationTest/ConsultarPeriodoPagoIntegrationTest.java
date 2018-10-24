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

import ModeloApi.PeriodoPagoDTO;

public class ConsultarPeriodoPagoIntegrationTest {

	private String url="http://localhost:8080";
	
	@Test
	public void consultarPeriodoPago_ExistenDatos_Devuelve200() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/periodospago");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
	}
	
	@Test
	public void consultarPeriodoPago_NoExistenDatos_Devuelve204() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/periodospago");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
	}
	
	@Test
	public void consultarPeriodoPago_ExistenDatos_DevuelveJsonCorrecto() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/periodospago");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        String response= EntityUtils.toString(httpResponse.getEntity());
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        PeriodoPagoDTO[] periodoPagoDTOs = objectMapper.readValue(response, PeriodoPagoDTO[].class);
        assertNotNull(periodoPagoDTOs[0].mes);
	}	

	@Test
	public void consultarPeriodoPagoPorAnio_ExistenDatos_Devuelve200() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/periodospago/anio/2017");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
	}
	
	@Test
	public void consultarPeriodoPagoPorAnio_NoExistenDatos_Devuelve204() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/periodospago/anio/1900");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
	}
	
	@Test
	public void consultarPeriodoPagoPorAnio_ExistenDatos_DevuelveJsonCorrecto() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/periodospago/anio/2017");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        String response= EntityUtils.toString(httpResponse.getEntity());
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        PeriodoPagoDTO[] periodoPagoDTOs = objectMapper.readValue(response, PeriodoPagoDTO[].class);
        assertNotNull(periodoPagoDTOs[0].mes);
	}	
	
	@Test
	public void consultarPeriodoPagoPorMes_ExistenDatos_Devuelve200() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/periodospago/mes/2");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
	}
	
	@Test
	public void consultarPeriodoPagoPorMes_NoExistenDatos_Devuelve204() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/periodospago/mes/0");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
	}

	@Test
	public void consultarPeriodoPagoPorMes_ExistenDatos_DevuelveJsonCorrecto() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/periodospago/mes/2");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        String response= EntityUtils.toString(httpResponse.getEntity());
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        PeriodoPagoDTO[] periodoPagoDTOs = objectMapper.readValue(response, PeriodoPagoDTO[].class);
        assertNotNull(periodoPagoDTOs[0].mes);
	}	
	
	@Test
	public void consultarPeriodoPagoPorMesAnio_ExistenDatos_Devuelve200() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/periodospago/mes/2/anio/2018");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
	}
	
	@Test
	public void consultarPeriodoPagoPorMesAnio_NoExistenDatos_Devuelve204() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/periodospago/mes/0/anio/2018");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
	}
	
	@Test
	public void consultarPeriodoPagoPorMesAnio_ExistenDatos_DevuelveJsonCorrecto() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/periodospago/mes/2/anio/2018");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        String response= EntityUtils.toString(httpResponse.getEntity());
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        PeriodoPagoDTO periodoPagoDTOs = objectMapper.readValue(response, PeriodoPagoDTO.class);
        assertNotNull(periodoPagoDTOs.mes);
	}	
	
	@Test
	public void consultarPeriodoPagoPorIntervalo_ExistenDatos_Devuelve200() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/periodospago/mesdesde/2/meshasta/4/anio/2018");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
	}
	
	@Test
	public void consultarPeriodoPagoPorIntervalo_NoExistenDatos_Devuelve204() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/periodospago/mesdesde/0/meshasta/0/anio/2018");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
	}
	
	@Test
	public void consultarPeriodoPagoPorIntervalo_ExistenDatos_DevuelveJsonCorrecto() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/periodospago/mesdesde/2/meshasta/4/anio/2018");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        String response= EntityUtils.toString(httpResponse.getEntity());
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        PeriodoPagoDTO[] periodoPagoDTOs = objectMapper.readValue(response, PeriodoPagoDTO[].class);
        assertNotNull(periodoPagoDTOs[0].mes);
	}	
}
