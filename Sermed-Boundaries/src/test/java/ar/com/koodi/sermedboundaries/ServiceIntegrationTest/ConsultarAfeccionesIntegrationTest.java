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

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
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

}
