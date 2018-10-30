package ar.com.koodi.sermedboundaries.ServiceIntegrationTest;

import ModeloApi.ComprobanteDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConsultarComprobanteIntegrationTest {

    private String url = "http://localhost:8080";

    @Test
    public void consultarComprobante_ExisteComprobantes_devuelve200() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/comprobantes");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void consultarComprobante_NoExisteComprobantes_devuelve204() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/comprobantes");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
    }

    @Test
    public void consultarComprobante_ExistenDatos_DevuelveJsonCorrecto() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/comprobantes");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        String response = EntityUtils.toString(httpResponse.getEntity());
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ComprobanteDTO[] comprobanteDTOS = objectMapper.readValue(response, ComprobanteDTO[].class);
        assertNotNull(comprobanteDTOS[0].idComprobante);
    }



    @Test
    public void consultarComprobantePorNumero_ExisteComprobantes_devuelve200() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/comprobantes/numero/1234");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void consultarComprobantePorNumero_NoExisteComprobantes_devuelve204() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/comprobantes/numero/1234");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
    }

    @Test
    public void consultarComprobantePorFecha_ExisteComprobantes_devuelve200() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/comprobantes/fechas/2018-03-18/2018-04-31");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void consultarComprobantePorFecha_NoExisteComprobantes_devuelve204() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/comprobantes/fechas/2018-02-18/2018-04-18");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
    }







}
