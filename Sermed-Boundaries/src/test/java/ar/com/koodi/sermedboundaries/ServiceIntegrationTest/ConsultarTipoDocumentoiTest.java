package ar.com.koodi.sermedboundaries.ServiceIntegrationTest;

import ModeloApi.TipoDocumentoDTO;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;


import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ConsultarTipoDocumentoiTest {

    private String url = "http://localhost:8080";

    @Test
    public void consultarTipoDocumento_ExistenTipoDocumento_Devuelve200() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("admin","123456");
        Header header = new BasicHeader("Authorization","Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/tipodocumento");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }
    @Test
    public void consultarTipoDocumento_NoExistenTipoDocumento_Devuelve404() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("admin","123456");
        Header header = new BasicHeader("Authorization","Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/tipodocumento");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void consultarTipoDocumento_ExisteTipoDocumento_JsonCorrecto() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("admin","123456");
        Header header = new BasicHeader("Authorization","Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/tipodocumento");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        String response = EntityUtils.toString(httpResponse.getEntity());
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        TipoDocumentoDTO[] tipoDocumentoDTOS = objectMapper.readValue(response, TipoDocumentoDTO[].class);
        assertNotNull(tipoDocumentoDTOS[0].nombre);
    }

    @Test
    public void consultarTipoDocumentoPorNombre_ExistenDatos_Devuelve200() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("admin","123456");
        Header header = new BasicHeader("Authorization","Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/tipodocumento/nombre/dni");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void consultarTipoDocumentoPorNombre_NoExistenDatos_Devuelve404() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("admin","123456");
        Header header = new BasicHeader("Authorization","Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/tipodocumento/nombre/dni");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void consultarTipoDocumentoPorNombre_ExisteTipoDocumento_JsonCorrecto() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("admin","123456");
        Header header = new BasicHeader("Authorization","Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/tipodocumento/nombre/dni");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        String response = EntityUtils.toString(httpResponse.getEntity());
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        TipoDocumentoDTO[] tipoDocumentoDTOS = objectMapper.readValue(response, TipoDocumentoDTO[].class);
        assertNotNull(tipoDocumentoDTOS[0].nombre);
    }

    @Test
    public void consultarTipoDocumentoPorNombreUnico_ExistenTipoDocumento_Devuelve200() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("admin","123456");
        Header header = new BasicHeader("Authorization","Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/tipodocumento/nombreunico/dni");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }
    @Test
    public void consultarTipoDocumentoPorNombreUnico_NoExistenTipoDocumento_Devuelve404() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("admin","123456");
        Header header = new BasicHeader("Authorization","Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/tipodocumento/nombreunico/dni");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void consultarTipoDocumentoPorNombreUnico_ExisteTipoDocumento_JsonCorrecto() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("admin","123456");
        Header header = new BasicHeader("Authorization","Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/tipodocumento/nombreunico/dni");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        String response = EntityUtils.toString(httpResponse.getEntity());
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        TipoDocumentoDTO tipoDocumentoDTOS = objectMapper.readValue(response, TipoDocumentoDTO.class);
        assertNotNull(tipoDocumentoDTOS.nombre);
    }











}
