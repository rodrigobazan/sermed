package ar.com.koodi.sermedboundaries.ServiceIntegrationTest;

import ModeloApi.SangreDTO;
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

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConsultarSangreIntegrationTest {
    private String url = "http://localhost:8080";


    @Test
    public void consultarTipoSangre_ExistenDatos_Devuelve200() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("admin","123456");
        Header header = new BasicHeader("Authorization","Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/sangre");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void consultarTipoSangre_NoExistenDatos_Devuelve404() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("admin","123456");
        Header header = new BasicHeader("Authorization","Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/sangre/asd");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
    }

    /*@Test
    public void consultarTipoSangre_NoExistenDatos_Devuelve204() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("admin","123456");
        Header header = new BasicHeader("Authorization","Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/sangre");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
    }*/

    @Test
    public void consultarTipoSangre_ExistenDatos_DevuelveJsonCorrecto() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/sangre");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        String response = EntityUtils.toString(httpResponse.getEntity());
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        SangreDTO[] sangreDTOS = objectMapper.readValue(response, SangreDTO[].class);
        assertNotNull(sangreDTOS[0].grupo);
    }

    @Test
    public void consultarTipoSangrePorGrupo_ExistenDatos_Devuelve200() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("admin","123456");
        Header header = new BasicHeader("Authorization","Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/sangre/grupo/A");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void consultarTipoSangrePorGrupo_NoExistenDatos_Devuelve404() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("admin","123456");
        Header header = new BasicHeader("Authorization","Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/sangre/grupo/A/asdas");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void consultarTipoSangrePorGrupo_NoExistenDatos_Devuelve204() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("admin","123456");
        Header header = new BasicHeader("Authorization","Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/sangre/grupo/c");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
    }

    @Test
    public void consultarTipoSangreporGrupo_ExistenDatos_DevuelveJsonCorrecto() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/sangre/grupo/A");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        String response = EntityUtils.toString(httpResponse.getEntity());
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        SangreDTO[] sangreDTOS = objectMapper.readValue(response, SangreDTO[].class);
        assertNotNull(sangreDTOS[0].grupo);
    }

    @Test
    public void consultarTipoSangrePorFactor_ExistenDatos_Devuelve200() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("admin","123456");
        Header header = new BasicHeader("Authorization","Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/sangre/factor/RH+");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void consultarTipoSangrePorFactor_NoExistenDatos_Devuelve404() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("admin","123456");
        Header header = new BasicHeader("Authorization","Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/sangre/factor/RH+/asdasd");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void consultarTipoSangrePorFactor_NoExistenDatos_Devuelve204() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("admin","123456");
        Header header = new BasicHeader("Authorization","Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/sangre/factor/RH*");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
    }

    @Test
    public void consultarTipoSangrePorFactor_ExistenDatos_DevuelveJsonCorrecto() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/sangre/factor/RH+");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        String response = EntityUtils.toString(httpResponse.getEntity());
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        SangreDTO[] sangreDTOS = objectMapper.readValue(response, SangreDTO[].class);
        assertNotNull(sangreDTOS[0].grupo);
    }

    @Test
    public void consultarTipoSangrePorGrupoFactor_ExistenDatos_Devuelve200() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("admin","123456");
        Header header = new BasicHeader("Authorization","Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/sangre/factor/RH+/grupo/A");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }

    @Test
    public void consultarTipoSangrePorGrupoFactor_NoExistenDatos_Devuelve404() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("admin","123456");
        Header header = new BasicHeader("Authorization","Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/sangre/factor/RH+/grupo/A/asda");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void consultarTipoSangrePorGrupoFactor_NoExistenDatos_Devuelve204() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("admin","123456");
        Header header = new BasicHeader("Authorization","Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/sangre/factor/RH*/grupo/A");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NO_CONTENT));
    }

    @Test
    public void consultarTipoSangrePorGrupoFactor_ExistenDatos_DevuelveJsonCorrecto() throws Exception {
        String token = TokenAuthentication.obtainAccessToken("usuario", "123456");
        Header header = new BasicHeader("Authorization", "Bearer "+token);
        HttpUriRequest request = new HttpGet(url+"/sermed/sangre/factor/RH+/grupo/A");
        request.setHeader(header);
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        String response = EntityUtils.toString(httpResponse.getEntity());
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        SangreDTO sangreDTOS = objectMapper.readValue(response, SangreDTO.class);
        assertNotNull(sangreDTOS.grupo);
    }


}
