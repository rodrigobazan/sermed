package ar.com.koodi.sermedboundaries.ServiceIntegrationTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.junit.jupiter.api.Test;

public class AnularComprobanteIntegrationTest {

	private String url="http://localhost:8080";
	
	@Test
	public void anularComprobante_ComprobanteAnulado_Devuelve200() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/comprobante/anular/numero/1234-567890");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
	}
	
	@Test
	public void anularComprobante_ComprobanteYaEstabaAnulado_Devuelve500() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/comprobante/anular/numero/1234-567892");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_INTERNAL_SERVER_ERROR));
	}
	
	@Test
	public void anularComprobante_ComprobanteNoExiste_Devuelve412() throws Exception {
		String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/comprobante/anular/numero/0000-567892");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_PRECONDITION_FAILED));
	}
}
