package ar.com.koodi.sermedboundaries.ServiceIntegrationTest;

import ModeloApi.AfeccionDTO;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ConsultarAfeccionesIntegrationTest {

    private String url = "http://localhost:8080";

    @Test
    public void consultarAfecciones_ExistenAfecciones_Devuelve200() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/afecciones");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void consultarAfecciones_ExistenAfecciones_DevuelveJsonCorrecto() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/afecciones");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        String response = EntityUtils.toString(httpResponse.getEntity());
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        AfeccionDTO[] afeccionDTOS = objectMapper.readValue(response, AfeccionDTO[].class);
        assertNotNull(afeccionDTOS[0].nombreAfeccion);
    }

    @Test
    public void consultarAfeccionesPorNombre_ExistenAfecciones_Devuelve200() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/afecciones/nombre/diab");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void consultarAfeccionesPorNombre_NoExistenAfecciones_Devuelve204() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/afecciones/nombre/asdasd");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
    }

    @Test
    public void consultarAfeccionesPorNombre_ExistenAfecciones_JsonCorrecto() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/afecciones/nombre/diab");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        String response = EntityUtils.toString(httpResponse.getEntity());
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        AfeccionDTO[] afecciones = objectMapper.readValue(response, AfeccionDTO[].class);
        assertNotNull(afecciones[0].nombreAfeccion);
    }

    @Test
    public void consultarAfeccionPorNombre_ExisteAfeccion_Devuelve200() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/afeccion/nombre/Diabetes");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void consultarAfeccionPorNombre_NoExisteAfeccion_Devuelve204() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/afeccion/nombre/xd");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
    }

    @Test
    public void consultarAfeccionPorNombre_ExistenAfecciones_JsonCorrecto() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/afeccion/nombre/Diabetes");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        String response = EntityUtils.toString(httpResponse.getEntity());
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        AfeccionDTO afeccion = objectMapper.readValue(response, AfeccionDTO.class);
        assertEquals("Diabetes", afeccion.nombreAfeccion);
    }


}
